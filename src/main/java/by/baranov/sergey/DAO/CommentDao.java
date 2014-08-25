package by.baranov.sergey.DAO;

import by.baranov.sergey.Entity.Comment;

import java.util.List;

/**
 * Created by sbaranau on 8/25/14.
 */
public interface CommentDao {

    public List<Comment> getComments(long advId);

    public Long addCommtent(Comment comment);

    public void removeComment(int userId, int advId);

}
