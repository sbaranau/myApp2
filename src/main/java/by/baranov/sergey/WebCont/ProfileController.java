package by.baranov.sergey.WebCont;

import by.baranov.sergey.Email.MailService;
import by.baranov.sergey.Entity.User;
import by.baranov.sergey.Service.AdvService;
import by.baranov.sergey.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * TODO
 */
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    AdvService advService;

    @Autowired
    MailService mailService;

    private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

    User user;

    /**
     * show form with user's information
     *
     * @param request
     * @param model
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/Profile.do", method = RequestMethod.GET)
    public final String showProfileForm(HttpServletRequest request, ModelMap model)
            throws java.lang.Exception {
        LOG.debug("profile controller show form");
        HttpSession session = request.getSession();
        Long idUser;
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("UserId = " + session.getAttribute("UserId"));
            }
            idUser = Long.parseLong(String.valueOf(session.getAttribute("UserId")));
            model.addAttribute("Back", "Home.do?page=" + request.getParameter("page"));
            this.user = userService.getProfileById(idUser);

        } catch (Exception exp) {
            LOG.warn("Error getting user info");
            return "Error";
        }
        model.addAttribute("user", user);

        return "Profile";
    }

    /**
     * change user's info
     *
     * @param user
     * @param request
     * @param model
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/Profile.do", method = RequestMethod.POST)

    public final String updateDo(User user, HttpServletRequest request, Model model)
            throws java.lang.Exception {
        LOG.debug("change profile controller ");
        User userUpdate = this.user;
        HttpSession session = request.getSession();

        try {
            if (!equalNull(user.getUsername())) {
                if ((user.getUsername().length() >= 3) && (user.getUsername().length() <= 20)) {
                    userUpdate.setUsername(user.getUsername());
                } else {
                    model.addAttribute("error", "Username should be from 3 to 20 signs");
                    return "Profile";
                }
            }

            if (!equalNull(user.getEmail())) {
                userUpdate.setEmail(user.getEmail());
            }

            if (!equalNull(request.getParameter("NewPassword"))) {                     //if user input new pass
                                                                                        // user want to change it
                LOG.debug("user want to change pass");

                if (equalNull(request.getParameter("OldPassword"))) {
                    model.addAttribute("error", "old pass isn't valid");
                    return "Profile";
                }

                if (equalNull(request.getParameter("NewPassword2"))) {
                    model.addAttribute("error", "Conform new pass");
                    return "Profile";
                }


                if ((request.getParameter("NewPassword").length() <= 3) || ((request.getParameter("NewPassword").length() >= 20))) {
                    model.addAttribute("error", "Password should be from 3 to 20 signs");
                    return "Profile";
                }

                if (!request.getParameter("NewPassword").equals(request.getParameter("NewPassword2"))) {
                    model.addAttribute("error", "Pass isn't equal");
                    return "Profile";
                }

                if (!request.getParameter("OldPassword").equals(userUpdate.getPassword())) {
                    model.addAttribute("error", "Old pass isn't right");
                    return "Profile";
                } else {

                    userUpdate.setPassword(request.getParameter("NewPassword"));
                }
            }

        } catch (NullPointerException e) {
            return "Error";
        }

        boolean successfulRename = false;

        File oldDir = new File("userfiles" + File.separatorChar + session.getAttribute("UserName"));

        if (oldDir.exists()) {
            LOG.debug("rename dir with user's files");
            File newDir = new File("userfiles" + File.separatorChar + userUpdate.getUsername());
            successfulRename = oldDir.renameTo(newDir);                    //rename directory with users files
        } else {
            successfulRename = true;
        }

        if (successfulRename) {
            LOG.debug("change profile in DB");
            model.addAttribute("action", "update Profile");
            userService.updateProfile(userUpdate);

            try {
                mailService.sendMail("MyAppAdmin", userUpdate.getEmail(), "Change profile", "U change your " +
                        "registration data: \n Your LOGIN:  " + userUpdate.getUsername() + "\n Your PASSWORD:  " +
                        userUpdate.getPassword());
            } catch (MailSendException exception) {
                LOG.warn("Error sending letter with new pass");
                exception.printStackTrace();
            }
            session.setAttribute("UserName", userUpdate.getUsername());

            return "Successfully";
        }

        model.addAttribute("error", "some problem with getting DB");
        return "Profile";
    }

    private boolean equalNull(String parameter) {
        return (parameter.equals("") || parameter.isEmpty());
    }


}


