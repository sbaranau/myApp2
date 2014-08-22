package by.baranov.sergey.Email;

/**
 *TODO
 */


import by.baranov.sergey.Entity.User;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service("mailService")
public class MailServiceImpl implements MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;
    private static final String  adminMailAddress = "bsvka83@gmail.com2";
    private static String templateLocation = "";
    private static String subject = "";


    /**
     * send  message
     *
     * @param
     */
    public void sendMail(final String mailFrom, final String mailTo, boolean isRegistration, final User user) throws MessagingException {

        if (isRegistration) {
            subject="Registration";
            templateLocation ="mailTemplateR.html";
        } else {
            subject="User's data changed";
            templateLocation ="mailTemplateC.html";
        }
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo("bsvka@mail.ru");
                message.setFrom(mailFrom);
                Map<String, Object> model = new HashMap<String,Object>();
                model.put("user", user);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, templateLocation,"UTF-8", model); //TODO replace in resources
                message.setText(text, true);
                message.setSubject(subject);
            }
        };

        mailSender.send(preparator);
// simple message to Admin
        LOG.debug("sent mail realization");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if(LOG.isDebugEnabled()){
            LOG.debug("sent mail from {} to {}",mailFrom, mailTo);
        }

        String msg = "New user e-mail:" + user.getEmail() + " <br/>"
                + " Name:" + user.getUsername() + " <br/>"
                + "Password -" + user.getPassword();

        helper.setFrom(mailFrom);
        helper.setTo(adminMailAddress);
        helper.setSubject("New user registered");
        helper.setText(msg,true);
        mailSender.send(message);
    }

}