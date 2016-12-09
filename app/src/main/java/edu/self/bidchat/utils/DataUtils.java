package edu.self.bidchat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    private static final String SIMPLE_DATA_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat SIMPLE_DATA_FORMAT = new SimpleDateFormat(SIMPLE_DATA_FORMAT_PATTERN);

    public static String convertNowToTimestamp() {
        return SIMPLE_DATA_FORMAT.format(new Date());
    }
}
