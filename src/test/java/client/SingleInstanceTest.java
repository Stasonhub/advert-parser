package client;

import api.interfaces.Settings;
import clientcli.ClientSettings;
import clientcli.SingleInstance;
import clientcli.SingleInstanceException;
import junit.framework.TestCase;


public class SingleInstanceTest extends TestCase {

    private Settings settings;

    /**
     * Проверяет выдается ли исключение при
     * двойном подключении к одному порту
     */
    public void testSingleInstance() {
        settings = new ClientSettings(new String[]{"1"});

        boolean isTwiceConnect = getTwiceConnect();

        assertTrue(isTwiceConnect);
    }


    private boolean getTwiceConnect(){
        boolean twiceConnect = false;

        try{
            new SingleInstance(settings);
            new SingleInstance(settings);
        }catch (SingleInstanceException e){
            twiceConnect = true;
        }
        return twiceConnect;
    }

}
