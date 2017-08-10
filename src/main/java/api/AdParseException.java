package api;


public class AdParseException extends Exception {


    public AdParseException() {
        super();
    }

    public AdParseException(String s) {
        super(s);
    }

    public AdParseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AdParseException(Throwable throwable) {
        super(throwable);
    }

}
