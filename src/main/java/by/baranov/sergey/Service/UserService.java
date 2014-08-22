package by.baranov.sergey.Service;

import by.baranov.sergey.Entity.User;

/**
 *TODO
 */

public interface UserService {

    public Long add(String login, String password, String email, String avatar, byte[] picture, String role);

    public User getProfileById(Long idUser);

    public User getProfileByName(String username);

    public boolean updateProfile(User user);

    public boolean confirmEmail(String id);

}
