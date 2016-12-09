package edu.self.bidchat.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    private static final String SIMPLE_DATA_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String convertNowToTimestamp() {
        SimpleDateFormat sdFormat = new SimpleDateFormat(SIMPLE_DATA_FORMAT_PATTERN);
        return sdFormat.format(new Date());
    }
}
