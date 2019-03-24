package Entity;

import com.google.gson.JsonElement;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author Hekai
 * @Date 2019/2/18 21:55
 * @Description TODO
 **/
@Alias(value = "Food")
public class Food implements Serializable{
    private BigInteger Id;
    private String Title;
    private BigInteger AuthorId;
    private Double Longitude;
    private Double Latitude;
    private String Address;
    private Date Time;

    public BigInteger getId() {
        return Id;
    }

    public void setId(BigInteger id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public BigInteger getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(BigInteger authorId) {
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
}
