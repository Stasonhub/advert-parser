package clientcli;

/**
 * Created by denisov_ae on 29.12.16.
 */
public class SingleInstanceException extends Exception {
    public SingleInstanceException() {
        super();
    }

    public SingleInstanceException(String s) {
        super(s);
    }

    public SingleInstanceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SingleInstanceException(Throwable throwable) {
        super(throwable);
    }
}
