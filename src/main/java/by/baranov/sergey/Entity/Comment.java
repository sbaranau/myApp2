package by.baranov.sergey.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sbaranau on 8/22/14.
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable{

    private static final long serialVersionUID = 5924361831551833717L;


    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(name = "text")
    private String commentText;

    @Column(name = "id_user")
    private int userId;

    @Column(name = "id_adv")
    private int advId;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user",insertable=false, updatable=false,
    nullable=false)
    private User user;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adv",insertable=false, updatable=false,
            nullable=false)
    private Adv advs;



    public Comment() {
    }

    public Comment(int user, int adv) {
        this.userId = user;
        this.advId = adv;
    }


    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Adv getAdvs() {
        return advs;
    }

    public void setAdvs(Adv advs) {
        this.advs = advs;
    }
}
