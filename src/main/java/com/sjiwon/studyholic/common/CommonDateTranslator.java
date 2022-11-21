package com.sjiwon.studyholic.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static com.sjiwon.studyholic.common.VariableFactory.LOCALE_KOREA;
import static com.sjiwon.studyholic.common.VariableFactory.MONTH_TO_STRING;

public class CommonDateTranslator {
    public static String translateLocalDateTimeToStringVersion1(LocalDateTime date, Locale locale) {
        if (locale.getLanguage().equalsIgnoreCase(LOCALE_KOREA)) { // locale: ko
            return getGeneralStringFromLocalDateTime(date);
        } else { // locale: other
            return getSpecificStringFromLocalDateTime(date);
        }
    }

    public static String translateLocalDateTimeToStringVersion2(LocalDateTime date, Locale locale) {
        if (locale.getLanguage().equalsIgnoreCase(LOCALE_KOREA)) { // locale: ko
            return getGeneralDetailStringFromLocalDateTime(date);
        } else { // locale: other
            return getSpecificDetailStringFromLocalDateTime(date);
        }
    }

    public static String translateLocalDateToString(LocalDate date, Locale locale) {
        if (locale.getLanguage().equalsIgnoreCase(LOCALE_KOREA)) { // locale: ko
            return getGeneralStringFromLocalDate(date);
        } else { // locale: other
            return getSpecificStringFromLocalDate(date);
        }
    }

    public static String translateRegisterDateFromCurrentDate(LocalDateTime date, Locale locale) {
        LocalDateTime currentDate = LocalDateTime.now();

        long hour = ChronoUnit.HOURS.between(date, currentDate);
        long minute = ChronoUnit.MINUTES.between(date, currentDate);
        long second = ChronoUnit.SECONDS.between(date, currentDate);

        if (locale.getLanguage().equalsIgnoreCase(LOCALE_KOREA)) { // locale: ko
            if (second < 60) {
                return second + "초 전";
            } else if (minute < 60) {
                return minute + "분 전";
            } else if (hour < 24) {
                return hour + "시간 전";
            } else {
                return getGeneralStringFromLocalDateTime(date);
            }
        } else { // locale: other
            if (second < 60) {
                return second + " Seconds Ago";
            } else if (minute < 60) {
                return minute + " Minutes Ago";
            } else if (hour < 24) {
                return hour + " Hours Ago";
            } else {
                return getSpecificStringFromLocalDateTime(date);
            }
        }
    }

    private static String getGeneralStringFromLocalDate(LocalDate date) {
        return date.getYear() + "년 " +
                date.getMonth().getValue() + "월 " +
                date.getDayOfMonth() + "일";
    }

    private static String getGeneralStringFromLocalDateTime(LocalDateTime date) {
        return date.getYear() + "년 " +
                date.getMonth().getValue() + "월 " +
                date.getDayOfMonth() + "일";
    }

    private static String getGeneralDetailStringFromLocalDateTime(LocalDateTime date) {
        String hour = getDateStringFromSpecificFormat(date.getHour());
        String minute = getDateStringFromSpecificFormat(date.getMinute());
        return getGeneralStringFromLocalDateTime(date) + " " + hour + "시 " + minute + "분";
    }

    private static String getSpecificStringFromLocalDate(LocalDate date) {
        return MONTH_TO_STRING.get(date.getMonth().getValue()) + " " + date.getDayOfMonth() + ", " + date.getYear();
    }

    private static String getSpecificStringFromLocalDateTime(LocalDateTime date) {
        return MONTH_TO_STRING.get(date.getMonth().getValue()) + " " + date.getDayOfMonth() + ", " + date.getYear();
    }

    private static String getSpecificDetailStringFromLocalDateTime(LocalDateTime date) {
        String hour = getDateStringFromSpecificFormat(date.getHour());
        String minute = getDateStringFromSpecificFormat(date.getMinute());
        return MONTH_TO_STRING.get(date.getMonth().getValue()) + " " + date.getDayOfMonth() + " " + date.getYear() + ", " + hour + ":" + minute;
    }

    private static String getDateStringFromSpecificFormat(int target) {
        String value = String.valueOf(target);
        while (value.length() != 2) {
            value = "0" + value;
        }
        return value;
    }
}
