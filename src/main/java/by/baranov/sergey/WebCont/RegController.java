package by.baranov.sergey.WebCont;


import by.baranov.sergey.Email.MailService;
import by.baranov.sergey.Service.UserService;
import by.baranov.sergey.UploadItem;
import by.baranov.sergey.UserVar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * TODO
 */
@Controller
public class RegController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;


    @Autowired
    @Qualifier("org.springframework.security.authenticationManager")
    private AuthenticationManager authenticationManager;

    private static final Log LOG = LogFactory.getLog(RegController.class);


    @RequestMapping(value = "/Reg.do", method = RequestMethod.GET)
    public String displayForm(ModelMap modelMap) {
        LOG.debug("Reg method GET");
        modelMap.addAttribute("userVar", new UserVar());
        modelMap.addAttribute(new UploadItem());
        return "Reg";
    }


    @RequestMapping(value = "/Reg.do", method = RequestMethod.POST)
    public final String login(@Valid UserVar userVar, BindingResult result, ModelMap model, HttpServletRequest request)
            throws java.lang.Exception {
        LOG.debug("Reg method Post");

        if (!result.hasErrors()) {

            String avatar = null;

            if (!userVar.getPassword().equals(userVar.getComPassword())) {          //check for equals passwords
                model.addAttribute("baseerror", "passwords not equals!!!!");
                return "Reg";
            }

            if (!userVar.getEmail().equals(userVar.getComEmail())) {          // check for equals Email
                model.addAttribute("baseerror", "emails not equals!!!!");
                return "Reg";
            }

            try {                                                              // check if user already exist
                userService.getProfileByName(userVar.getName());
                model.addAttribute("baseerror", "User with such name already  exist!!!!");
                return "Reg";
            } catch (IndexOutOfBoundsException exp) {

                MultipartFile file;

                // load avatar +++++++++++++++++++++++++++++++++++++++++++++++++++++++=
                try {
                    file = userVar.getFileData();

                    if (file.getSize() > 0) {
                        if (file.getSize() > 100000) {                                        // if file too big
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("File Size:::" + file.getSize());
                            }
                            model.addAttribute("baseerror", "File Size to big:" + file.getSize() + "kb");
                            return "Reg";
                        }
                        avatar = file.getOriginalFilename();
                    }

                    LOG.debug(file.getOriginalFilename());
                } catch (Exception e) {
                    model.addAttribute("baseerror", "Error uploading file");
                    return "Reg";
                }

                Long userId = userService.add(userVar.getName(), userVar.getPassword(), userVar.getEmail(), avatar, file.getBytes(), "ROLE_USER");

                //if successful creating user
                if (userId != 0) {

                    //Send Email+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    try {
                        mailService.sendMail("myAppadministrator", userVar.getEmail(), "Registration information", "Congratulations!!!!! \n\n " +
                                "Hello dear" + userVar.getName() + "\n\n Your registration at myApp successful! \n " +
                                "Your login - " + userVar.getName() +
                                "\n your password -" + userVar.getPassword() +
                                "\n <a href=\"http://localhost:8080/myApp/confirm.do?id=" + userId +
                                "\">Confirm you e-mail</a>");
                    } catch (MailSendException exception) {
                        LOG.debug("!!!!Error sending letter");
                        exception.printStackTrace();
                    }

                    //Set session attributes+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                    HttpSession session = request.getSession(true);
                    session.setAttribute("UserName", userVar.getName());
                    session.setAttribute("UserId", userId);
                    session.setAttribute("Avatar", avatar);

                    //Set Authentication successful for login controller+++++++++++++++
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            userVar.getName(), userVar.getPassword());
                    token.setDetails(new WebAuthenticationDetails(request));
                    Authentication authenticatedUser = authenticationManager.authenticate(token);

                    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

                    model.addAttribute("action", "registrated.");
                    return "Successfully";

                } else {

                    model.addAttribute("baseerror", "user doesn't create");

                }
            } //end of try/catch find user
        }
        return "Reg";

    }
}
