package tim9.realEstate.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * This class enables application to send mails
 * by using Java Mail Service
 */
@Component
public class MailUtil {
	
	@Autowired
	JavaMailSender  javaMailService;
	
	/**
	 * This methods sets email, subject and text and
	 * then sends mail with this data
	 * @param email mail to
	 * @param subject subject of an email
	 * @param text mail text
	 */
	public void sendMail(String email, String subject, String text){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailService.send(mailMessage);
	}

}
