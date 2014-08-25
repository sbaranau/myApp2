package by.baranov.sergey.WebCont;

/**
 *TODO
 */

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.User;
import by.baranov.sergey.Service.AdvService;
import by.baranov.sergey.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Formatter;
import java.util.List;


@Controller
public class AdvViewAllController {

    @Autowired
    private AdvService advService;

    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(AdvViewAllController.class);

    @RequestMapping(value = "/Home.do")
    public final ModelAndView showMainForm(HttpServletRequest request, HttpSession session, Principal principal)
            throws java.lang.Exception {

        LOG.debug("Adv Controller");

        ModelAndView mav = new ModelAndView();
        String userName;
        session = request.getSession(true);

        try {
            userName = principal.getName();                                             // get Username after LoginController
            if ((!(userName == null)) && (session.getAttribute("UserName") == null)) {     //check if user login
                session.setAttribute("UserName", userName);
                User user = userService.getProfileByName(userName);
                session.setAttribute("UserId", user.getIdUser());
                session.setAttribute("Avatar", user.getAvatar());

                if (LOG.isDebugEnabled()) {
                    LOG.debug("User login. User's  name = " + user.getUsername());
                }
            }
        } catch (NullPointerException e) {
            LOG.debug("user is guest");
            userName = null;
        }

        int quantityOfAdvsOnPage;

        mav.setViewName("Home");
        //find quantity of advs
        int totalQuantityOfAdv = advService.getNumbersOfAdvs();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Total quantity of advs = " + totalQuantityOfAdv);
        }
        int numberOfPages;

        //find quantity of advs on page
        if (request.getParameter("countity") != null) {
            quantityOfAdvsOnPage = Integer.parseInt(request.getParameter("countity"));
            session.setAttribute("countity", quantityOfAdvsOnPage);
        } else {
            if (session.getAttribute("countity") != null) {
                quantityOfAdvsOnPage = Integer.parseInt(String.valueOf(session.getAttribute("countity")));
            } else {
                quantityOfAdvsOnPage = 10;                   //  default
            }
        }

        if (quantityOfAdvsOnPage == 2) {              //if "2" show all advs
            quantityOfAdvsOnPage = totalQuantityOfAdv;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("quantity of advs on page = " + quantityOfAdvsOnPage);
        }
        //find number of pages

        if (totalQuantityOfAdv != 0) {
            if (totalQuantityOfAdv <= quantityOfAdvsOnPage) {
                numberOfPages = 1;
            } else {
                numberOfPages = totalQuantityOfAdv / quantityOfAdvsOnPage;
                if (totalQuantityOfAdv % quantityOfAdvsOnPage != 0) {
                    numberOfPages++;
                }
                if (LOG.isDebugEnabled()) {
                    LOG.debug("quantity pages = " + numberOfPages);
                }
            }

            //find last number of adv on page
            int advLastNumber;
            if ((totalQuantityOfAdv % quantityOfAdvsOnPage == 0) && (totalQuantityOfAdv > quantityOfAdvsOnPage)) {
                advLastNumber = quantityOfAdvsOnPage;
            } else {
                advLastNumber = totalQuantityOfAdv % quantityOfAdvsOnPage;
            }

            if (LOG.isDebugEnabled()) {
                LOG.debug("last number of adv on page = " + advLastNumber);
            }

            //get number of page what user want to open
            String pageSt;
            int page;
            try {                                                     //try to get number of page to go
                pageSt = request.getParameter("page");
                page = Integer.parseInt(pageSt);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("page to go = " + pageSt);
                }
            } catch (NumberFormatException e) {                        // if isn't number   (at beginning)
                LOG.debug("No page parameter - first page");
                pageSt = null;
                page = 0;
            }

            //find first number of adv what user want to get
            int pageToGet = 1;
            int nextPage = 0;
            int previousPage = 0;
            Formatter form = new Formatter();
            if ((pageSt == null || page == 0) && (totalQuantityOfAdv > quantityOfAdvsOnPage)) {
                pageToGet = 1;                  //page at the beginning  advs>numberOfAdvsOnPage
                nextPage = 2;
                previousPage = 0;
                form.format(" %s-%s ", 1, quantityOfAdvsOnPage);
            } else {
                if ((pageSt == null || page == 0) && (totalQuantityOfAdv <= quantityOfAdvsOnPage) && (totalQuantityOfAdv > 0)) {
                    pageToGet = 1;                             //page at the beginning advs<numberOfAdvsOnPage
                    nextPage = 0;
                    previousPage = 0;
                    form.format(" %s-%s ", 1, totalQuantityOfAdv);
                }
            }

            if ((page != 0) && (pageSt != null)) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("page number= " + page);
                }
                if (numberOfPages == 1) {
                    pageToGet = 1;                             //only 1 page
                    LOG.debug("number of page=1");
                    nextPage = 0;
                    previousPage = 0;
                    form.format(" %s-%s ", 1, totalQuantityOfAdv);
                } else {

                    if ((page == numberOfPages) && (numberOfPages != 1)) {                                                 //Last page
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("page=" + quantityOfAdvsOnPage * (page - 1) + 1 + " to   " + quantityOfAdvsOnPage * (page - 1) + advLastNumber);
                        }
                        pageToGet = numberOfPages;
                        nextPage = 0;
                        previousPage = numberOfPages - 1;
                        form.format(" %s-%s ", quantityOfAdvsOnPage * (page - 1) + 1, quantityOfAdvsOnPage * (page - 1) + advLastNumber);
                    } else {
                        LOG.debug("page number");                                     //page number "page"
                        pageToGet = page;
                        form.format(" %s-%s ", quantityOfAdvsOnPage * (page - 1) + 1, quantityOfAdvsOnPage * page);
                        nextPage = page + 1;
                        previousPage = page - 1;
                    }
                }
            }

            List<Adv> advs = advService.getAdvs(pageToGet, quantityOfAdvsOnPage);
            mav.addObject("Advs", advs);
            if (previousPage != 0) {
                mav.addObject("PreviousPage", "<a href=\"Home.do?page=" + previousPage + "\"> <<< " + quantityOfAdvsOnPage + "</a>");
            } else {
                mav.addObject("PreviousPage", "");
            }
            if (nextPage != 0) {
                mav.addObject("NextPage", "<a href=\"Home.do?page=" + nextPage + "\"> >>> </a>");
            } else {
                mav.addObject("NextPage", "");
            }

            mav.addObject("CurrentPage", form);
            mav.addObject("countOfPages", numberOfPages);

        } else {
            mav.addObject("CurrentPage", null);

        }
        return mav;
    }

}
