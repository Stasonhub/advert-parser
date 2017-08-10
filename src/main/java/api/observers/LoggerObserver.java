package api.observers;

import api.Advert;
import api.AdvertEvent;
import api.interfaces.Observer;
import api.interfaces.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * оповещает слушателей через консоль
 */
public class LoggerObserver extends SettingsObserver {
    private Logger logger = LoggerFactory.getLogger(LoggerObserver.class);


    public LoggerObserver(Settings settings){
        super(settings);
    }

    @Override
    public void update(List<Advert> ads, AdvertEvent type) {
        if(type != AdvertEvent.ADVERT_NO_CHANGED){
            ads.forEach(
                    ad -> logger.info("Объявление было изменено, тип изменения: {},\n {}", type, ad)
            );
        }
    }
}
