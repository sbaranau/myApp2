package by.baranov.sergey.Email;

/**
 *TODO
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;
    /**
     * send very simple message without attachment
     *
     * @param from
     * @param to
     * @param subject
     * @param msg
     */
    public void sendMail(String from, String to, String subject, String msg) throws MessagingException {

        LOG.debug("sent mail realization");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if(LOG.isDebugEnabled()){
            LOG.debug("sent mail from {} to {}",from,to);
        }

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(msg,true);
        mailSender.send(message);
    }

}