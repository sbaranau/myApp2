package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.UserFile;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *TODO
 */

@Repository
public class FileDaoImplSQL implements FileDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOG = LoggerFactory.getLogger(FileDaoImplSQL.class);


    /**
     * upload file in BD
     *
     * @param userFile - file to upload
     * @return file id in DB
     */
    @Transactional
    @Override
    public Long upload(UserFile userFile, String userName) {
        LOG.debug("Uploading file in DB");
        return (Long) sessionFactory.getCurrentSession().save(userFile);
    }

    /**
     * get file from DB using file's name and adv's id
     * can get any type of file
     *
     * @param advId    - adv's id
     * @param fileName - file's name
     * @return file in UserFile class
     */
    @Transactional
    @Override
    public UserFile download(String UserName, Long advId, String fileName) {

        LOG.debug("Downloading file from DB");
        Adv adv = (Adv) sessionFactory.getCurrentSession().get(Adv.class, advId);
        return (UserFile) sessionFactory.getCurrentSession().createCriteria(UserFile.class).add(Restrictions.eq("adv", adv)).
                add(Restrictions.eq("filename", fileName)).list().get(0);
    }

    /**
     * do nothing
     * @param filenameToDelete
     * @param adv
     * @return  always true because file deleted by BD
     */
    @Override
    public boolean deleteFile(String filenameToDelete, Adv adv) {
        return true;
    }
}
