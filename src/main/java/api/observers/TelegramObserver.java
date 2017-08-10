package api.observers;

import api.Advert;
import api.AdvertEvent;
import api.SettingsParam;
import api.interfaces.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;
import java.util.stream.Collectors;

public class TelegramObserver extends SettingsObserver {
    private Logger logger = LoggerFactory.getLogger(TelegramObserver.class);
    private TelegramSimpleBot telegramBot;

    private AdvertEvent type;


    public TelegramObserver(Settings settings) {
        super(settings);
        telegramBot = new TelegramSimpleBot(settings);
    }

    @Override
    public void update(List<Advert> ads, AdvertEvent type) {

        this.type = type;
        GroupAdvertsToMessage groupMessages = new GroupAdvertsToMessage(getMessages(ads));
        List<String> messages = groupMessages.getGroupMessages();
        messages.forEach(this::sendMessage);
    }

    private List<String> getMessages(List<Advert> ads){
        return ads.stream()
                .map(this::getMessage)
                .collect(Collectors.toList());
    }

    private void sendMessage(String message){
        try {
           telegramBot.mySendMessage(message);
        } catch (TelegramApiException e) {
            logger.warn("", e);
        }
    }

    private String getTitle(){
        String title = "<b>New Advert</b>";
        if(type == AdvertEvent.ADVERT_UPDATED){
            title = "<b>Advert Updated</b>";
        }
        return title;
    }

    private String getMessage(Advert ad){
        return getTitle() +"\n"+ ad.getTitle()+"\n"+ad.getUrl()+"\n\n";
    }


}
