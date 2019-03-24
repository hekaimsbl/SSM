package Utils;

import java.text.SimpleDateFormat;

/**
 * @author hekai
 * 生成16位数的唯一id
 *
 */
public class IDGenerate {
    public static void main(String[] args) {
        //调用生成id方法
        System.out.println(getGuid());
    }

    public static int Guid = 100;

    public static String getGuid() {

        IDGenerate.Guid+=1;

        long now = System.currentTimeMillis();
        String info=now+"";
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran=0;
        if(IDGenerate.Guid>999){
            IDGenerate.Guid=100;
        }
        ran=IDGenerate.Guid;

        /*return info.substring(2)+ran;*/
        return info.substring(3)+ran;
    }
}
