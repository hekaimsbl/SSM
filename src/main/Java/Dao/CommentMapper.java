package Dao;

import Entity.Comment;
import Entity.CommentLike;
import Entity.CommentReply;
import Entity.Reply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/4/26 16:43
 * @Description TODO
 **/
public interface CommentMapper {
    /**
     * 添加评论
     *
     * @param comment 评论实体
     * @return
     */
    String Sql_insertComment = "INSERT INTO t_comment (food_id,content,comment_userId,create_time)" +
            " VALUES " +
            "(#{comment.foodId},#{comment.content},#{comment.commentUserId},#{comment.createTime})";

    @Insert(Sql_insertComment)
    int insertComment(@Param("comment") Comment comment);


    /**
     * 添加回复
     * @param reply
     * @return
     */
   /* String Sql_insertReply = "INSERT INTO t_reply (comment_id,content,reply_userId)" +
            "VALUES " +
            "(#{reply.commentId},#{reply.content},#{reply.replyUserId})";
    @Insert(Sql_insertReply)
    int insertReply(@Param("reply") Reply reply);
*/
    /**
     * 删除评论点赞关系
     *
     * @param userId
     * @param commentId
     * @return
     */
    String Sql_deleteCommentLike = "DELETE FROM t_relationship_comment_like" +
            " WHERE " +
            "comment_id = #{commentId}" +
            " AND " +
            "from_userId = #{userId}";

    @Delete(Sql_deleteCommentLike)
    int deleteCommentLike(@Param("userId") String userId, @Param("commentId") int commentId);


    List<Comment> queryCommentsByFoodId(@Param("foodId") String foodId, @Param("startPage") int startPage, @Param("pageSize") int pageSize);

    String Sql_addCommentLike = "INSERT INTO t_relationship_comment_like(comment_id,from_userId,create_time)" +
            "VALUES" +
            "(#{comment.commentId},#{comment.userId},#{comment.date})";

    @Insert(Sql_addCommentLike)
    int addCommentLike(@Param("comment") CommentLike commentLike);


    String Sql_insertReply = "INSERT INTO t_reply (comment_id,content,reply_userId,create_time)" +
            "VALUES " +
            "(#{reply.commentId},#{reply.content},#{reply.replyUserId},#{reply.date})";

    @Insert(Sql_insertReply)
    int addCommentReply(@Param("reply") CommentReply commentReply);

    List<Reply> queryReplyListByCommentId(@Param("commentId") int commentId);
}
