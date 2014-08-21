package by.baranov.sergey.WebCont;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sbaranau on 8/18/14.
 */
@Controller
public class RootController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/")
    public String root(Model model) {
       LOG.debug("Root.controller");
        return "Home.do";
    }
}
