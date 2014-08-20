package by.baranov.sergey.WebCont;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 */
@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

    @RequestMapping(value = "/Login.do", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("Back", ("Home.do?page=" + request.getParameter("page")));
        LOG.debug("User login");
        return "Login";
    }

    @RequestMapping(value = "/loginfailed.do", method = RequestMethod.GET)
    public String loginError(Model model, HttpServletRequest request) {
        model.addAttribute("error", "true");
        LOG.warn("Login failed");
        model.addAttribute("Back", ("Home.do?page=" + request.getParameter("page")));
        return "Login";
    }

    @RequestMapping(value = "/Logout.do", method = RequestMethod.GET)
    public String logout() {
        LOG.debug("user loggout");
        return "Home.do";

    }
}
