package uk.co.datadisk.ddflix.services;

import org.springframework.mail.SimpleMailMessage;
import uk.co.datadisk.ddflix.entities.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public interface MailService {

    SimpleMailMessage constructEmail(String subject, String body, User user);
    SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user);
    String getAppUrl(HttpServletRequest request);
    void sendMail(SimpleMailMessage email);
}
