package com.rd.scm.util.jsch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.KeyPair;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

public class SftpClient {

    public static final String CHANNEL_TYPE_SFTP = "sftp";
    public static final String SEPARATOR = "/";
    public static final byte CONVERT_NONE = 0;
    public static final byte CONVERT_TO_UNIX_ASCII = 1;
    public static final byte CONVERT_TO_WIN_ASCII = 2;
    private String mHost;
    private String mUser;
    private int mPort = 22; 
    private String mPassword;
    private String mPrivatekey;

    private Session mSession;
    private ChannelSftp mChannel;
    private String mLocalWorkDir;
    private String mRemoteWorkDir;
    private String fileAbsolutePath;
    private static JSch jSch;
    private static final Logger log = LoggerFactory.getLogger(SftpClient.class.getName());
    
//    private static JSchLogger jschLogger = new JSchLogger();
    public SftpClient () {
        // initialize jsch
        jSch = new JSch();
        mChannel = null;
        
//        jSch.setLogger(jschLogger);     
    }

    public void connectPassword(String host, int port, String user, String password) throws Exception {
        this.mHost = host;
        this.mPort = port;
        this.mUser = user;
        this.mPassword = password;
    }

    public void connectKeypair(String host, int port, String user, String privateKey) throws Exception {
        this.mHost = host;
        this.mPort = port;
        this.mUser = user;
        this.mPrivatekey = privateKey;
        createJSchIdentity( mPrivatekey );
        getChannel();
    }

    public void setLocalWorkDir ( String localWorkDir ) {
        this.mLocalWorkDir = localWorkDir;
    }

    public void setRemoteWorkDir ( String rmWorkDir ) {
        this.mRemoteWorkDir = rmWorkDir;
    }

    public String getLocalWorkDir () {
        return this.mLocalWorkDir;
    }

    public String getRemoteWorkDir () {
        return mRemoteWorkDir;
    }

    /**
     * Process cd command.
     * @param path
     */
    public void cd( String path )
        throws JSchException, SftpException {
        getChannel().cd( path );
    }

    /**
     * Process ls command.
     *
     * @param path
     */
    public Vector<String> ls( String path ) throws JSchException, SftpException {
    
        Vector<LsEntry> files;
        Vector<String> fileNames = new Vector<String>();
        if (path==null || path.length()==0) {
            files = getChannel().ls(".");
        } else{
            files = getChannel().ls( path );
        }
        if(files != null && !files.isEmpty()) {
    
            for(LsEntry entry : files) {
                SftpATTRS attrs = entry.getAttrs();
                String name = entry.getLongname();
                // Ignore "." and ".."
                if (name.equals(".") || name.equals("..")) continue;
                // Ignore directories
                if (attrs.isDir()) continue;
                fileNames.add(entry.getFilename());                
            }
        }
        
        return fileNames;
    }
        
    /*
    public void lsRecursive( String parentDir, String currentDir, String[] fileExtensions, 
            String objectTypeCode, String oaCustom, String product, String language, String oaBase, boolean isOAF, boolean isCustom, ObjectTypeListVO objects) 
                    throws IOException, JSchException, SftpException {
        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }
        
        Vector subFiles = getChannel().ls(dirToList);
        for(int i=0; i < subFiles.size(); i++){
            LsEntry entry = (LsEntry)subFiles.elementAt(i);
            SftpATTRS attrs = entry.getAttrs();
            String currentFileName = entry.getFilename();
    
            if (currentFileName.equals(".")
                    || currentFileName.equals("..")) {
                // skip parent directory and directory itself
                continue;
            }
            if (attrs.isDir()) {
                lsRecursive(dirToList, currentFileName, fileExtensions, objectTypeCode, oaCustom, product, language, oaBase, isOAF, isCustom, objects);
            } else {
                for(String ext : fileExtensions) {
                    
                    if (currentFileName.toLowerCase().matches(".*\\." + ext.toLowerCase())) {
                        
                        String objName = "";
                        if(isCustom) {
                            objName = (dirToList.startsWith("./") ? dirToList.substring(2, dirToList.length()) : dirToList) + "/" + currentFileName;
                        } else {
                            objName = (dirToList.startsWith(".") ? dirToList.substring(1, dirToList.length()) : dirToList) + "/" + currentFileName;
                        }
                        //This is special processing for OAF and Custom objects
                        if(isOAF) {
                            if(!Utility.OAFProcessing(objName, objectTypeCode, product)) {
                                continue;
                            }
                        }
                        
                        String[] objNameArray = Utility.processName(objName, oaCustom, oaBase, "", objectTypeCode, isOAF);
                        
                        //Insert into VO
                        Map<String, String> obj = new LinkedHashMap<String, String>();
                        if(objNameArray[0].equals(objNameArray[1])) {
                            objects.addDecodeMapping(ObjectTypeListVO.OV1, ObjectTypeListVO.F1);
                        } else {
                            obj.put(ObjectTypeListVO.V1, objNameArray[0]);
                        }
                        obj.put(ObjectTypeListVO.F1, objNameArray[1]);
                        
                        objects.addObjectValue(obj);
                        break;
                    }
                }
            }
        }
    }*/
    
    
    /**
     * Process rm command.
     * @param path
     */
    public void rm( String file ) {
        try {
            getChannel().rm( file );
        } catch ( Exception ex ) {
            log.error( "rm [" + file + "] Error:", ex );
        }
    }

