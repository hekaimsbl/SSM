package Entity;

import com.google.gson.annotations.SerializedName;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author Hekai
 * @Date 2019/4/8 14:32
 * @Description TODO
 **/
@Alias("KeySearchResult")
public class KeySearchResult implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("describe")
    private String describe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
