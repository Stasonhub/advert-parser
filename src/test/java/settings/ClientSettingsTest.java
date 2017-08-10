package settings;

import api.SettingsParam;
import clientcli.ClientSettings;
import junit.framework.TestCase;


public class ClientSettingsTest extends TestCase {



    public void testSettingsFromStr(){

        ClientSettings settings = new ClientSettings(new String[] {"1","OBSERVERS=Logger Telegram,DB_LOGIN=root , STORAGE=Pg"});

        ClientSettings testSettings = new ClientSettings(new String[]{""});
        testSettings.put(SettingsParam.OBSERVERS.toString(),"Logger Telegram");
        testSettings.put(SettingsParam.DB_LOGIN.toString(),"root");
        testSettings.put(SettingsParam.STORAGE.toString(),"Pg");

        assertEquals(settings,testSettings);

    }

}
