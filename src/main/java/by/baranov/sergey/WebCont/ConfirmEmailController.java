package by.baranov.sergey.WebCont;

import by.baranov.sergey.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sbaranau on 8/18/14.
 */
@Controller
public class ConfirmEmailController {
    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/confirm.do", method = RequestMethod.GET)
    public String confirmEmail( Model model, HttpServletRequest request){
    String id = request.getParameter("id");
    LOG.debug("Confirm user with id=" + id);
    if (userService.confirmEmail(id)) {
        model.addAttribute("action", "confirm email. Now you can use it for login");
        return "Successfully";
    } else {
        return "Error";
    }


}
}
