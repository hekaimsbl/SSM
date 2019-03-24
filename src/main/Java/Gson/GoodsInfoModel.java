package Gson;

import com.google.gson.annotations.SerializedName;

/**
 * @Author Hekai
 * @Date 2019/3/24 17:03
 * @Description TODO
 **/
public class GoodsInfoModel {
    @SerializedName("price")
    private int mPrice;

    @SerializedName("type")
    private String mType;


    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
}
