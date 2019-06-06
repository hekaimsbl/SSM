package Entity;

import java.util.Date;

/**
 * @Author Hekai
 * @Date 2019/4/26 19:12
 * @Description TODO
 **/
public class CommentReply {
    private int commentId;
    private String content;
    private String replyUserId;
    private Date date;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
