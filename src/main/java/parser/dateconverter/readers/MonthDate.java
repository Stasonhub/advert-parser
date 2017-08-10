package parser.dateconverter.readers;

/**
 * парсит дату форматов
 * 1 января 12:12
 * 1 января 2016
 * 1 января
 */
public class MonthDate extends DateReader {

    /**
     * секунды и миллисекунды устанавливаются в ноль т.к. такая информация не требуется
     *
     * @param date
     */
    public MonthDate(String date) throws DateConvertException {
        super(date);
    }

    @Override
    protected void readDate() throws DateConvertException {
        String[] splitDate = date.split(" ");
        if (splitDate.length >= 2) {
            updateDateDay(splitDate[0]);
            updateDateMonth(splitDate[1]);
        }
        if (splitDate.length >= 3) {
            updateDateTime(splitDate[2]);
        }
    }


    private void updateDateDay(String day){
        dateTime = dateTime.dayOfMonth().setCopy(day);
    }

    private void updateDateMonth(String month){
        MonthReader convertHelper = new MonthReader();
        int monthNumber = convertHelper.getMonthNumber(month);
        if (monthNumber != 0) {
            dateTime = dateTime.monthOfYear().setCopy(monthNumber);
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
        }else if(tmpTime.length == 1) {
            dateTime = dateTime.year().setCopy(tmpTime[0]);
        }else {
            throw new DateConvertException("wrong format : "+date);
        }
    }
}
