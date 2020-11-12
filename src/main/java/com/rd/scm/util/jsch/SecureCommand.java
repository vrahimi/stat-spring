package com.rd.scm.util.jsch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class SecureCommand {
    
    private JSch jsch;
    private Session session;
    private Channel channel;
    private ChannelExec cExec;    

    private String fileAbsolutePath;
    
    MyLogger logger = new MyLogger();
        
    public SecureCommand() {
        super();
    }

    public void connect(String host, int port, String user, String password, String privateKey, String passphrase) throws Exception {
                
        JSch.setLogger(logger);
        jsch = new JSch();        
        
        if (privateKey != null) {            
            try {
                jsch.addIdentity(createTmpKeyFile(privateKey), passphrase);
            } catch (Exception e) {
                throw new Exception("Authentication failed: " + e.getMessage());
            }            
        }
        try {
            session = jsch.getSession(user, host, port);
        } catch (JSchException e1) {
            throw new Exception("Session failed: " + e1.getMessage());
        }
        
        UserInfo ui = new MyUserInfo();
        session.setUserInfo(ui);
        session.setPassword(password);
        session.setConfig("PreferredAuthentications","password,publickey,keyboard-interactive,gssapi-with-mic");
        
        try {    
            session.connect();            
        } catch (Exception e2) {
            throw new Exception("Connection failed: " + e2.getMessage());            
        } finally {
            deleteFile(fileAbsolutePath);
        }        
    }

    public void disconnect() {
        channel.disconnect();
        session.disconnect();        
    }    
    
    public void runCommand(String cmd) throws Exception {
        logger.log(MyLogger.DEBUG, "SCM-Execute CMD \"" + cmd + "\" at " + new java.util.Date().toString());
        
        exeExec(cmd);
    }    
    
    public String getResult(int maxWait) throws Exception {
        return getResultExec(maxWait);    
    }
    
    public int getExitCode() throws Exception {
        return channel.getExitStatus();   
    }
    
    /********************************************************/
    /* Private Methods                                      */
    /********************************************************/
     
    private void exeExec(String cmd) throws Exception {
        //every execution requires a new channel
        channel = session.openChannel("exec");
        cExec = (ChannelExec) channel;    
        
        cExec.setInputStream(null);
        cExec.setErrStream(System.err);
        cExec.setCommand(cmd);

        cExec.connect();         
    }
    
    private String getResultExec (int maxWait) throws Exception {
        StringBuffer resultBuf = new StringBuffer();
        byte[] buffer = new byte[1024];    
        int bytes_read, i;
        String tmpBuf;        

        InputStream in = channel.getInputStream();            
        
        for (i=0; i < maxWait; i++) {
            try {                
                while (in.available() > 0) {    
                    bytes_read = in.read(buffer);    
                    
                    if (bytes_read > 0 ) {    
                        tmpBuf = new String(buffer,0,bytes_read);
                        resultBuf.append(tmpBuf);
                    }
                    
                    if (bytes_read == -1) {
                        break;
                    }     
                } 
                
                if (channel.isClosed()) {    
                    logger.log(MyLogger.DEBUG, "Command executed at " + new java.util.Date().toString() + " with exit status " + channel.getExitStatus());

                    break;
                } else if (!channel.isConnected()) {
                    logger.log(MyLogger.DEBUG, "Command disconnected at " + new java.util.Date().toString());                    
                    
                    break;
                } else if (channel.isEOF()) {
                    logger.log(MyLogger.DEBUG, "Command reach end of file at " + new java.util.Date().toString());
                    
                    break;
                }    
                
                Thread.sleep(1000);
                
            } catch (Exception e) {                
                e.printStackTrace();
            }        
        }
        
        logger.log(MyLogger.DEBUG, "Command execution timeout count " + i + " at " + new java.util.Date().toString());
        logger.log(MyLogger.DEBUG, "Command output: " + String.format("%n") + resultBuf.toString());
            
        return resultBuf.toString();    
    }
         
    private String createTmpKeyFile( String privateKey ) throws Exception {
        FileWriter fileWriter = null;
        BufferedWriter buffWriter = null;
        try {
            File tempPrivateKeyFile = File.createTempFile("jsch", ".rd");

            fileWriter = new FileWriter( tempPrivateKeyFile.getPath() );
            buffWriter = new BufferedWriter( fileWriter );
            buffWriter.write( privateKey );

            fileAbsolutePath = tempPrivateKeyFile.getAbsolutePath() ;            
        } catch( IOException ioe ) {
            throw new Exception( "Unable to create private key file." );
        }  finally {
            buffWriter.close();
            fileWriter.close();
        }
        
        return fileAbsolutePath;
    }        

    private void deleteFile(String fileName) {
        if (fileName != null) {            
            File target = new File(fileName);
            
            if (target.exists()) {
                target.delete();
            }
        }
    }
    
    private static class MyUserInfo implements com.jcraft.jsch.UserInfo {
        String passwd;

        public String getPassword() {
            return passwd;
        }

        public boolean promptYesNo(String str) {
            return true;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return true;
        }

        public boolean promptPassword(String message) {
            return true;
        }

        public void showMessage(String message) {
        }
    }
    
    public static Logger log4 = LoggerFactory.getLogger(MyLogger.class.getPackage().getName());
    public static class MyLogger implements com.jcraft.jsch.Logger {         
        static java.util.Hashtable<Integer,String> name=new java.util.Hashtable<Integer,String>();        
        static {
            name.put(Integer.valueOf(DEBUG), "DEBUG: ");
            name.put(Integer.valueOf(INFO), "INFO: ");
            name.put(Integer.valueOf(WARN), "WARN: ");
            name.put(Integer.valueOf(ERROR), "ERROR: ");
            name.put(Integer.valueOf(FATAL), "FATAL: ");
        }
        
        public boolean isEnabled(int level) {
            return true;
        }
        
        public void log(int level, String message) {        
            if(log4.isDebugEnabled()) {
                switch (level) {
                    case DEBUG: log4.debug(message); break;
                    case INFO:  log4.info(message); break;
                    case WARN:  log4.warn(message); break;
                    case ERROR: log4.error(message); break;
                    case FATAL: log4.error(message); break;
                }
            }
        }    
        
        public void close() {}

        public MyLogger() {}
    }    
}


