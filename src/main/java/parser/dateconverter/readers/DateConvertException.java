package parser.dateconverter.readers;


public class DateConvertException extends Exception {


    public DateConvertException() {
        super();
    }

    public DateConvertException(String s) {
        super(s);
    }

    public DateConvertException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DateConvertException(Throwable throwable) {
        super(throwable);
    }

}
