package publishing.service;

import publishing.model.Document;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendViaEmailService  {

    private Document document;
    private String username;
    private String password;
    private String recipient;


    public SendViaEmailService(Document document) {
        GetUserDataService getUserDataService = new GetUserDataService("C:\\Users\\Stefii\\Desktop\\Year_4\\CEBP\\CEBP-Project-1\\email_data.txt");
        Hashtable<String, String> data = (Hashtable<String, String>) getUserDataService.runService();

        this.document = document;
        this.username = data.get("username");
        this.password = data.get("password");
        this.recipient = data.get("recipient");
    }


    public void runService() {

        try {
            Properties props = this.setProperties();
            Session session = this.createSession(props);
            Message message = this.buildSimpleMessage(session);

            Transport.send(message);
            System.out.println("Message sent.");

            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
    }


    private Properties setProperties(){
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        return props;
    }


    private Session createSession(Properties properties) {
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        return session;
    }


    private Message buildSimpleMessage(Session session) throws MessagingException {
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(this.username));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(this.recipient,false));

        msg.setSubject("[WorkflowSimulator] Documentation mail");
        msg.setText(this.document.getDocument());

        msg.setSentDate(new Date());

        return msg;
    }
}
