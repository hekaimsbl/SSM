package Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Hekai
 * @Date 2019/4/3 10:23
 * @Description TODO
 **/
@Alias("User")
public class User{
    @SerializedName("user_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @Expose(serialize = false,deserialize = false)
    private String phoneNumber;

    @SerializedName("img_head")
    private String imgHead;

    @SerializedName("create_time")
    private Date createTime;

    @Expose(serialize = false,deserialize = false)
    private int status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgHead() {
        return imgHead;
    }

    public void setImgHead(String imgHead) {
        this.imgHead = imgHead;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
