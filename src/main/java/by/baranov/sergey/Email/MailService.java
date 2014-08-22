package by.baranov.sergey.Email;


import by.baranov.sergey.Entity.User;

import javax.mail.MessagingException;

/**
 *TODO
 */
public interface MailService {

    void sendMail(String from, String to, boolean isLogin, User user) throws MessagingException;
}
