package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Role;
import by.baranov.sergey.Entity.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *TODO
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);


    /**
     * add new user in DB
     *
     * @param login
     * @param password
     * @param email
     * @param avatar
     * @param picture
     * @return 0 - if error or userId if ok
     */
    public Long add(String login, String password, String email, String avatar, byte[] picture, String role) {

        LOG.debug("Add user dao");
        User user = new User(login, password, 1, email, avatar, picture);
        Long userId = (Long) sessionFactory.getCurrentSession().save(user);
        Long roleId = (Long) sessionFactory.getCurrentSession().save(new Role(userId, role));
        if ((userId != 0) && (roleId != 0)) {
            return userId;
        } else {
            return 0L;
        }
    }


    /**
     * find user in DB using id
     *
     * @param idUser user's id
     * @return user
     */
    public User findById (Long idUser) {
        LOG.debug("FindUser dao by id dao");
        return (User) sessionFactory.getCurrentSession().get(User.class, idUser);
    }


    /**
     * find user in DB using id
     *
     * @param username - user's name
     * @return user
     */
    public User findByName(String username) {
        LOG.debug("FindUser by name dao ");
        return (User) sessionFactory.getCurrentSession().createQuery
                ("from User where username=:username").setString("username", username).list().get(0);
    }


    /**
     * update user profile in DB
     *
     * @param user new user profile
     * @return result
     */
    public boolean update(User user) {
        LOG.debug("UpdateUser dao ");
        try {
            sessionFactory.getCurrentSession().update(user);
        } catch (Exception e) {
            LOG.warn("Update failed");
            return false;
        }
        return true;
    }

    @Override
    public boolean updateEnabledById(int id) {
        LOG.debug("UpdateUserById dao ");
        User user = findById((long)id);
        user.setEnabled(1);
        return update(user);
    }

    @Override
    public boolean updateRoleById(int id) {
        LOG.debug("UpdateRoleById dao ");
        Role role = sessionFactory.getCurrentSession().get()
        sessionFactory.getCurrentSession().
        return false;
    }

}
