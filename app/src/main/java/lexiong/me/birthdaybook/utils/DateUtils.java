package lexiong.me.birthdaybook.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by yueli on 2018/4/2.
 * lexiong.me.birthdaybook.utils
 */

public class DateUtils {
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String getDateTimeString(Date date)
    {
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format0.format(date.getTime());//这个就是把时间戳经过处理得到期望格式的时间
    }
}
