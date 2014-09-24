package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Adv;
import by.baranov.sergey.Entity.Comment;
import by.baranov.sergey.Entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sbaranau on 8/25/14.
 */
@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<Comment> getComments(int advId) {
        List <Comment> list= sessionFactory.getCurrentSession().createCriteria(Comment.class).add(Restrictions.eq("advId",advId)).list();
     //   List <Comment> list= (List <Comment>)sessionFactory.getCurrentSession().createQuery("from Comment where advId=:id").setInteger("id",advId).list();
        return list;
    }
    @Transactional
    @Override
    public int addComment(Comment comment) {
        if (comment.getUser() == null && comment.getUserId()>0) {
            comment.setUser(new User((long)comment.getUserId()));
        } else {
            return 0;
        }
        Adv adv = null;
        if (comment.getAdvs() == null && comment.getAdvId() > 0) {
            adv = (Adv) sessionFactory.getCurrentSession().get(Adv.class, comment.getAdvId());
            comment.setAdvs(adv);
        } else {
            return 0;
        }
        int id = (int)sessionFactory.getCurrentSession().save(comment);
        if (id >0) {
            int count =  adv.getCommentsCount();
            adv.setCommentsCount(++count);
        }
        return id;
    }
    @Transactional
    @Override
    public void removeComment(int userId, int advId) {
        Comment comment = (Comment)sessionFactory.getCurrentSession().createCriteria(Comment.class).add(Restrictions.eq("userId",userId)).add(Restrictions.eq("advId",advId)).list().get(0);
        sessionFactory.getCurrentSession().delete(comment);
    }

  /*  */
}
