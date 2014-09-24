package by.baranov.sergey.WebCont;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Service.AdvService;
import by.baranov.sergey.Service.UserFileService;
import by.baranov.sergey.UploadItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * TODO
 */
@Controller
public class UploadController {

    @Autowired
    private AdvService advService;

    @Autowired
    private UserFileService userFileService;

    private static final int FILE_MAX_SIZE = 1048576;    //set file max size in byte

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/LoadPhoto.do/{id}", method = RequestMethod.POST)
    public String showForm(UploadItem uploadItem, ModelMap model, HttpServletRequest request) throws IOException {
        LOG.debug("UploadPhoto Controller");
        HttpSession session = request.getSession();

        try {

            MultipartFile file = uploadItem.getFileData();
            LOG.debug("file name = " + file.getName() + " type = " + file.getContentType());

            if (!file.getContentType().matches("image/(\\w+)")) {                                  // check for filetype
                LOG.warn("file is not image");                                                    // if del this verification
                model.addAttribute("UploadError", "not a picture!!!!");                           // user can upload any file
                model.addAttribute("pics", request.getParameter("pics"));
                return "LoadPhoto";
            }

            int fileSize = (int) file.getSize();
            String uploadedFileNames = null;

            try {                                                                 // get names of files what were uploaded before
                uploadedFileNames = request.getParameter("pics");
                if (LOG.isDebugEnabled()) {
                    LOG.debug("uploaded files:  " + uploadedFileNames);
                }
            } catch (Exception ex) {
                LOG.debug("No files uploaded");
            }

            if (file.getSize() > 0) {

                if (fileSize > FILE_MAX_SIZE) {                                         // size verification

                    if (LOG.isDebugEnabled()) {
                        LOG.debug("File Size bigger then FILE_MAX_SIZE and = " + fileSize);
                    }

                    model.addAttribute("UploadError", "File Size bigger then :" + FILE_MAX_SIZE / 1024 + "kb");
                    model.addAttribute("pics", request.getParameter("pics"));
                    return "LoadPhoto";
                }

                if (LOG.isDebugEnabled()) {
                    LOG.debug("File size: " + fileSize);
                }

                if (userFileService.uploadFile(file, String.valueOf(session.getAttribute("UserName")),     //  upload file
                        String.valueOf(session.getAttribute("AdId")))) {

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(uploadedFileNames);
                    stringBuilder.append("#");
                    model.addAttribute("pics", stringBuilder.append(file.getOriginalFilename()));
                    model.addAttribute("picName", file.getOriginalFilename());
                } else {
                    model.addAttribute("pics", request.getParameter("pics"));
                    model.addAttribute("UploadError", "Error uploading file!");
                }
            } else {
                LOG.debug("pic size = 0");
                model.addAttribute("pics", request.getParameter("pics"));
                model.addAttribute("UploadError", "Error: uploading file bellow zero");
            }

            LOG.debug(file.getOriginalFilename());
        } catch (Exception e) {
            LOG.debug("Catch uploading");
            e.printStackTrace();
            model.addAttribute("UploadError", " Terrible Error uploading file");
            model.addAttribute("pics", request.getParameter("pics"));
            return "LoadPhoto";
        }

        return "LoadPhoto";
    }


    /**
     * then user upload all files save there names in DB
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/LoadPhoto/ready.do", method = RequestMethod.POST)
    public String endUpload(ModelMap model, HttpServletRequest request) {
        LOG.debug("End of uploading files");
        HttpSession session = request.getSession();
        int advId = Integer.parseInt(String.valueOf(session.getAttribute("AdId")));

        if (!"".equals(request.getParameter("pics")) && (request.getParameter("pics") != null)) {    //if some file were upload
            Adv adv = advService.viewAdv(Long.parseLong(String.valueOf(session.getAttribute("UserId"))), advId);                                                   //save there names
            adv.setPicture(request.getParameter("pics"));
            advService.updateAdv(adv);
            LOG.debug("some files were upload");
        }

        model.addAttribute("action", "create adv");
        model.addAttribute("page", request.getParameter("page"));
        session.removeAttribute("AdId");
        return "redirect:../Successfully.jsp";

    }
}
