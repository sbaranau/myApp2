package by.baranov.sergey.Service;

import by.baranov.sergey.DAO.AdvDao;
import by.baranov.sergey.DAO.CommentDao;
import by.baranov.sergey.Entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sbaranau on 8/25/14.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private AdvDao advDao;

    @Override
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public void deleteComment(int userId, int advId) {
        commentDao.removeComment(userId,advId);
    }

    @Override
    public List<Comment> getComments(int advId) {
        return commentDao.getComments(advId);
    }
}
