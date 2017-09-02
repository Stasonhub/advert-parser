package parser;

import junit.framework.TestCase;
import org.joda.time.DateTime;
import parser.dateconverter.Avito;
import parser.dateconverter.readers.DateConvertException;
import parser.dateconverter.readers.DateReader;
import parser.dateconverter.readers.DigitalDate;


public class DigitDateTest extends TestCase {

    public void testDigitDayMonth() throws DateConvertException {
        DateReader reader = new DigitalDate("12-12");

        long parseDate = reader.getMillis();
        DateTime date = new DateTime();
        date = date
                .withMonthOfYear(12)
                .withDayOfMonth(12)
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);

        assertEquals(date.getMillis(), parseDate);
    }

    public void testDayMonthYear() throws DateConvertException {
        DateReader reader = new DigitalDate("12-12-12");

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
