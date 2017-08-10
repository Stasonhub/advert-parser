package parser.dateconverter.readers;


/**
 *  парсит дату формата 12-12-12
 */
public class DigitalDate extends DateReader {

    /**
     * Дата формата (11-12)
     */
    private static final int TYPE_DAY_MONTH = 2;
    /**
     * Дата формата (11-12-16)
     */
    private static final int TYPE_DAY_MONTH_YEAR = 3;

    private String[] splitDate;

    /**
     * секунды и миллисекунды устанавливаются в ноль т.к. такая информация не требуется
     *
     * @param date
     */
    public DigitalDate(String date) throws DateConvertException {
        super(date);
    }


    @Override
    protected void readDate() throws DateConvertException {
        splitDate = date.split("-");
        switch (splitDate.length){
            case TYPE_DAY_MONTH:
                convertDayMonthDate();
                break;
            case TYPE_DAY_MONTH_YEAR:
                convertDayMonthYearDate();
                break;
            default: throw new DateConvertException();
        }
    }

    private void convertDayMonthYearDate() throws DateConvertException {
        try {
            convertDayMonthDate();
            int year = Integer.parseInt("20"+splitDate[2]);
            dateTime = dateTime.withYear(year);
        } catch (NumberFormatException e){
            throw new DateConvertException(e);
        }
    }



    private void convertDayMonthDate() throws DateConvertException {
        try {
            dateTime = dateTime.withDayOfMonth(Integer.parseInt(splitDate[0]));
            dateTime = dateTime.withMonthOfYear(Integer.parseInt(splitDate[1]));
        } catch (NumberFormatException e){
            throw new DateConvertException(e);
        }

    }


}
