package poly.util;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MailUtil {
	public static void sendMail(String recipient, String subject, String body)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		String host = "smtp.gmail.com";
		

		final String username = "lsb180126@gmail.com";
		final String password = "dltnqls1201";

		Properties props = System.getProperties();
		
		
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			String un = username;
			String pw = password;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true);
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress("lsb180126@gmail.com"));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		mimeMessage.setSubject(MimeUtility.encodeText(subject,"UTF-8","B"));
		mimeMessage.setContent(body, "text/html; charset=UTF-8");
		Transport.send(mimeMessage);
	}
}