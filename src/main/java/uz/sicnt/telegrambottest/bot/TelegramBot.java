package uz.sicnt.telegrambottest.bot;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * Author: Baxodir_Pardaboyev
 * Date: 7/30/2024 8:33 PM
 * Project:telegramBotTest
 */
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
//            simpleSendMessage(message);

            User from = message.getFrom();
            Long id = from.getId();

            if (message.hasContact()) {
                Contact contact = message.getContact();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(id);
                sendMessage.setText(contact.toString());
                execute(sendMessage);

                sendMessage = SendMessage.builder()
                        .chatId(id)
                        .text("Hi, " + from.getFirstName() + ". Share contact")
                        .replyMarkup(
                                ReplyKeyboardMarkup.builder()
                                        .resizeKeyboard(true)
                                        .keyboardRow(new KeyboardRow(List.of(
                                                KeyboardButton.builder()
                                                        .text("Youtube")
                                                        .webApp(new WebAppInfo("https://www.youtube.com"))
                                                        .build()
                                        )))
                                        .build()
                        )
                        .build();

                execute(sendMessage);

                return;
            }

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(id)
                    .text("Hi, " + from.getFirstName() + ". Share contact")
                    .replyMarkup(
                            ReplyKeyboardMarkup.builder()
                                    .resizeKeyboard(true)
                                    .keyboardRow(new KeyboardRow(List.of(
                                            KeyboardButton.builder()
                                                    .text("Share contact 📞")
                                                    .requestContact(true)
                                                    .build()
                                    )))
                                    .build()
                    )
                    .build();

            execute(sendMessage);

        }
    }

    private void simpleSendMessage(Message message) throws TelegramApiException {
        execute(SendMessage.builder()
                .chatId(message.getFrom().getId())
                .text(message.getText() + " Assalom")
                .replyToMessageId(message.getMessageId())
                .build());
    }

    @Override
    public String getBotUsername() {
        return botConfig.getUsername();
    }
}
