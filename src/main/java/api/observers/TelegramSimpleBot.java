package api.observers;

import api.SettingsParam;
import api.interfaces.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * телеграмм бот
 */
public class TelegramSimpleBot extends TelegramLongPollingBot {
    private Logger logger = LoggerFactory.getLogger(TelegramSimpleBot.class);

    private Settings settings;

    public TelegramSimpleBot(Settings settings){
        this.settings = settings;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());

            try {
                sendMessage(message);
            } catch (TelegramApiException e) {
                logger.warn("send error.", e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "ParserBot";
    }

    @Override
    public String getBotToken() {
        return settings.get(SettingsParam.TELEGRAM_BOT_TOKEN.toString());

    }

    void mySendMessage(String message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.disableWebPagePreview();
        sendMessage.setChatId(settings.get(SettingsParam.TELEGRAM_CHAT_ID.toString()));
        sendMessage.setText(message);
        this.sendMessage(sendMessage);
    }
}
