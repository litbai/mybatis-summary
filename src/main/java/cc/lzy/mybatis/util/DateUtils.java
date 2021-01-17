/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package cc.lzy.mybatis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期Util
 *
 * @author taigai
 * @version DateUtils.java, v 0.1 2020年12月26日 20:09 taigai Exp $
 */
public class DateUtils {

    private static final DateTimeFormatter GENERAL_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 通用格式化，yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime 日期时间
     * @return 格式化文本
     */
    public static String format(LocalDateTime dateTime) {
        return GENERAL_FORMAT.format(dateTime);
    }

    /**
     * 解析为Date
     *
     * @param text 格式化文本
     * @return Date
     */
    public static Date parseDate(String text) {
        LocalDateTime localDateTime = LocalDateTime.parse(text, GENERAL_FORMAT);
        return Date.from(localDateTime.toInstant(ZoneOffset.ofHours(8)));
    }

    /**
     * 解析为LocalDateTime
     *
     * @param text 格式化文本
     * @return LocalDateTime
     */
    public static LocalDateTime parseLocalDateTime(String text) {
        return LocalDateTime.parse(text, GENERAL_FORMAT);
    }
}