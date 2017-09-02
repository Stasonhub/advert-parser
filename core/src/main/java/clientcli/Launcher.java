package clientcli;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Инициализирует настройки, проверяет порт на единственное подключение, подключает наблюдателей
 * если все в порядке запускает приложение.
 */
public class Launcher {

    private static Logger logger = LoggerFactory.getLogger(Launcher.class);
    private static ClientSettings settings;
    private static String[] cliArgs;

    public static void main(String[] args) {
        try {
            cliArgs = args;
            startSingleInstanceCliApp();
        } catch (SingleInstanceException e) {
            logger.info("Application already run.", e);
        }
    }

    private static void startSingleInstanceCliApp() throws SingleInstanceException {
        settings = new ClientSettings();
        SingleInstance singleInstanceSocket = new SingleInstance(settings);
        tryStartCliApp();
        singleInstanceSocket.socketClose();
    }
    /**
     * Инициализирует нужные объекты и запускает консольное приложение
     */
    private static void tryStartCliApp() {

        try {
            Runnable app = new CliApplication(settings, cliArgs);
            new Thread(app).start();
        } catch (ParseException e) {
            logger.info("This option is not existing try -h");
        }
    }




}
