package parser.dateconverter.readers;

/**
 * парсит дату формата (Сегодня 21:12)
 */
public class CloseDaysDate extends DateReader {

    /**
     * секунды и миллисекунды устанавливаются в ноль т.к. такая информация не требуется
     *
     * @param date
     */
    public CloseDaysDate(String date) throws DateConvertException {
        super(date);
    }

    @Override
    protected void readDate() throws DateConvertException {

        if (date.contains("вчера")) {
            dateTime = dateTime.minusDays(1);
        }
        String[] tmpDate = date.split(" ");
        if (tmpDate.length == 2) {
            updateDateTime(tmpDate[1]);
        }

    }


    /**
     * парсит строку формата (ч:м)
     *
     * @param t - dateTime
     */
    private void updateDateTime(String t) throws DateConvertException {
        String[] tmpTime = t.split(":");
        if (tmpTime.length == 2) {
            dateTime = dateTime.minuteOfHour().setCopy(tmpTime[1]);
            dateTime = dateTime.hourOfDay().setCopy(tmpTime[0]);
        } else {
            throw new DateConvertException();
        }
    }

}
