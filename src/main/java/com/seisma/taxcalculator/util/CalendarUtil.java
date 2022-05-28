package com.seisma.taxcalculator.util;

import com.seisma.taxcalculator.model.MonthInfo;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class CalendarUtil {

    private CalendarUtil() {};

    public static MonthInfo getMonthInfo(int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        String monthText = new DateFormatSymbols().getMonths()[month];
        return MonthInfo.builder()
                .firstDay(String.format("%d %s", 1, monthText))
                .lastDay(String.format("%d %s", cal.getActualMaximum(Calendar.DATE), monthText))
                .build();
    }
}
