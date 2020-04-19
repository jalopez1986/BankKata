package com.jlopez.bankkata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public String todayAsString() {
        return today().format(DateTimeFormatter.ofPattern(DD_MM_YYYY));
    }

    protected LocalDate today() {
        return LocalDate.now();
    }
}
