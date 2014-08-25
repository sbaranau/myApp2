package by.baranov.sergey.Service;

import by.baranov.sergey.Entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sbaranau on 8/25/14.
 */
@Service
public interface CommentService {

    public Long addComment(Comment comment);

    public void deleteComment(int userId, int advId);

    public List<Comment> getComments(long advId);
}
