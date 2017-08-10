package parser;

import junit.framework.TestCase;
import org.joda.time.DateTime;
import parser.dateconverter.readers.CloseDaysDate;
import parser.dateconverter.readers.DateConvertException;
import parser.dateconverter.readers.DateReader;
import parser.dateconverter.readers.MonthDate;

/**
 * Created by denisov_ae on 09.01.17.
 */
public class CloseDaysDateTest extends TestCase {
    public void testToday() throws DateConvertException {
        DateReader reader = new CloseDaysDate("Сегодня 12:12");

        long parseDate = reader.getMillis();
        DateTime date = new DateTime();
        date = date
                .withHourOfDay(12)
                .withMinuteOfHour(12)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);

        assertEquals(date.getMillis(), parseDate);
    }

    public void testYesterday() throws DateConvertException {
        DateReader reader = new CloseDaysDate("Вчера 12:12");

        long parseDate = reader.getMillis();
        DateTime date = new DateTime();
        date = date
                .minusDays(1)
                .withHourOfDay(12)
                .withMinuteOfHour(12)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);


        assertEquals(date.getMillis(), parseDate);
    }
}
