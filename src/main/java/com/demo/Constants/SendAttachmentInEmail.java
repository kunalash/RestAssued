package com.demo.Constants;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class SendAttachmentInEmail {

    static String body = """
            Hi,
                        
            Got to know that you are hiring. I am looking for a “Test Automation” position. Below are my details,
                        
            • Current Role: Automation Engineer\s
                        
            • Current Location:jaipur Rajasthan\s
                        
            • Experience: 4 years 6 months
                        
            • Skills:java, Selenium, TestNg, Cucumber, Postgres, Jenkins\s
                        
            • Current CTC: 1284000 LPA
                        
            Attached is my resume for reference. Please share it with your colleagues. Do let me know in case you
            need any further information.
                        
            Thanks & regards,
            Kunal Ash
            Contact:7976926155
            """;

    static String subject = "Automation test engineer | 4.5 years";
    public static void main(String[] args) throws IOException {
        final Set<String> receivers = extractEmailsFromPdfFile("C:\\Users\\faber\\Downloads\\j\\3-6 Years - 07-Aug.pdf");
        System.out.println(receivers);
//        final Set<String> receivers = extractEmailsFromText(s1);
        System.out.println(receivers);

//               receivers.forEach(s ->sendEmail(s));
    }

    private static void sendEmail(String receiver){
        // Recipient's email ID needs to be mentioned.
        String to = receiver;

        // Sender's email ID needs to be mentioned
        String from = "kunalash.it17@gmail.com";

        final String username = "kunalash.it17@gmail.com";//change accordingly
        final String password = "vodulajagklxfwel";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";


        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(body);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\faber\\Downloads\\resume\\Kunal_Ash_Resume_.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Kunal_Ash_Resume.pdf");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully to: "+to);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



    private static String convertPDFToTxt(String filePath) throws IOException {
            // Load the PDF document
            File file = new File(filePath);
            PDDocument document = PDDocument.load(file);

            // Create a PDFTextStripper object to extract text from the PDF
            PDFTextStripper textStripper = new PDFTextStripper();

            // Extract text from the entire document
            String text = textStripper.getText(document);
            System.out.println(text);

            // Close the document
            document.close();
            return text;
    }

    private static Set<String> extractEmailsFromPdfFile(String filePath) throws IOException {
        Set<String> emails = new HashSet<>();
        String text = convertPDFToTxt(filePath);

        // Regular expression pattern to match email addresses
        String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Find and print all email addresses
        while (matcher.find()) {
            String email = matcher.group();
           emails.add(email);
        }
        return emails;
    }

    private static Set<String> extractEmailsFromText(String text) {
        Set<String> emails = new HashSet<>();

        // Regular expression pattern to match email addresses
        String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Find and print all email addresses
        while (matcher.find()) {
            String email = matcher.group();
            emails.add(email);
        }
        return emails;
    }

}
