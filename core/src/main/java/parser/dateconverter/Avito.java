package parser.dateconverter;


import parser.dateconverter.readers.CloseDaysDate;
import parser.dateconverter.readers.DateConvertException;
import parser.dateconverter.readers.DateReader;
import parser.dateconverter.readers.MonthDate;

/**
 * Переводит дату с авито из String в DateTime
 */
public class Avito implements DateConverter {
    private static final int TYPE_DAY_TIME = 0;
    private static final int TYPE_MONTH_TIME = 1;


    private DateReader dateConverter;
    private String date;

    public Avito(String date) throws DateConvertException {
        this.date = date.toLowerCase();
        switch (getType()) {
            case TYPE_DAY_TIME:
                readDayTimeDate();
                break;
            case TYPE_MONTH_TIME:
                readMonthTime();
                break;
            default:
                throw new DateConvertException("No match type of date.");
        }
    }

    private int getType() {
        return (date.contains("вчера") || date.contains("сегодня")) ? TYPE_DAY_TIME : TYPE_MONTH_TIME;
    }


    /**
     * парсит дату формата ( Сегодня/Вчера ч:м )
     */
    private void readDayTimeDate() throws DateConvertException {
        dateConverter = new CloseDaysDate(date);
    }

    /**
     * парсит дату формата ( д месяц ч:м )
     */
    private void readMonthTime() throws DateConvertException {
        dateConverter = new MonthDate(date);
    }


    @Override
    public long getDateInMillis() {
        return dateConverter.getMillis();
    }
}
