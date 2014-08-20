package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.UserFile;

import java.io.IOException;

/**
 *TODO
 */
public interface FileDao {

    public Long upload(UserFile userFile, String userName) throws IOException;

    public UserFile download(String userName, Long advId, String fileName) throws IOException;

    public boolean deleteFile(String filenameToDelete, Adv adv);
}
