package by.baranov.sergey.Email;


import javax.mail.MessagingException;

/**
 *TODO
 */
public interface MailService {

    void sendMail(String from, String to, String subject, String msg) throws MessagingException;
}
