package com.example.studooo_v1;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailSender {

    mailSenderInfo info;

    public mailSender() {
        this.info = new mailSenderInfo();
    }

    public String sendmailCode(String receiverAdress) throws MessagingException {
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", getInfo().getHost());
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getInfo().getMyEposaAdress(), getInfo().getMyEpostaPass());
            }
        });

        Random random = new Random();
        int maxNum = 999999;
        int minNum = 100000;

        // generating a random code between 100000 and 999999
        int randomNumInt = random.nextInt(maxNum - minNum + 1) + minNum;
        String randomNumString = String.valueOf(randomNumInt);

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverAdress));
        mimeMessage.setSubject("Studoo doğrulama kodu");
        mimeMessage.setText(randomNumString);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Transport.send(mimeMessage);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        return randomNumString;

    }

    public mailSenderInfo getInfo() {
        return info;
    }

    private class mailSenderInfo{
        private final String myEposaAdress = "studodeneme@gmail.com";
        private final String myEpostaPass = "fvclykphbduusmum";
        private final String host = "smtp.gmail.com";

        public String getMyEpostaPass() {
            return myEpostaPass;
        }

        public String getHost() {
            return host;
        }

        public String getMyEposaAdress() {
            return myEposaAdress;
        }
    }


}
