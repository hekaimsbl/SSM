package Utils;

import java.text.SimpleDateFormat;

/**
 * @author hekai
 * 生成16位数的唯一id
 */
public class IDGenerate {
    public static int Guid = 100;


    public static String getGuid16() {

        IDGenerate.Guid += 1;

        long now = System.currentTimeMillis();
        String info = now + "";
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran = 0;
        if (IDGenerate.Guid > 999) {
            IDGenerate.Guid = 100;
        }
        ran = IDGenerate.Guid;

        /*return info.substring(2)+ran;*/
        return info.substring(3) + ran;
    }

    public static String getGuid() {

        IDGenerate.Guid += 1;

        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        //获取时间戳
        String time = dateFormat.format(now);
        String info = now + "";
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran = 0;
        if (IDGenerate.Guid > 999) {
            IDGenerate.Guid = 100;
        }
        ran = IDGenerate.Guid;

        return time + info + ran;
    }
}
