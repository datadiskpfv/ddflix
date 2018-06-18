package uk.co.datadisk.ddflix.services.admin;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.services.MailService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Service
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public SimpleMailMessage constructEmail(String subject, String body, User user) {
        System.out.println("constructEmail method......");
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("support@example.com");
        return email;
    }

    public SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        System.out.println("constructResetTokenEmail..........");
        final String url = contextPath + "/user/updatePassword?id=" + user.getId() + "&token=" + token;
        System.out.println("constructResetTokenEmail URL: " + url);
        //final String message = messages.getMessage("message.resetPassword", null, locale);
        //System.out.println("constructResetTokenEmail message: " + message);
        return constructEmail("Reset Password", url, user);
    }

    public String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @Override
    public void sendMail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
