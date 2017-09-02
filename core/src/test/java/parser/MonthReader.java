package parser;

import junit.framework.TestCase;

public class MonthReader extends TestCase {

    public void testMonths(){
        parser.dateconverter.readers.MonthReader monthReader = new parser.dateconverter.readers.MonthReader();
        assertEquals(monthReader.getMonthNumber("Января"), 1);
        assertEquals(monthReader.getMonthNumber("Декабря"), 12);
    }
}
