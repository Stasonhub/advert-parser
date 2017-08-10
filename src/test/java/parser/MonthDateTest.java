package parser;

import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.junit.Test;
import parser.dateconverter.readers.DateConvertException;
import parser.dateconverter.readers.DateReader;
import parser.dateconverter.readers.DigitalDate;
import parser.dateconverter.readers.MonthDate;


public class MonthDateTest extends TestCase {

    public void testDayMonth() throws DateConvertException {

        DateReader reader = new MonthDate("12 декабря 12:12");

        long parseDate = reader.getMillis();
        DateTime date = new DateTime();
        date = date
                .withMonthOfYear(12)
                .withDayOfMonth(12)
                .withHourOfDay(12)
                .withMinuteOfHour(12)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);


        assertEquals(date.getMillis(), parseDate);

    }

    public void testDayMonthYear() throws DateConvertException {
        DateReader reader = new MonthDate("12 декабря 2012");

        long parseDate = reader.getMillis();
        DateTime date = new DateTime();
        date = date
                .withYear(2012)
                .withMonthOfYear(12)
                .withDayOfMonth(12)
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);


        assertEquals(date.getMillis(), parseDate);
    }

}
