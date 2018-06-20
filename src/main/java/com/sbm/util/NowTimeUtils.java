package com.sbm.util;

import java.util.Calendar;

public final class NowTimeUtils {
    static Calendar now = Calendar.getInstance();

    private NowTimeUtils() {

    }

    public static String getYear() {
        return Integer.valueOf(now.get(Calendar.YEAR)).toString();
    }

    public static String getMonth() {
        if (now.get(Calendar.MONTH) + 1 >= 10) {
            return Integer.valueOf(now.get(Calendar.MONTH) + 1).toString();
        } else {
            return "0" + Integer.valueOf(now.get(Calendar.MONTH) + 1).toString();
        }
    }

    public static String getDay() {
        if (now.get(Calendar.DAY_OF_MONTH) >= 10) {
            return Integer.valueOf(now.get(Calendar.DAY_OF_MONTH)).toString();
        } else {
            return "0" + Integer.valueOf(now.get(Calendar.DAY_OF_MONTH)).toString();
        }

    }

    public static String getHour() {
        if (now.get(Calendar.HOUR_OF_DAY) >= 10) {
            return Integer.valueOf(now.get(Calendar.HOUR_OF_DAY)).toString();
        } else {
            return "0" + Integer.valueOf(now.get(Calendar.HOUR_OF_DAY)).toString();
        }

    }

    public static String getMinute() {
        if (now.get(Calendar.MINUTE) >= 10) {
            return Integer.valueOf(now.get(Calendar.MINUTE)).toString();
        } else {
            return "0" + Integer.valueOf(now.get(Calendar.MINUTE)).toString();
        }

    }

    public static String getSecond() {
        if (now.get(Calendar.SECOND) >= 10) {
            return Integer.valueOf(now.get(Calendar.SECOND)).toString();
        }
        return "0" + Integer.valueOf(now.get(Calendar.SECOND)).toString();
    }

    public static String getYmd() {
        return getYear() + getMonth() + getDay();
    }

    public static String getYmdhms() {
        return getYear() + getMonth() + getDay() + getHour() + getMinute() + getSecond();
    }


}
