package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.UserFile;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;

/**
 *TODO
 */
@Component
@Repository
public class FileDaoImplFileSystem implements FileDao{

    private static final Logger LOG = LoggerFactory.getLogger(FileDaoImplFileSystem.class);

    /**
     *  upload user's files in folder "userfiles/userName/number of advs"
     * @param userFile
     * @param userName
     * @return
     * @throws IOException
     */
    @Override
    public Long upload(UserFile userFile, String userName) throws IOException {

        LOG.debug("UploadDAO FileSystem");

        File myPath;

        myPath = new File("userfiles" + File.separator + userName + File.separator + userFile.getAdv().getIdAdv());
        String fileName = "userfiles" + File.separatorChar + userName + File.separator +
                userFile.getAdv().getIdAdv() + File.separator + userFile.getFilename();

        if (myPath.mkdirs() || (myPath.exists())) {

            if(LOG.isDebugEnabled()){
                LOG.debug("file save at: " + myPath.getAbsolutePath());
            }

            InputStream inputStream = null;
            OutputStream outputStream = null;
            inputStream = new BufferedInputStream(new ByteArrayInputStream(userFile.getFile()));

            outputStream = new FileOutputStream(fileName);
            outputStream = new BufferedOutputStream(outputStream);

            if(LOG.isDebugEnabled()){
                LOG.debug("fileName: " + userFile.getFilename());
            }

            int fileSize = userFile.getFile().length;
            int readBytes = 0;
            byte[] buffer = new byte[fileSize];

            while ((readBytes = inputStream.read(buffer, 0, fileSize)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }

            outputStream.close();
            inputStream.close();

            return 1L;

        } else {
            LOG.warn("error uploading file");
            return 0L;
        }
       
    }

    /**
     * download file from user's folder
     * @param userName
     * @param advId
     * @param fileName
     * @return
     * @throws IOException
     */
    @Override
    public UserFile download(String userName, Long advId, String fileName) throws IOException {

        LOG.debug("DownloadDAO FileSystem");

        byte[] fileInByte;
        InputStream is;

        is = new FileInputStream("userfiles" + File.separatorChar + userName +         //put file in stream
                File.separatorChar + advId + File.separator + fileName);
        fileInByte = IOUtils.toByteArray(is);                                         //put stream in byte array

        try {
            is.close();
        } catch (IOException e) {
            LOG.warn("Stream doesn't close!");
            e.printStackTrace();
        }
        UserFile userFile = new UserFile();
        userFile.setFilename(fileName);

        userFile.setFile(fileInByte);
        return userFile;
    }

    /**
     * delete file from user's folder
     * @param filenameToDelete
     * @param adv
     * @return
     */
    @Override
    public boolean deleteFile(String filenameToDelete, Adv adv) {

        LOG.debug("delete file DAO");

        File fileToDelete = new File("userfiles" + File.separatorChar +
                adv.getUser().getUsername() + File.separatorChar + adv.getIdAdv() + File.separatorChar + filenameToDelete);

        if (fileToDelete.isDirectory()) {                                                       //deleting if directory

            if(LOG.isDebugEnabled()){
                LOG.debug("Deleting directory: " + fileToDelete.getAbsolutePath());
            }

            File[] listOfFilesToDelete = fileToDelete.listFiles();
            for (File aFilesToDelete : listOfFilesToDelete) {                            //deleting all files in directory
                LOG.debug(aFilesToDelete.getPath());
                if (aFilesToDelete.delete()) {
                    LOG.debug("File deleted");
                } else{
                    LOG.warn("File doesn't delete!");
                }
            }
            if (fileToDelete.delete()) {                                         //delete directory
                LOG.debug("Directory deleted");
                return true;
            } else {
                LOG.warn("Directory doesn't deleted");
                return false;
            }

        } else {
            LOG.debug("delete file in folder");

            if(LOG.isDebugEnabled()){
                LOG.debug("file to delete:  " + fileToDelete);
            }

            if (fileToDelete.exists()) {                                 // deleting if file exist
                LOG.debug("file exist");
                return fileToDelete.delete();
            } else {
                LOG.warn("File doesn't exist");
                return false;
            }
        }

    }
}
