package Utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @Author Hekai
 * @Date 2019/3/25 14:03
 * @Description TODO
 **/
public class Print {
    private static final Boolean PRINT = true;

    public static void msg(Object msg) {
        if (PRINT) {
            System.out.println(msg);
        }
    }
}
