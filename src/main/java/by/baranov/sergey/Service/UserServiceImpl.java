package by.baranov.sergey.Service;

import by.baranov.sergey.DAO.UserDao;
import by.baranov.sergey.Entity.User;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);


    @Transactional
    public Long add(String login, String password, String email, String avatar, byte[] picture, String role) {
        LOG.debug("add user. User Service");
        return userDao.add(login, password, email, avatar, picture, role);
    }

    @Transactional
    public User getProfileById(Long idUser) {
        LOG.debug("get user's profile by id. User Service");
        return userDao.findById(idUser);
    }

    @Transactional
    public User getProfileByName(String username) {
        LOG.debug("get user's profile by name. User Service");
        return userDao.findByName(username);
    }

    @Transactional
    public boolean updateProfile(User user) {
        LOG.debug("update user's profile. User Service");
        return userDao.update(user);
    }

    @Override
    public boolean confirmEmail(int id) {
        LOG.debug("confirm email. User Service");
        userDao.updateEnabledById(id);
        return false;
    }

}