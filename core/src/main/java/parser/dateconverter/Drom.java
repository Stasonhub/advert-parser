package parser.dateconverter;


import parser.dateconverter.readers.DateConvertException;
import parser.dateconverter.readers.DateReader;
import parser.dateconverter.readers.DigitalDate;

/**
 * Переводит дату с drom.ru из String в DateTime
 */
public class Drom implements DateConverter {

    private DateReader dateConverter;

    public Drom(String date) throws DateConvertException {
        dateConverter = new DigitalDate(date);
    }

    @Override
    public long getDateInMillis() {
        return dateConverter.getMillis();
    }
}
