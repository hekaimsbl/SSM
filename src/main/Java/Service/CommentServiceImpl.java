package Service;

import Dao.CommentMapper;
import Entity.Comment;
import Entity.CommentLike;
import Entity.CommentReply;
import Entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/4/26 16:36
 * @Description TODO
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    public List<Comment> queryCommentsByFoodId(String foodId, int startPage, int pageSize) {
        return commentMapper.queryCommentsByFoodId(foodId, startPage, pageSize);
    }

    public int addCommentLike(CommentLike commentLike) {
        return commentMapper.addCommentLike(commentLike);
    }

    public int deleteCommentLike(String userId, int commentId) {
        return commentMapper.deleteCommentLike(userId, commentId);
    }

    public int addCommentReply(CommentReply commentReply) {
        return commentMapper.addCommentReply(commentReply);
    }

    public List<Reply> queryReplyListByCommentId(int commentId) {
        return commentMapper.queryReplyListByCommentId(commentId);
    }
}
