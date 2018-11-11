package helper;

import java.io.IOException;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

public class MailSender {
	
	public static void sendEmail(String recipient, String subject, String message) {
		Email from = new Email("sportloc@heroku.com");
		Email to = new Email(recipient);
		Content content = new Content("text/plain", message);
		Mail mail = new Mail(from, subject, to, content);
		
		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			sg.api(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
