package by.baranov.sergey.WebCont;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.User;
import by.baranov.sergey.Service.AdvService;
import by.baranov.sergey.UploadItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Formatter;

/**
 * TODO
 */
@Controller
public class AddController {

    @Autowired
    private AdvService advService;

    private static final Logger LOG = LoggerFactory.getLogger(AddController.class);

    @RequestMapping(value = "/Create.do", method = RequestMethod.GET)
    public String showCreateForm(ModelMap model) {
        LOG.debug("Show form create controller");
        model.addAttribute("adv", new Adv());

        return "Create";
    }

    /**
     * create new adv (do verification, create adv as Adv, redirect at load photo if need )
     *
     * @param adv
     * @param result
     * @param request
     * @param model
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/Create.do", method = RequestMethod.POST)
    public final String createNewAdv(@Valid Adv adv, BindingResult result, HttpServletRequest request, Model model)
            throws java.lang.Exception {
        LOG.debug("Create do controller");
        if (!result.hasErrors()) {
            Adv adToAdd = new Adv();
            HttpSession session = request.getSession();

            if (LOG.isDebugEnabled()) {
                LOG.debug("Adv create by " + session.getAttribute("UserName"));
            }

            adToAdd.setUser(new User(Long.parseLong(String.valueOf(session.getAttribute("UserId")))));
            adToAdd.setTitle(adv.getTitle());
            adToAdd.setText(adv.getText());
            adToAdd.setViews(0L);
            adToAdd.setActivity(adv.getActivity());
            Formatter format = new Formatter();
            Calendar calendar = Calendar.getInstance();
            format.format("%ta %tb %td - %tI:%tM %Tp", calendar, calendar, calendar, calendar, calendar, calendar);
            adToAdd.setDate(format.toString());
            adToAdd.setPicture(null);                    //as default - no attachment

            Long advId = advService.addAdv(adToAdd);   //get error from method add
            if (advId == 0) {
                LOG.warn("Avd doesn't create!!");
                model.addAttribute("error", "true");
                return "Create";
            } else {

                if (request.getParameter("photo").equals("yes")) {        //if user check   "I want to load photo"
                    LOG.warn("user want to upload file");
                    session.setAttribute("AdId", advId);                  // way to sent parameter to other pages (!!!not one)
                    model.addAttribute(new UploadItem());
                    return "LoadPhoto";
                } else {
                    LOG.warn("adv create successfully");
                    model.addAttribute("action", "create adv");
                    return "Successfully";
                }
            }
        } else {                                          //if validation fail
            LOG.warn("validation fail!!!!");
            return "Create";
        }

    }

}
