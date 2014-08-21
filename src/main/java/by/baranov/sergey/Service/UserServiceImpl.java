package by.baranov.sergey.Service;

import by.baranov.sergey.DAO.UserDao;
import by.baranov.sergey.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


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