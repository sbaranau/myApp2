package by.baranov.sergey.Email;

/**
 *TODO
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private MailSender mailSender;

    /**
     * send very simple message without attachment
     *
     * @param from
     * @param to
     * @param subject
     * @param msg
     */
    public void sendMail(String from, String to, String subject, String msg) {

        LOG.debug("sent mail realization");
        SimpleMailMessage message = new SimpleMailMessage();

        if(LOG.isDebugEnabled()){
            LOG.debug("sent mail from {} to {}",from,to);
        }

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }

}