package clientcli;

import api.AdParseException;
import api.AdsParserFacade;
import api.factories.ObserverFactory;
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
    private static AdsParserFacade adsParserFacade;
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
        settings = new ClientSettings(cliArgs);
        SingleInstance singleInstanceSocket = new SingleInstance(settings);
        tryStartCliApp();
        singleInstanceSocket.socketClose();
    }
    /**
     * Инициализирует нужные объекты и запускает консольное приложение
     */
    private static void tryStartCliApp() {

        try {
            adsParserFacade = new AdsParserFacade(settings);
            addObservers();
            Runnable app = new CliApplication(adsParserFacade, cliArgs);
            new Thread(app).start();
        } catch (AdParseException e) {
            logger.warn("Facade init error. ", e);
        } catch (ParseException e) {
            logger.info("This option is not existing try -h");
        }
    }


    /**
     * подписывает наблюдателей
     */
    private static void addObservers(){
        settings.getObserversFromSettings()
                .forEach(Launcher::addObserver);
    }

    private static void addObserver(String observerName){
        try {
            adsParserFacade.addObserver(ObserverFactory.create(settings, observerName));
        } catch (AdParseException e) {
            logger.warn("Observer create error. ",e);
        }
    }

}
