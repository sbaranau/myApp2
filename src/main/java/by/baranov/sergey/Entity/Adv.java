package by.baranov.sergey.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *TODO
 */
@Entity
@Table(name = "adv")
public class Adv implements Serializable {

    private int idAdv;
    private String date;
    private String title;
    private Long views;
    private String text;
    private String activity;
    private String picture;
    private int commentsCount;
    private User user;
    private Set<Comment> comments = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapps")
    public int getIdAdv() {
        return this.idAdv;
    }

    public void setIdAdv(int idAdv) {
        this.idAdv = idAdv;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "title")
    @NotEmpty(message = "Title can't be empty")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "views")
    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    @Column(name = "text")
    @NotEmpty(message = "Text can't be empty")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Column(name = "activity")
    @NotEmpty(message = "you should select action!")
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(name = "comments")
    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "members_idmembers")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //todo
    /* @OneToMany(cascade={CascadeType.ALL})
   @JoinColumn(name="id_adv")*/

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "advs")
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Adv() {
    }


    public Adv(String title, String text) {

        this.title = title;
        this.text = text;
    }


    public Adv(String date, String title, Long views, User user, String text, String picture) {
        this.date = date;
        this.title = title;
        this.views = views;
        this.user = user;
        this.text = text;
        this.picture = picture;
    }

    public Adv(int idAdv) {
        this.idAdv = idAdv;
    }
}
