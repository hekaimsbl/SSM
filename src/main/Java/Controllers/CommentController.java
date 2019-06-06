package Controllers;

import Entity.Comment;
import Entity.CommentLike;
import Entity.CommentReply;
import Entity.Reply;
import Service.CommentService;
import Service.CommentServiceImpl;
import Utils.*;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/4/26 16:35
 * @Description TODO
 **/
@Controller
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService = new CommentServiceImpl();

    private ResultUtils resultUtils = new ResultUtils();

    @RequestMapping(value = "/addComment", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String addComment(@Param("commentInfo") String commentInfo) {
        Gson gson = new Gson();
        Print.msg("commentIn" + commentInfo);
        Comment comment = gson.fromJson(commentInfo, Comment.class);
        comment.setCreateTime(TimeUtil.getSqlTime());
        int result = commentService.insertComment(comment);
        return resultUtils.generateOpretorResult(result);
    }

    @RequestMapping(value = "/queryCommentsByFoodId", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryCommentsByFoodId(@Param("foodId") String foodId, @Param("startPage") int startPage, @Param("pageSize") int pageSize) {
        List<Comment> comments = commentService.queryCommentsByFoodId(foodId, startPage, pageSize);
        return resultUtils.generateFoodResult(comments);
    }

    @RequestMapping(value = "/queryReplyListByCommentId", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryReplyListByCommentId(@Param("commentId") int commentId) {
        List<Reply> replyList = commentService.queryReplyListByCommentId(commentId);
        return resultUtils.generateFoodResult(replyList);
    }

    @RequestMapping(value = "/addCommentLike", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String addCommentLike(@Param("userId") String userId, @Param("commentId") int commentId) {
        CommentLike commentLike = new CommentLike();
        commentLike.setUserId(userId);
        commentLike.setCommentId(commentId);
        commentLike.setDate(TimeUtil.getSqlTime());
        int result = commentService.addCommentLike(commentLike);
        return resultUtils.generateFoodResult(result);
    }

    @RequestMapping(value = "/deleteCommentLike", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String deleteCommentLike(@Param("userId") String userId, @Param("commentId") int commentId) {
        int result = commentService.deleteCommentLike(userId, commentId);
        return resultUtils.generateOpretorResult(result);
    }

    @RequestMapping(value = "/addCommentReply", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String addCommentReply(@Param("userId") String userId, @Param("commentId") int commentId, @Param("content") String content) {
        CommentReply commentReply = new CommentReply();
        commentReply.setReplyUserId(userId);
        commentReply.setCommentId(commentId);
        commentReply.setContent(content);
        commentReply.setDate(TimeUtil.getSqlTime());
        int result = commentService.addCommentReply(commentReply);
        return resultUtils.generateOpretorResult(result);
    }
}