    /**
     * Process chmod command.
     * @param path
     */
    public boolean chmod( String mod, String file ) {
        try {
            int foo = 0;
            byte[] bar= mod.getBytes();
            int k;
            for(int j=0; j<bar.length; j++) {
                k=bar[j];
                if(k<'0'||k>'7'){foo=-1; break;}
                foo<<=3;
                foo|=(k-'0');
            }
            if (foo ==-1) {
                log.error( "chmod [" + mod + " " + file + "] Error: Mod is invalid.");
                return false;
            }
            getChannel().chmod( foo, file );
        } catch ( Exception ex ) {
            log.error( "chmod [" + mod + " " + file + "] Error:", ex );
            return false;
        }
        return true;
    }

    /**
     * Process chmod command.
     * @param path
     */
    public boolean chmod( int mod, String file ) {
        try {
            int foo = 0;
            // add convertion logic to fix chmod error
            byte[] bar = String.valueOf(mod).getBytes();
            int k;
            for(int j=0; j<bar.length; j++) {
                k=bar[j];
                if(k<'0'||k>'7') {foo=-1; break;}
                foo<<=3;
                foo|=(k-'0');
            }
            if (foo ==-1) {
                log.error( "chmod [" + mod + " " + file + "] Error: Mod is invalid.");
                return false;
            }
            getChannel().chmod( foo, file );
        } catch ( Exception ex ) {
            log.error( "chmod [" + mod + " " + file + "] Error:", ex );
            return false;
        }
        return true;
    }

    /**
     * Process rmdir command.
     * @param path
     */
    public void rmdir( String path ) {
        try {
            getChannel().rm( path );
        } catch ( Exception ex ) {
            log.error( "rmdir [" + path + "] Error:", ex );
        }
    }

    
    public boolean mkdir(String directory) {        
        try {
            getChannel().mkdir(directory);
            return true;
        } catch ( Exception ex ) {
            log.debug( "mkdir [" + directory + "] failed.");    //stat-947
        }
        
        return false;
    }
    
    /**
     * Process pwd command.
     * @return remote workdir
     */
    public String pwd() {
        try {
            String workdir = getChannel().pwd();
            return workdir;
        } catch ( Exception ex ) {
            log.error( "pwd Error:", ex );
        }
        return null;
    }

    /**
     * Process get command.
     * @param filename
     */
    public boolean get( String filename ) {
        try {
            return sftpGet( filename, this.mLocalWorkDir );
        } catch ( Exception ex ) {
            log.error( "get [" + filename + "] Error:", ex );
        }
        return false;
    }

