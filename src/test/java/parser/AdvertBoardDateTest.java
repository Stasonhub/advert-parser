package parser;

import junit.framework.TestCase;
import org.joda.time.DateTime;
import parser.dateconverter.*;
import parser.dateconverter.readers.DateConvertException;

/**
 * Created by denisov_ae on 09.01.17.
 */
public class AdvertBoardDateTest  extends TestCase{

    public void testAvito() throws DateConvertException {
        DateConverter reader = new parser.dateconverter.Avito("Сегодня 12:12");

        long parseDate = reader.getDateInMillis();

        DateTime date = new DateTime();
        date = date
                .withHourOfDay(12)
                .withMinuteOfHour(12)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
        assertEquals(date.getMillis(), parseDate);
    }

    public void testDrom() throws DateConvertException {
        DateConverter reader = new parser.dateconverter.Drom("12-12-12");

        long parseDate = reader.getDateInMillis();

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
