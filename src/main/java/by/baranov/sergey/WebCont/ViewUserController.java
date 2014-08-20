package by.baranov.sergey.WebCont;

import by.baranov.sergey.Entity.User;
import by.baranov.sergey.Service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 */
@Controller
public class ViewUserController {
    @Autowired
    private UserService userService;

    private static final Log LOG = LogFactory.getLog(ViewUserController.class);

    @RequestMapping(value = "/ViewUser.do")
    public final String update(HttpServletRequest request, ModelMap model)
            throws java.lang.Exception {
        LOG.debug("View User controller");

        if (LOG.isDebugEnabled()) {
            LOG.debug("user " + request.getSession().getAttribute("UserName") + " look at user with id = " +
                    "" + request.getParameter("IdUser"));
        }

        User user = userService.getProfileById(Long.parseLong(request.getParameter("IdUser")));
        model.addAttribute("User", user);
        model.addAttribute("Back", ("Home.do?page=" + request.getParameter("page")));
        return "ViewUser";

    }

}