    /**
     * Process put command: put the localWork dir file to remoteworkDir.
     * @param filename
     */
    public boolean put( String filename ) {
        try {
            return sftpPut( this.getLocalWorkDir() + File.separator + filename, this.mRemoteWorkDir );
        } catch ( Exception ex ) {
            log.error( "put [" + filename + "] Error:", ex );
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * process sftp get command, checking remoteSourceFile, if it is a directory, report error.
     * @param remoteSourceFile - the get source file in sftp server side
     * @param localDestFile - the get destination in local
     * @return transfer result: true - successful; false - failed
     */
    public boolean sftpGet( String remoteSourceFile, String localDestFile ) {
        //check remote file
        SftpATTRS fAttr = null;
        try {
            fAttr = getChannel().stat( remoteSourceFile );
        } catch ( Exception ex ) {
            log.error( "File[" + remoteSourceFile + "] error:" + ex );
            return false;
        }

        if ( fAttr != null && fAttr.isDir() ) {
            log.error( "SFTP error: File[" + remoteSourceFile + "] is a directory." );
            return false;
        } else {
            try {
                File tempf = new File( localDestFile );
                getChannel().get( remoteSourceFile, tempf.getCanonicalPath(), null );
            } catch ( IOException ex ) {
                log.error( "File[" + remoteSourceFile + "]:\n--", ex );
                ex.printStackTrace();
                return false;
            } catch ( Exception ex ) {
                log.error( "SFTP transfer error: File[" + remoteSourceFile + "]:\n--", ex );
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public InputStream sftpGet( String remoteSourceFile ) {
        //check remote file
        SftpATTRS fAttr = null;
        try {
            fAttr = getChannel().stat( remoteSourceFile );
        } catch ( Exception ex ) {
            log.error( "File[" + remoteSourceFile + "] error:" + ex );
            return null;
        }

        if ( fAttr != null && fAttr.isDir() ) {
            log.error( "SFTP error: File[" + remoteSourceFile + "] is a directory." );
            return null;
        } else {
            try {
                return getChannel().get( remoteSourceFile);
            } catch ( Exception ex ) {
                log.error( "SFTP transfer error: File[" + remoteSourceFile + "]:\n--", ex );
                ex.printStackTrace();
                return null;
            }
        }        
    }
    
    /**
     *
     */
    public boolean sftpPut( String localSourceFile, String remoteDestFile ) {

        try {
            File f = new File(localSourceFile);
            getChannel().put( f.getCanonicalPath(), remoteDestFile, null );
            log.debug("done with put file: " + remoteDestFile);
        } catch ( IOException ex ) {
            log.error( "put File [" + remoteDestFile + "]:\n", ex );
            return false;
        } catch ( Exception ex ) {
            log.error( "SFTP transfer error: put File [" + remoteDestFile + "]:\n--", ex );
            return false;
        }

        return true;
    }

    /**
     * Process sftp put command with special wraper function on demanded:
     * convert to unix ASCII and convert to Windows ASCII.
     * If the remoteDestFile's parent directory doesn't exist, the SftpException
     * will be thrown.
     *
     * convertCode - constants code defined by this class:
     *      CONVERT_NONE, CONVERT_TO_UNIX_ASCII, CONVERT_TO_WIN_ASCII
     */
    public boolean sftpPut ( String localSourceFile, String remoteDestFile, byte convertCode ) {
        // if no conversion required
        if (convertCode == CONVERT_NONE) {
            return sftpPut ( localSourceFile, remoteDestFile);
        }
        try {
            InputStream is = null;
            try {
                //add logice to convert ASCII Stream between Unix and Windows
                if (convertCode == CONVERT_TO_UNIX_ASCII) {
                    //convert to unix ASCII Stream
                    is = new FromNetASCIIInputStream(new FileInputStream(localSourceFile));
                } else if (convertCode == CONVERT_TO_WIN_ASCII) {
                    //convert to windows ASCII Stream
                    is = new ToNetASCIIInputStream(new FileInputStream(localSourceFile));
                }
            } catch (Exception e) {
                log.error( "Error: converting file ["+localSourceFile+"] to "
                        + ((convertCode == CONVERT_TO_UNIX_ASCII)? "Unix": "Windows") + " format.", e );
                return false;
            }
            getChannel().put(is, remoteDestFile, null);
            log.debug("done with put file: " + remoteDestFile);
        } catch ( Exception ex ) {
            log.error( "SFTP transfer error: put File [" + remoteDestFile + "]:\n", ex );
            return false;
        }
        
        return true;
    }
    
    public boolean sftpPut( InputStream localInputStream, String remoteDestFile ) {

        try {
            getChannel().put( localInputStream, remoteDestFile, null );
        } catch ( Exception ex ) {
            log.error( "SFTP transfer error: put File [" + remoteDestFile + "]:\n", ex );
            return false;
        }
        
        return true;
    }
    
    public boolean sftpPut( InputStream localInputStream, String remoteDestFile, byte convertCode ) {
        if (convertCode == CONVERT_NONE) {
            return sftpPut ( localInputStream, remoteDestFile );
        }
        
        InputStream is = null;
        try {
            //convert ASCII Stream between Unix and Windows
            if (convertCode == CONVERT_TO_UNIX_ASCII) {
                //convert to unix ASCII Stream
                is = new FromNetASCIIInputStream(localInputStream);
            } else if (convertCode == CONVERT_TO_WIN_ASCII) {
                //convert to windows ASCII Stream
                is = new ToNetASCIIInputStream(localInputStream);
            }
        } catch (Exception e) {
            log.error( "Error: converting stream to "
                    + ((convertCode == CONVERT_TO_UNIX_ASCII)? "Unix": "Windows") + " format.", e );
            return false;
        }
        
        return sftpPut ( is, remoteDestFile );
    }

    /**
     * This method can generate a rsa or dsa keypair.
     * 
     * @throws Exception
     */
    public String[] generateKeypair(String keyType, int keySize) throws Exception {
        
        String[] key = new String[2];
        int type = 0;
        KeyPair keyPair = null;
        
        try {
            if(keyType == null) {
                throw new Exception( "Invalid key type." );
            }
            else if(keyType.toLowerCase().equals("rsa")) { type = KeyPair.RSA; }
            else if(keyType.toLowerCase().equals("dsa")) { type=KeyPair.DSA; }
            else {
                throw new Exception( "Invalid key type." );
            }
            
            keyPair = KeyPair.genKeyPair(jSch, type, keySize);
            OutputStream os = new ByteArrayOutputStream();
            keyPair.writePrivateKey(os);
            key[0] = os.toString();
            
            os = new ByteArrayOutputStream();
            keyPair.writePublicKey(os, "Stat Generated using JSch");
            //System.out.println("Finger print: "+kpair.getFingerPrint());
            key[1] = os.toString();
        } finally {
            keyPair.dispose();
        }
        
        return key;
    }
    
    /**
     * close the sftp channel.
     */
    public void close() {
        if( fileAbsolutePath != null ) {
            try {
                File tempPrivateKeyFile = new File( fileAbsolutePath );
                if( tempPrivateKeyFile.exists() ) {
                    if (log.isDebugEnabled())
                        log.debug("[Migration Clean up]remove temp key file:"+ tempPrivateKeyFile.getName());
                    tempPrivateKeyFile.delete();
                    tempPrivateKeyFile = null;
                }
            } catch ( Exception e ) {}
        }

        try {
            mChannel.disconnect();
            mChannel = null;
        } catch ( Exception ex ) { }
        try {
            mSession.disconnect();
            mSession = null;
        } catch ( Exception ex1 ) {}
        if (log.isDebugEnabled()) {
            log.debug( "close sftp channel" );
        }
    }
    
    public boolean isConnected()  {
        boolean isConnected = false;
        
        try {
            isConnected = mSession.isConnected(); 
        } catch (Exception e) {
            
        }
        return isConnected;
    }

    public boolean fileExists ( String file )
        throws IOException {

        SftpATTRS fAttr = null;
        File f = new File( file );
        try {
             String currentPath = getChannel().pwd();
             String workFile = file;
             String parent = f.getParent();
             //CR0087935: for windows absolute path
             if (parent!=null && parent.length()>0 && parent.lastIndexOf(":")>=1) {
               String parentPath = parent.replaceAll("\\", "/");
               if ( currentPath.lastIndexOf(parentPath) <= 1) {
                 workFile = f.getName();
               }
             }
            fAttr = getChannel().stat( workFile );
            if ( fAttr != null && !fAttr.isDir() ) {
                return true;
            }

        } catch ( Exception ex ) { }

        return false;
    }

    public boolean fileExists ( String file, long[] fileSize )
            throws IOException {

        SftpATTRS fAttr = null;
        File f = new File( file );
        try {
             String currentPath = getChannel().pwd();
             String workFile = file;
             String parent = f.getParent();
             //for windows absolute path
             if (parent!=null && parent.length()>0 && parent.lastIndexOf(":")>=1){
               String parentPath = parent.replaceAll("\\", "/");
               if ( currentPath.lastIndexOf(parentPath) <= 1) {
                 workFile = f.getName();
               }
             }
            fAttr = getChannel().stat( workFile );
            if ( fAttr != null && !fAttr.isDir() ) {
                fileSize[0] = fAttr.getSize();
                return true;
            }

        } catch ( Exception ex ) {
           //skip for false
        }

        return false;
    }
    
    //for windows SSH server
    /**
     * Used to skip the banner message
     * Since jsch-0.1.16.jar.
     */
    public static class MyUserInfo
        implements UserInfo
    {

        public String getPassword () {
            return passwd;
        }

        public boolean promptYesNo ( String str ) {
            return true;
        }

        String passwd;
        public String getPassphrase () {
            return null;
        }

        public boolean promptPassphrase ( String message ) {
            return true;
        }

        public boolean promptPassword ( String message ) {
            return true;
        }

        public void showMessage ( String message ) {
        }
    }

    /**
     * Generate a private key file and add to user's JSch identity.
     *
     * @param privateKey
     * @throws IOException
     */
    private void createJSchIdentity( String privateKey ) throws Exception {
        // Use a temp file to hold the private key content.
        // Note: The file is deleted via the close() method so it must be
        //       invoked when the sftp session completes.
        try {
            File tempPrivateKeyFile = File.createTempFile("jsch", ".qst");

            if (log.isDebugEnabled()) {
                log.debug( "Key file: " + tempPrivateKeyFile.getAbsolutePath() );
            }

            FileWriter fileWriter = new FileWriter( tempPrivateKeyFile.getPath() );
            BufferedWriter buffWriter = new BufferedWriter( fileWriter );
            buffWriter.write( privateKey );
            buffWriter.close();
            fileWriter.close();

            fileAbsolutePath = tempPrivateKeyFile.getAbsolutePath() ;

            jSch.addIdentity( fileAbsolutePath );
        } catch( IOException ioe ) {
            throw new Exception( "Invalid private key file." );
        } catch( JSchException je ) {
            throw new Exception( "Invalid private key." );
        }
    }

    /**
     * Set connection to sftp channel for the system default user, port and password
     * @return ChannelSftp instance
     */
    private ChannelSftp getChannel() throws JSchException {
        if (log.isDebugEnabled()) {
            log.debug("trying ssh/sftp: " + mUser + "@" + mHost + ":" + mPort);
        }
        if (mChannel == null) {
            // Create the session
            if (mSession == null) {
                mSession = jSch.getSession(mUser, mHost, mPort);
                mSession.setPassword(mPassword);

                UserInfo ui = new MyUserInfo();
                mSession.setUserInfo(ui);
                
                mSession.setConfig("PreferredAuthentications","password,publickey,keyboard-interactive,gssapi-with-mic");    //6-23-10 cr235647 
                mSession.connect();
            }
            // Use the pooled channel, or create a new one
            mChannel = (ChannelSftp) mSession.openChannel(CHANNEL_TYPE_SFTP);
            mChannel.connect();
        }
        return mChannel;
    }


    public static void main(String[] argv) {
        
        //How to use the private/public keys on Linux?
        //If we have a private key/public key pair. Store the private key in Stat and
        //store the public key on remote computer under ~/.ssh/authorized_keys
        //This should give you a successful connection.
        
        //Some info about different formats of an RSA key:   pkcs#8 DER vs. openssl (PEM)
        //BEGIN RSA PRIVATE KEY is PKCS#1 and is just an RSA key. It is essentially just the key object from PKCS#8, but without the version or algorithm identifier in front. 
        //BEGIN PRIVATE KEY is PKCS#8 and indicates that the key type is included in the key data itself.
        //The unencrypted PKCS#8 encoded data starts and ends with the tags:
        //    -----BEGIN PRIVATE KEY-----
        //    BASE64 ENCODED DATA
        //    -----END PRIVATE KEY-----
        //Within the base64 encoded data the following DER structure is present:
        //    PrivateKeyInfo ::= SEQUENCE {
        //      version         Version,
        //      algorithm       AlgorithmIdentifier,
        //      PrivateKey      BIT STRING
        //    }
        //    AlgorithmIdentifier ::= SEQUENCE {
        //      algorithm       OBJECT IDENTIFIER,
        //      parameters      ANY DEFINED BY algorithm OPTIONAL
        //    }
        //So for an RSA private key, the OID is 1.2.840.113549.1.1.1 and there is a RSAPrivateKey as the PrivateKey key data bitstring.
        //As opposed to BEGIN RSA PRIVATE KEY, which always specifies an RSA key and therefore doesn't include a key type OID. BEGIN RSA PRIVATE KEY is PKCS#1:
        //The RSA private key PEM file is specific for RSA keys.
        //It starts and ends with the tags:
        //-----BEGIN RSA PRIVATE KEY-----
        //BASE64 ENCODED DATA
        //-----END RSA PRIVATE KEY-----
        //
        //This generates a RSA key in openssl format
        //openssl genrsa -out private_key.pem 4096
        //openssl rsa -pubout -in private_key.pem -out public_key.pem
        //# convert private key to pkcs8 format in order to import it from Java
        //openssl pkcs8 -topk8 -in private_key.pem -inform pem -out private_key_pkcs8.pem -outform pem -nocrypt
        
        
        //For DSA key:
        //ssh-keygen -t dsa -b 1024

            //String[] key = client.generateKeypair("rsa", 2048);
            //System.out.println( "==============================" );
            //System.out.println( key[0] );
            //System.out.println( "==============================" );
            //System.out.println( key[1] );
            //System.out.println( "==============================" );

    }

}
