import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Type;
import Gson.*;

/**
 * @Author Hekai
 * @Date 2019/3/24 14:45
 * @Description TODO
 **/

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class GsonTest {
    @org.junit.Test
    public void Test(){
        ApiResult<GoodsInfoModel> apiResult = new ApiResult<GoodsInfoModel>();

        GoodsInfoModel goodsInfoModel = new GoodsInfoModel();
        goodsInfoModel.setmPrice(20);
        goodsInfoModel.setmType("hello");

        apiResult.setError("not_found");
        apiResult.setStatus(123);
        apiResult.setData(goodsInfoModel);

        Gson gson = new Gson();
        Type jsonType = new TypeToken<ApiResult<GoodsInfoModel>>(){}.getType();
        String jsonStr = gson.toJson(apiResult,jsonType);
        System.out.println("toJson:" + jsonStr + "\n");

        ApiResult<GoodsInfoModel> apiResultt = new ApiResult<GoodsInfoModel>();
        apiResultt = gson.fromJson(jsonStr,jsonType);
        StringBuffer sb = new StringBuffer();
        sb.append(apiResultt.getStatus() + "\n");
        sb.append(apiResultt.getError() + "\n");
        sb.append(apiResultt.getData().getmPrice() + "\n");
        System.out.println("fromJson:" + sb.toString());
    }
}
