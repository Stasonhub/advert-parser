package parser.dateconverter.readers;

import org.joda.time.DateTime;

/**
 * Класс парсит дату в строковом формате
 */
public abstract class DateReader {



    protected String date;
    protected DateTime dateTime;

    /**
     * секунды и миллисекунды устанавливаются в ноль т.к. такая информация не требуется
     */
    public DateReader(String date) throws DateConvertException {
        this.date = date.toLowerCase();
        dateTime = new DateTime();
        dateTime = dateTime
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
        readDate();
    }

    /**
     * Считывает дату находящуюся в поле date и записывает результат в dateTime
     */
    protected abstract void readDate() throws DateConvertException;

    /**
     * Возвращает распознаное время в формате DateTime
     * @return
     */
    public DateTime getDateTime(){
        return dateTime;
    }

    /**
     * Возвращает распознаное время в миллисикундах
     * @return
     */
    public long getMillis(){
        return dateTime.getMillis();
    }


}
