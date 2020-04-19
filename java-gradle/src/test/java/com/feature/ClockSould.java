package com.feature;

import com.jlopez.bankkata.Clock;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClockSould {
    @Test
    public void return_today_date_in_dd_MM_yyyy_format() {
        Clock clock = new TestableClock();

        String date = clock.todayAsString();

        assertThat(date, is("24/04/2020"));

    }

    private class TestableClock extends Clock {
        @Override
        protected LocalDate today() {
            return LocalDate.of(2020,04,24);
        }
    }
}