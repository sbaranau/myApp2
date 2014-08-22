package by.baranov.sergey.Entity;

import javax.persistence.*;

/**
 * Created by sbaranau on 8/22/14.
 */
@Entity
@Table(name = "comment")
public class Comment {

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


}
