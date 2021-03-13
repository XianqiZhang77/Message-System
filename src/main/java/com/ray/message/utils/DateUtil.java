package com.ray.message.utils;

import java.util.Date;

public class DateUtil {

    public static Date addDays(Date date, int days) {

        return new Date(date.getTime() + days * 86400 * 1000);
    }
}
