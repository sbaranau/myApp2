package by.baranov.sergey.WebCont;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Service.AdvService;
import by.baranov.sergey.Service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 * TODO
 */
@Controller
public class ViewAdvController {
    @Autowired
    private AdvService advService;

    @Autowired
    private CommentService commentService;

    private static final Logger LOG = LoggerFactory.getLogger(ViewAdvController.class);

    @RequestMapping(value = "/ViewAd.do")
    public final String viewAdvForm(HttpServletRequest request, HttpServletResponse response, Model model)
            throws java.lang.Exception {
        LOG.debug("View controller");
        HttpSession session = request.getSession();
        Long idUser;

        if (LOG.isDebugEnabled()) {
            LOG.debug("username is " + session.getAttribute("UserName"));
        }

        try {                                                                      //check if user registreted or guest
            idUser = Long.parseLong(String.valueOf(session.getAttribute("UserId")));
        } catch (NumberFormatException e) {
            idUser = 0L;
        }

        model.addAttribute("Back", ("Home.do?page=" + request.getParameter("page")));
        Adv adv = advService.viewAdv(idUser, Long.parseLong(request.getParameter("AdId")));

        if (LOG.isDebugEnabled()) {
            LOG.debug("user view adv with id " + adv.getIdAdv());
        }

        model.addAttribute("Activity", adv.getActivity());
        model.addAttribute("Title", adv.getTitle());
        model.addAttribute("Text", adv.getText());
        model.addAttribute("Views", adv.getViews());
        model.addAttribute("Author", adv.getUser().getUsername());
        model.addAttribute("AdId", adv.getIdAdv());
        model.addAttribute("CommentCount", adv.getCommentsCount());
        model.addAttribute("Comments",commentService.getComments(adv.getIdAdv()));

        if (!(adv.getPicture() == null)) {

            String allPicture = adv.getPicture().substring(1);  // cut '#' at beginning
            Pattern pattern = Pattern.compile("#");
            String[] pictures = pattern.split(allPicture);      // get all pics names
            model.addAttribute("pics", pictures);
        }
        return "ViewAd";


    }
}
