package Utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Hekai
 * @Date 2019/2/22 9:55
 * @Description TODO
 **/
public class TimeUtil {
    public static Timestamp getSqlTime() {
        Date ud = new Date();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String a = sp.format(new Date());
        try {
            ud = sp.parse(a);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp st = new Timestamp(ud.getTime());
        Date date = new Timestamp(new Date().getTime());
        Print.msg(st.toString());
        return st;
    }

    public static String dateToString(Date date) {
        String dateStr;
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateStr = sdf.format(date);
        return dateStr;
    }

    public static Date StringToDate(String str) {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date StampToDate(Date date) {
        Date date1 = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            String a = sdf.format(date);
            date1 = sdf.parse(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date1;
    }
}
