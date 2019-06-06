package Entity;

import java.util.Date;

/**
 * @Author Hekai
 * @Date 2019/4/26 18:17
 * @Description TODO
 **/
public class CommentLike {
    private String userId;
    private int commentId;
    private Date date;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
