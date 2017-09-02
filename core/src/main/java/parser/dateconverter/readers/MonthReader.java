package parser.dateconverter.readers;

/**
 * содержит вспомогательные методы для парсинга даты
 */
public class MonthReader {

    private String[] months = {
            "Января",
            "Февраля",
            "Марта",
            "Апреля",
            "Мая",
            "Июня",
            "Июля",
            "Августа",
            "Сентября",
            "Октября",
            "Ноября",
            "Декабря",
    };

    /**
     * Возвращает номер месяца начиная с 1-12, 0 если такого месяца не существует
     *
     * @param month - месяц
     * @return
     */
    public int getMonthNumber(String month) {
        int res = 0;
        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(month)) {
                res = i + 1;
                break;
            }
        }
        return res;
    }
}
