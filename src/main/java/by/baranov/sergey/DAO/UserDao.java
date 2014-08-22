package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.User;

/**
 *TODO
 */
public interface UserDao {

    public Long add(String login, String password, String email, String avatar, byte[] picture, String role);

    public User findById (Long idUser);

    public User findByName(String username);

    public boolean update(User user);

    public boolean updateEnabledById(Long id);

    public boolean updateRoleById(Long id);
}
