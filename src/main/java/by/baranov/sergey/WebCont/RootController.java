package by.baranov.sergey.WebCont;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sbaranau on 8/18/14.
 */
@Controller
public class RootController {
    private static final Log LOG = LogFactory.getLog(LoginController.class);
    @RequestMapping(value = "/")
    public String root(Model model) {
       LOG.debug("Root.controller");
        return "Home.do";
    }
}
