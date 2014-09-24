package by.baranov.sergey.WebCont;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.Comment;
import by.baranov.sergey.Service.AdvService;
import by.baranov.sergey.Service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        Adv adv = advService.viewAdv(idUser, Integer.parseInt(request.getParameter("AdId")));

        if (LOG.isDebugEnabled()) {
            LOG.debug("user view adv with id " + adv.getIdAdv());
        }

        model.addAttribute("Adv",adv);
        Comment comment = new Comment(idUser.intValue(), adv.getIdAdv());
        model.addAttribute("Comment",comment);

        if (!(adv.getPicture() == null)) {

            String allPicture = adv.getPicture().substring(1);  // cut '#' at beginning
            Pattern pattern = Pattern.compile("#");
            String[] pictures = pattern.split(allPicture);      // get all pics names
            model.addAttribute("pics", pictures);
        }
        return "ViewAd";
    }



    @RequestMapping(value = "/addComment.do", method = RequestMethod.POST)
    public final String createNewAdv(Comment comment, HttpServletRequest request, Model model)
            throws java.lang.Exception {
        LOG.debug("Add Comment controller");
        HttpSession session = request.getSession();
        int idUser;
        int idAdv;

        if (LOG.isDebugEnabled()) {
            LOG.debug("username is " + session.getAttribute("UserName"));
        }

        try {                                                                      //check if user registreted or guest
            idUser = Integer.parseInt(String.valueOf(session.getAttribute("UserId")));
        } catch (NumberFormatException e) {
            idUser = 0;
        }
        try {
            idAdv = Integer.parseInt(request.getParameter("AdId"));
        } catch (NumberFormatException e) {
            idAdv = 0;
        }
        comment.setUserId(idUser);
        comment.setAdvId(idAdv);

        int comId = commentService.addComment(comment);
        if (comId == 0) {
            return "Error";
        }
        model.addAttribute("Back", ("Home.do?page=" + request.getParameter("page")));
        Adv adv = advService.viewAdv((long)idUser, idAdv);

        if (LOG.isDebugEnabled()) {
            LOG.debug("user view adv with id " + adv.getIdAdv());
        }

        model.addAttribute("Adv",adv);
        Comment commentEmpty = new Comment(idUser, adv.getIdAdv());
        model.addAttribute("Comment",commentEmpty);

        if (!(adv.getPicture() == null)) {

            String allPicture = adv.getPicture().substring(1);  // cut '#' at beginning
            Pattern pattern = Pattern.compile("#");
            String[] pictures = pattern.split(allPicture);      // get all pics names
            model.addAttribute("pics", pictures);
        }
        return "ViewAd";
    }
}
