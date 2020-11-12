package com.rd.scm.util.jsch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SftpClientTest {
    
    SftpClient client = new SftpClient();

    String privateKey = 
    "-----BEGIN RSA PRIVATE KEY-----\n" +
    "MIICXgIBAAKBgQCaaeUkoCfgUuJkX+TCr/XO9BpI9hd2yJHMrv4u1LjMKVHNDC1E\n" +
    "gvq3y6nJhI9CVtL8ZMMz61VIJx1mtTI7bMjDb69N63XVTw/xcVkGq1FyeGgPgpsw\n" +
    "AfdewgIvo4KaRr2r2l9efgu7bMeX5NCD8P/02q3VWp55cONMiHT6ql7KCwIDAQAB\n" +
    "AoGBAJHDDtyxFpNqim1oHEEv62EuCrX/II0vrnpCYo2AnuWSx7ZBHWZEIjpn1eH1\n" +
    "8WoVHdvFnoMfToTq75UpzL5N5YEoP0MhRww8AlgeK71q4KLoIZakYgQRBKMAgyrM\n" +
    "tSd+QSbIZZknou4A545nZMSiN+tXvjLIdavCgWZCpgpDZwOBAkEAybVemfe0XAqn\n" +
    "ARh6h31cyoW2uGkkhUHcjRDTPMzHKDYRed3x96Lov60uSYUSv7mtvvC1An+0QIPH\n" +
    "rBZ2iGRgwQJBAMP5solZ9LnOQif4Kp7LV4ZUqUQ/m4YkDmx7oNHzszNqdnkZuwbg\n" +
    "9+cXZQWZrQZFCtS1AE4FgKIedR+nDFFpUcsCQQCKdcixkhqpj9ph4ybA+OoIT09F\n" +
    "OU+LddFquCD4vGz9ilfmutbNMXPpl3yNlR5wo4nD50GprHXUT2FyHJ1TNOxBAkEA\n" +
    "rST5VlzB2T+gN4wMq8tgAvDds8y27acBj1sS1SwI0Y5oKcpP8V3l6KhEutPxiK6L\n" +
    "SS65nDVEVvet509BlNQGTQJAOuZ2R5A4Q4UKKkTA/4eNgZ9349dqrImx5bXch0ex\n" +
    "2+74BixlWeV1syzkm1BbgjHZ1jJwUz6JetCVj4Glj5ED9g==\n" +
    "-----END RSA PRIVATE KEY-----";


    @Test
    @DisplayName("Test testRunCommandSuccessful")
    public void testRunCommandSuccessful() {

        try {
            client.connectPassword("AZSD-MailFTPSvr.dev.quest.corp", 22, "statftp", "ftpStat!");
            client.ls(".");
            Assertions.assertEquals(client.isConnected(), true, "password connection should return true");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    @Test
    @DisplayName("Test testRunCommandKeypairSuccessful")
    public void testRunCommandKeypairSuccessful() {

        //Public key pair
        //ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCaaeUkoCfgUuJkX+TCr/XO9BpI9hd2yJHMrv4u1LjMKVHNDC1Egvq3y6nJhI9CVtL8ZMMz61VIJx1mtTI7bMjDb69N63XVTw/xcVkGq1FyeGgPgpswAfdewgIvo4KaRr2r2l9efgu7bMeX5NCD8P/02q3VWp55cONMiHT6ql7KCw== Stat Generated using JSch

        try {
            client.connectKeypair("10.1.83.252", 22, "applmgr", privateKey);
            client.ls(".");
            Assertions.assertEquals(client.isConnected(), true, "keypair connection should return true");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    @Test
    @DisplayName("Test testRunSingleFilePutCommandSuccessful")
    public void testRunSingleFilePutCommandSuccessful() {

        //check end of line character on unix using: file filename.txt
        try {
            client.connectKeypair("10.1.83.252", 22, "applmgr", privateKey);
            boolean b = client.sftpPut("target/test-classes/sftp/account.xml", "./account.xml");
            b = client.sftpPut("target/test-classes/sftp/account.xml", "./account1.xml", SftpClient.CONVERT_TO_UNIX_ASCII);
            Assertions.assertEquals(b, true, "sftp put should return true");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }


}