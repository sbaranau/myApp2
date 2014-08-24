package by.baranov.sergey.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sbaranau on 8/22/14.
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable{

    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int commentId;

    @Column(name = "text")
    String commentText;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    User user;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adv")
    Adv adv;

    public Comment() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Adv getAdv() {
        return adv;
    }

    public void setAdv(Adv adv) {
        this.adv = adv;
    }
}
