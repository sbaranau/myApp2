package by.baranov.sergey.Service;

import by.baranov.sergey.Entity.Adv;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *TODO
 */
public interface UserFileService {

    public boolean uploadFile(MultipartFile file, String userName, String adId) throws IOException;

    public boolean deleteFile(String filenameToDelete, Adv adv);

    public byte[] getFile(String userName, String adId, String pictureName) throws IOException;

}
