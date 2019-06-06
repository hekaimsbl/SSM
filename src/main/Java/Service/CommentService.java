package Service;

import Entity.Comment;
import Entity.CommentLike;
import Entity.CommentReply;
import Entity.Reply;

import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/4/26 16:35
 * @Description TODO
 **/
public interface CommentService {
    int insertComment(Comment comment);

    List<Comment> queryCommentsByFoodId(String foodId, int startPage, int pageSize);

    int addCommentLike(CommentLike commentLike);

    int deleteCommentLike(String userId, int commentId);

    int addCommentReply(CommentReply commentReply);

    List<Reply> queryReplyListByCommentId(int commentId);
}
