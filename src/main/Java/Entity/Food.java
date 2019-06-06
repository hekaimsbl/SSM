package Entity;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.ibatis.type.Alias;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/2/18 21:55
 * @Description TODO
 **/
@Alias(value = "Food")
public class Food implements Serializable {
    @SerializedName("id")
    private String Id;

    @SerializedName("title")
    private String Title;

    @SerializedName("author_id")
    private String AuthorId;

    @SerializedName("business_time")
    private String businessTime;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("user_name")
    private String UserName;

    @SerializedName("content")
    private String Content;

    @SerializedName("longitude")
    private Double Longitude;

    @SerializedName("latitude")
    private Double Latitude;

    @SerializedName("address")
    private String Address;

    @SerializedName("city_code")
    private String cityCode;

    @SerializedName("create_time")
    private Date Time;

    @SerializedName("image_url")
    @Nullable
    private List<String> ImageUrls;

    @SerializedName("tags")
    @Nullable
    private List<String> Tags;

    @SerializedName("recommend_number")
    @Expose(deserialize = false)
    private int RecommendNumber;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(String authorId) {
        AuthorId = authorId;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    @Nullable
    public List<String> getImageUrls() {
        return ImageUrls;
    }

    public void setImageUrls(@Nullable List<String> imageUrls) {
        ImageUrls = imageUrls;
    }

    public int getRecommendNumber() {
        return RecommendNumber;
    }

    public void setRecommendNumber(int recommendNumber) {
        RecommendNumber = recommendNumber;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Nullable
    public List<String> getTags() {
        return Tags;
    }

    public void setTags(@Nullable List<String> tags) {
        Tags = tags;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
