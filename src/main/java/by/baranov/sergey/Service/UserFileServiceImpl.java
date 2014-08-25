package by.baranov.sergey.Service;

import by.baranov.sergey.DAO.FileDao;
import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.UserFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * TODO
 */
@Service
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    @Qualifier("fileDaoImplSQL")
    private FileDao fileDao;

    @Autowired
    private AdvService advService;

    private static final Logger LOG = LoggerFactory.getLogger(UserFileServiceImpl.class);

    /**
     * upload user's files in folder on filesystem, full path: userfiles    /  user name  /    adv's ad
     * (doesn't change)
     *
     * @param file
     * @param userName
     * @param adId
     * @return result of uploading
     * @throws IOException
     */
    @Override
    public boolean uploadFile(MultipartFile file, String userName, String adId) throws IOException {           // upload any type of file
        // with any size
        LOG.debug("upload file: File service");                                                            // in user directory
        UserFile userFile = new UserFile();
        userFile.setFile(file.getBytes());
        userFile.setFilename(file.getOriginalFilename());
        userFile.setFiletype(file.getContentType());
        userFile.setAdv(new Adv(Long.parseLong(adId)));

        return (fileDao.upload(userFile, userName) != 0);

    }


    /**
     * delete file or directory: if file name is empty - it's means that we want to delete directory
     *
     * @param filenameToDelete
     * @param adv
     * @return result of deleting
     */
    public boolean deleteFile(String filenameToDelete, Adv adv) {
        LOG.debug("delete file: File service");
        return fileDao.deleteFile(filenameToDelete, adv);
    }


    /**
     * get file from file system and put in byte array
     *
     * @param userName
     * @param adId
     * @param pictureName
     * @return file as byte array
     * @throws IOException
     */
    public byte[] getFile(String userName, String adId, String pictureName) throws IOException {
        LOG.debug("download file: File service");
        return fileDao.download(userName, Long.parseLong(adId), pictureName).getFile();
    }
}


