import Entity.*;
import Service.*;
import Utils.*;
import com.google.gson.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/2/19 15:51
 * @Description TODO
 **/

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentTest {
    @Autowired
    CommentService commentService = new CommentServiceImpl();

    String myFoodId = "20191554260951620101";
    String myUserId = "20191554260795089102";


    @org.junit.Test
    public void queryCommentsByFoodId() {
        String foodId = "20191554260951620101";
        List<Comment> comments = commentService.queryCommentsByFoodId(foodId, 0, 20);
        ApiResult<List<Comment>> apiResult = new ApiResult<List<Comment>>();
        apiResult.setData(comments);
        Gson gson = new Gson();
        Print.msg(gson.toJson(apiResult));
    }

    /**
     * 添加评论
     */
    @org.junit.Test
    public void insertComment() {
        String food_id = "20191554260951620101";
        String content = "这是一条评论";
        String comment_userId = "20191554260795089102";
        Date date = TimeUtil.getSqlTime();

        Comment comment = new Comment();
        comment.setCommentUserId(comment_userId);
        comment.setContent(content);
        comment.setCreateTime(date);
        comment.setFoodId(food_id);

        int result = commentService.insertComment(comment);
    }

    @org.junit.Test
    public void insertCommentLike() {
        String food_id = "20191554260951620101";
        String content = "这是一条评论";
        String comment_userId = "20191554260795089102";
        Date date = TimeUtil.getSqlTime();

        Comment comment = new Comment();
        comment.setCommentUserId(comment_userId);
        comment.setContent(content);
        comment.setCreateTime(date);
        comment.setFoodId(food_id);

        CommentLike commentLike = new CommentLike();
        commentLike.setDate(TimeUtil.getSqlTime());
        commentLike.setCommentId(1);
        commentLike.setUserId(food_id);


        int result = commentService.addCommentLike(commentLike);
    }

    @org.junit.Test
    public void addCommentReply() {
        String content = "hhhhhh";
        ResultUtils resultUtils = new ResultUtils();
        CommentReply commentReply = new CommentReply();
        commentReply.setReplyUserId(myFoodId);
        commentReply.setCommentId(1);
        commentReply.setContent(content);
        commentReply.setDate(TimeUtil.getSqlTime());
        int result = commentService.addCommentReply(commentReply);
        Print.msg(resultUtils.generateOpretorResult(result));
    }


}
