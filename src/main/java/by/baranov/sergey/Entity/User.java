package by.baranov.sergey.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *TODO
 */
@Entity
@Table(name = "login")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long idUser;

    @Column(name = "username")
    @NotEmpty(message = "shouldn't be empty")
    @Size(min = 3, max = 20, message = "should be from 3 to 20 signs")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "shouldn't be empty")
    @Size(min = 3, max = 20, message = "should be from 3 to 20 signs")
    private String password;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "email")
    private String email;


    @Column(name = "avatar")
    private String avatar;

    @Column(name = "picture")
    private byte[] picture;

/*
    private Set<Comment> comments = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public User() {
    }

    public User(String username) {
        this.username = username;
        this.idUser = null;
    }

    public User(Long id) {
        this.idUser = id;
    }

    public User(String username, String password, Integer enabled, String email, String avatar, byte[] picture) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.avatar = avatar;
        this.picture = picture;
    }

    public User(String username, String password, String email, String avatar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
    }
}
