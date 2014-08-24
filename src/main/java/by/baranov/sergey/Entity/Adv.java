package by.baranov.sergey.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *TODO
 */
@Entity
@Table(name = "adv")
public class Adv implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapps")
    private Long idAdv;
    @Column(name = "date")
    private String date;

    @Column(name = "title")
    @NotEmpty(message = "Title can't be empty")
    private String title;

    @Column(name = "views")
    private Long views;

    @Column(name = "text")
    @NotEmpty(message = "Text can't be empty")
    private String text;

    @Column(name = "activity")
    @NotEmpty(message = "you should select action!")
    private String activity;

    @Column(name = "picture")
    private String picture;

    @Column(name = "comments")
    private int comments;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "members_idmembers")
    private User user;

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adv")
    private List<Comment> comment;

    public Adv() {
    }


    public Adv(String title, String text) {

        this.title = title;
        this.text = text;
    }

    public Adv(Long idAdv, String date, String title, Long views, String text, String activity, String picture, User user, List<Comment> comment) {
        this.idAdv = idAdv;
        this.date = date;
        this.title = title;
        this.views = views;
        this.text = text;
        this.activity = activity;
        this.picture = picture;
        this.user = user;
        this.comment = comment;
    }

    public Adv(String date, String title, Long views, User user, String text, String picture) {
        this.date = date;
        this.title = title;
        this.views = views;
        this.user = user;
        this.text = text;
        this.picture = picture;
    }

    public Adv(Long idAdv) {
        this.idAdv = idAdv;
    }

    public Long getIdAdv() {
        return idAdv;
    }

    public void setIdAdv(Long idAdv) {
        this.idAdv = idAdv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
