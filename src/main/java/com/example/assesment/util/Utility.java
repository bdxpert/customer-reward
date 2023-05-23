package com.example.assesment.util;

import java.time.LocalDate;

public class Utility {

    public static LocalDate getSearchStartDate()
    {
       return LocalDate.of(LocalDate.now().minusMonths(2).getYear(), LocalDate.now().minusMonths(2).getMonthValue(), 1);
    }
    public static LocalDate getSearchEndDate()
    {
        return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().lengthOfMonth());
    }

    public static LocalDate getFirstMonthStartDate(LocalDate localDate)
    {
        return LocalDate.of(localDate.getYear(), localDate.getMonthValue(), 01);
    }

    public static LocalDate getSecondMonthStartDate(LocalDate localDate)
    {
        return LocalDate.of(localDate.getYear(), localDate.getMonthValue()-1, 01);
    }
    public static LocalDate getLastMonthStartDate(LocalDate localDate)
    {
        return LocalDate.of(localDate.getYear(), localDate.getMonthValue(), 01);
    }
    public static Boolean matchMonthOnStartAndEndDate(LocalDate startDate, LocalDate endDate)
    {
        if(startDate.getMonthValue() == endDate.getMonthValue() && startDate.getYear() == endDate.getYear())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
