package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Comment;
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
    public List<Comment> getComments(long advId) {
      //  List <Comment> list= sessionFactory.getCurrentSession().createCriteria(Comment.class).add(Restrictions.eq("advId",advId)).list();
        List <Comment> list= (List <Comment>)sessionFactory.getCurrentSession().createQuery("from Comment where advId=:id").setLong("id",advId).list();
        return list;
    }
    @Transactional
    @Override
    public Long addCommtent(Comment comment) {
        return (Long)sessionFactory.getCurrentSession().save(comment);
    }
    @Transactional
    @Override
    public void removeComment(int userId, int advId) {
        Comment comment = (Comment)sessionFactory.getCurrentSession().createCriteria(Comment.class).add(Restrictions.eq("userId",userId)).add(Restrictions.eq("advId",advId)).list().get(0);
        sessionFactory.getCurrentSession().delete(comment);
    }


}
