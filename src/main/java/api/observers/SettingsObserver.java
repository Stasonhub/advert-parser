package api.observers;

import api.interfaces.Observer;
import api.interfaces.Settings;


 abstract class SettingsObserver implements Observer {

    protected Settings settings;
    public SettingsObserver(Settings settings){
        this.settings = settings;
    }

}
