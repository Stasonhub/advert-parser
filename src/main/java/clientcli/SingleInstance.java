package clientcli;


import api.SettingsParam;
import api.interfaces.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Класс предназначен для проверки единственного подключения к приложению
 */
public class SingleInstance {

    private static Logger logger = LoggerFactory.getLogger(Launcher.class);

    private ServerSocket singleInstanceSocket;
    private Settings settings;

    public SingleInstance(Settings settings) throws SingleInstanceException {
        this.settings = settings;
        connectSoccet();
    }

    private void connectSoccet() throws SingleInstanceException {
        try {
            singleInstanceSocket = new ServerSocket(
                    getPortNumber(), 0, InetAddress.getByAddress(new byte[]{127, 0, 0, 1})
            );
        } catch (IOException e) {
            throw new SingleInstanceException(e);
        }
    }

    /**
     * закрытие порта
     */
    public void socketClose() {
        try {
            singleInstanceSocket.close();
        } catch (IOException e) {
            logger.warn("Socket socketClose error. ", e);
        }
    }

    private Integer getPortNumber(){
        Integer portNumber = 9999;
        try{
            portNumber = Integer.parseInt(settings.get(SettingsParam.PORT.toString()));
        }catch (NumberFormatException e){
            logger.warn("wrong port number in settings.", e);
        }
        return portNumber;
    }

}
