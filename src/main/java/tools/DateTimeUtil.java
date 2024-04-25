package tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DateTimeUtil {
    public static final DateTimeFormatter LOCAL_DATE_TIME_STAMP = ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now().format(LOCAL_DATE_TIME_STAMP);
    }


}
