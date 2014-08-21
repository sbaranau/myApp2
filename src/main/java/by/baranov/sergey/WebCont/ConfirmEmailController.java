package by.baranov.sergey.WebCont;

import by.baranov.sergey.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sbaranau on 8/18/14.
 */
@Controller
public class ConfirmEmailController {
    @Autowired
    UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
@RequestMapping(value = "/confirm.do")
    public String confirmEmail(HttpServletRequest request , Model model){
    Integer id = (Integer)request.getAttribute("id");
    LOG.debug("Confirm user with id=" + id);
    userService.confirmEmail(id);
    model.addAttribute("action", "confirm email. Now you can use it for login");
    return "Successfully";
}
}
