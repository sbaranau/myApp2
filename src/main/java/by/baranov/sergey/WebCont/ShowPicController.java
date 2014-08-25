package by.baranov.sergey.WebCont;

import by.baranov.sergey.Service.AdvService;
import by.baranov.sergey.Service.UserFileService;
import by.baranov.sergey.Service.UserService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 */
@Controller
public class ShowPicController {

    @Autowired
    private UserFileService userFileService;

    @Autowired
    private AdvService advService;

    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(ShowPicController.class);


    @RequestMapping(value = "/Picture.do", method = RequestMethod.GET)
    public void showPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LOG.debug("Show Pic Controller");
        byte[] fileInByte;
        InputStream inputStream;

        if ("Avatar".equals(request.getParameter("picType"))) {                        //showing avatar

            LOG.debug("User load avatar");

            fileInByte = userService.getProfileByName(request.getParameter("Auther")).getPicture();

        } else {

            LOG.debug("user load adv's image ");
            fileInByte = userFileService.getFile(request.getParameter("Auther"), request.getParameter("AdId"), request.getParameter("picName"));

        }

        inputStream = new BufferedInputStream(new ByteArrayInputStream(fileInByte));
        IOUtils.copy(inputStream, response.getOutputStream());

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
