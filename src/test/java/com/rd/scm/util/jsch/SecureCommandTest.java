package com.rd.scm.util.jsch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SecureCommandTest {
    
    @Test
    @DisplayName("Test testRunCommandSuccessful")
    public void testRunCommandSuccessful() {
        SecureCommand client = new SecureCommand();

        try {
            client.connect("AZSD-MailFTPSvr.dev.quest.corp", 22, "statftp", "ftpStat!", null, null);            

            client.runCommand("cmd /c cd /d E:/Statftp/PS_PROD/sql && sqlplus STAT/St24816at@ORA630w2 @sql_without_error.sql ");
            String returnResult = client.getResult(15);
            client.disconnect();
            Assertions.assertEquals(client.getExitCode(), 0, "command execution should return status 0");
            Assertions.assertNotEquals(returnResult, null, "command execution should return results");
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    @Test
    @DisplayName("Test testRunCommandKeypairSuccessful")
    public void testRunCommandKeypairSuccessful() {

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

        //Public key pair
        //ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCaaeUkoCfgUuJkX+TCr/XO9BpI9hd2yJHMrv4u1LjMKVHNDC1Egvq3y6nJhI9CVtL8ZMMz61VIJx1mtTI7bMjDb69N63XVTw/xcVkGq1FyeGgPgpswAfdewgIvo4KaRr2r2l9efgu7bMeX5NCD8P/02q3VWp55cONMiHT6ql7KCw== Stat Generated using JSch

        SecureCommand client = new SecureCommand();

        try {
            client.connect("10.1.83.252", 22, "applmgr", null, privateKey, null);

            client.runCommand("ls . ");
            String returnResult = client.getResult(15);
            client.disconnect();
            Assertions.assertEquals(client.getExitCode(), 0, "command execution should return status 0");
            Assertions.assertNotEquals(returnResult, null, "command execution should return results");
        } catch (Exception e) {
            e.printStackTrace();
        }    

    }


}