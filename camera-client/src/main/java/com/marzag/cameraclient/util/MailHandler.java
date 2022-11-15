package com.marzag.cameraclient.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class MailHandler {
    FileReader propertiesReader;
    private Properties properties = new Properties();
    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    /**
     * TODO
     * Dodać logger który będzie logował gdzieś wyniki operacji,
     * lista odbiorców pobierana z bazy,
     * credentiale do maila wysylajacego pobierane z bazy
     * dodawanie obrazka do bazy danych
     **/
    public MailHandler() {


        try {
            propertiesReader = new FileReader(rootPath + "application.properties");
            properties.load(propertiesReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.login"), properties.getProperty("mail.password"));
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.login")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("mail.recipient")));
            message.setSubject("Zdjecie z dnia " + new SimpleDateFormat("d.M.Y").format(Calendar.getInstance().getTime()));
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart attachment = new MimeBodyPart();
            MimeBodyPart messageText = new MimeBodyPart();
            try {
                File file = new File(rootPath + "zdjecie.jpg");//placeholder tu bedzie zdjecie zrobione kamera
                attachment.attachFile(file);
                messageText.setText("To zdjecie zostalo zrobione o godzinie " + new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()) + ".");
                mimeMultipart.addBodyPart(attachment);
                mimeMultipart.addBodyPart(messageText);
            } catch (IOException e) {
                e.printStackTrace();
            }
            message.setContent(mimeMultipart);
            System.out.println("Sending...");//temporary
            Transport.send(message);
            System.out.println("Sent succesfully");//temporary
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
