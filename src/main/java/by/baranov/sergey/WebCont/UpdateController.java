package by.baranov.sergey.WebCont;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Service.AdvService;
import by.baranov.sergey.Service.UserFileService;
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
import java.util.regex.Pattern;

/**
 * TODO
 */
@Controller
public class UpdateController {
    @Autowired
    AdvService advService;

    @Autowired
    UserFileService userFileService;

    Adv adv;
    String[] pictures;

    private static final Logger LOG = LoggerFactory.getLogger(UpdateController.class);

    @RequestMapping(value = "/UpdateAd.do", method = RequestMethod.GET)
    public final String update(HttpServletRequest request, ModelMap model)
            throws java.lang.Exception {
        LOG.debug("View controller");
        HttpSession session = request.getSession();
        Long idUser;
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("UserId = " + session.getAttribute("UserId"));
            }
            idUser = Long.parseLong(String.valueOf(session.getAttribute("UserId")));
        } catch (NumberFormatException e) {
            idUser = 0L;
        }
        model.addAttribute("Back", ("Home.do?page=" + request.getParameter("page")));
        try {
            adv = advService.viewAdv(idUser, Long.parseLong(request.getParameter("AdId")));
        } catch (NullPointerException exp) {
            return "Error";
        }
        model.addAttribute("adv", adv);
        session.setAttribute("AdId", adv.getIdAdv());

        if (!(adv.getPicture() == null)) {

            String allPicture = adv.getPicture().substring(1);

            Pattern pattern = Pattern.compile("#");
            pictures = pattern.split(allPicture);
            model.addAttribute("pics", pictures);
        }

        return "UpdateAd";
    }

    @RequestMapping(value = "/UpdateAd.do", method = RequestMethod.POST)
    public final String updateDo(@Valid Adv adv, BindingResult result, HttpServletRequest request, Model model)
            throws java.lang.Exception {
        LOG.debug("Update do controller");
        if (!result.hasErrors()) {
            String allPicture;
            try {
                adv.setViews(this.adv.getViews());
                adv.setDate(this.adv.getDate());
                adv.setIdAdv(this.adv.getIdAdv());
                allPicture = this.adv.getPicture();
                adv.setUser(this.adv.getUser());
                adv.setActivity(adv.getActivity());
            } catch (NullPointerException e) {
                return "Error";
            }

            try {
                for (String checkboxSelected : request.getParameterValues("picForDelete")) {

                    Pattern pattern = Pattern.compile("#" + checkboxSelected);         //if user delete picture
                    pictures = pattern.split(allPicture);                              //delete it's name from adv.picture

                    if (pictures.length == 1) {
                        allPicture = pictures[0];
                    } else {
                        if (pictures.length == 2) {
                            allPicture = pictures[0].concat(pictures[1]);
                        } else {
                            allPicture = null;
                        }
                    }

                    userFileService.deleteFile(checkboxSelected, this.adv);

                    LOG.debug("delete picture: " + checkboxSelected);
                }
            } catch (NullPointerException exp) {
                LOG.debug("no adv or pic to delete");
            }

            adv.setPicture(allPicture);

            model.addAttribute("Author", this.adv.getUser().getUsername());
            model.addAttribute("Back", ("Home.do?page=" + request.getParameter("page")));
            model.addAttribute("pics", allPicture);
            model.addAttribute("page", request.getParameter("page"));
            HttpSession session = request.getSession();


            try {
                advService.updateAdv(adv);

                if (request.getParameter("loadPhoto").equals("loadPhoto")) {

                    model.addAttribute(new UploadItem());
                    LOG.debug("Photo will be loaded!");
                    return "LoadPhoto";
                }

            } catch (NullPointerException er) {          //if upload photo not checked
                model.addAttribute("action", "update Adv");
                session.removeAttribute("AdId");
                return "Successfully";
            } catch (Exception e) {                 //if problem in UserDao
                e.printStackTrace();
                LOG.warn("Adv doesn't change");
                model.addAttribute("error", "Your Adv doesn't change!!!");
                return "UpdateAd";
            }
        }
        return "UpdateAd";
    }


    @RequestMapping(value = "/UpdateAd/delete.do")
    public final String DeleteDo(HttpServletRequest request, Model model)
            throws java.lang.Exception {

        HttpSession session = request.getSession();
        LOG.debug("Delete do controller");

        if (advService.delAdv(this.adv)) {

            userFileService.deleteFile("", this.adv);
            model.addAttribute("action", "create adv");
            model.addAttribute("page", request.getParameter("page"));
            session.removeAttribute("AdId");
            return "redirect:../Successfully.jsp";
        }

        model.addAttribute("error", "Your Adv doesn't delete!!!");
        model.addAttribute("page", request.getParameter("page"));
        return "UpdateAd";
    }
}
