package com.sjiwon.studyholic.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CommonDateTranslator {
    public static String translateLocalDateTimeToStringVersion1(LocalDateTime date) {
        return date.getYear() + "년 " +
                date.getMonth().getValue() + "월 " +
                date.getDayOfMonth() + "일";
    }

    public static String translateLocalDateTimeToStringVersion2(LocalDateTime date) {
        String hour = String.valueOf(date.getHour());
        while (hour.length() != 2) {
            hour = "0" + hour;
        }

        return date.getYear() + "년 " +
                date.getMonth().getValue() + "월 " +
                date.getDayOfMonth() + "일 " +
                hour + "시 " +
                date.getMinute() + "분";
    }

    public static String translateLocalDateToString(LocalDate date) {
        return date.getYear() + "년 " +
                date.getMonth().getValue() + "월 " +
                date.getDayOfMonth() + "일";
    }

    public static String translateRegisterDateFromCurrentDate(LocalDateTime date) {
        LocalDateTime currentDate = LocalDateTime.now();

        long hour = ChronoUnit.HOURS.between(date, currentDate);
        long minute = ChronoUnit.MINUTES.between(date, currentDate);
        long second = ChronoUnit.SECONDS.between(date, currentDate);

        if (second < 60) {
            return second + "초 전";
        } else if (minute < 60) {
            return minute + "분 전";
        } else if (hour < 24) {
            return hour + "시간 전";
        } else {
            return date.getYear() + "년 " +
                    date.getMonth().getValue() + "월 " +
                    date.getDayOfMonth() + "일";
        }
    }
}
