package Utils;

import Entity.KeySearchResult;
import Entity.QueryData;
import Service.FoodService;
import Service.FoodServiceImpl;
import Service.UserService;
import Service.UserServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/4/9 8:40
 * @Description TODO
 **/
public class QueryByKeys {
    private int startPage;
    private int pageSize;
    private String key;

    @Autowired
    FoodService foodService = new FoodServiceImpl();
    @Autowired
    UserService userService = new UserServiceImpl();

    public QueryByKeys(int startPage, int pageSize, String key) {
        this.startPage = startPage;
        this.pageSize = pageSize;
        this.key = key;
    }

    public List<QueryData<List<KeySearchResult>>> queryKeys() {

        String[] keys = key.split(" ");
        List<String> keyList = Arrays.asList(keys);

        List<QueryData<List<KeySearchResult>>> totalQueryData = new ArrayList<QueryData<List<KeySearchResult>>>();

        QueryData<List<KeySearchResult>> foodListQueryData = new QueryData<List<KeySearchResult>>();

        foodListQueryData.setMsg("table_food");
        List<KeySearchResult> totalList = new ArrayList<KeySearchResult>();
        //分割查询
        List<KeySearchResult> lists = new ArrayList<KeySearchResult>();
        lists = foodService.queryByKeys(startPage, pageSize, keyList);
        totalList.addAll(foodService.queryByKeys(startPage, pageSize, keyList));
        //直接查询string key
        List<KeySearchResult> listss = foodService.queryByKey(startPage, pageSize, key);
        for (int i = 0; i < listss.size(); i++) {
            Print.msg("s" + lists.get(i).getDescribe());
        }
        totalList.addAll(listss);

        foodListQueryData.setData(totalList);
        totalQueryData.add(foodListQueryData);

        //查询标签
        QueryData<List<KeySearchResult>> tagQueryData = new QueryData<List<KeySearchResult>>();
        tagQueryData.setMsg("table_tag");
        List<KeySearchResult> totalTagQuery = new ArrayList<KeySearchResult>();
        List<KeySearchResult> tagQueryDataList;

        tagQueryDataList = userService.queryKeys(startPage, pageSize, keyList);

        for (int i = 0; i < tagQueryDataList.size(); i++) {
            Print.msg(tagQueryDataList.get(i).getId());
        }
        totalTagQuery.addAll(tagQueryDataList);

        tagQueryDataList = userService.queryKey(startPage, pageSize, key);
        totalTagQuery.addAll(tagQueryDataList);

        tagQueryData.setData(totalTagQuery);

        totalQueryData.add(tagQueryData);
        Gson gson = new Gson();
        Print.msg(gson.toJson(totalQueryData));
        return totalQueryData;

    }
}
