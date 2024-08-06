package uz.sicnt.telegrambottest.bot;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Scanner;

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

    Scanner scanner = new Scanner(System.in);

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Long id = update.getMessage().getFrom().getId();

            /*

            Let's go _

             */

            System.out.println(update.getMessage().getText());
            System.out.print("WRITE: ");
            String ans = scanner.nextLine();
            execute(SendMessage.builder().chatId(id).text(ans).build());

//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(String.valueOf(id));
//            sendMessage.setText(update.getMessage().getText());
//
//            // inline
//            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//
//            InlineKeyboardButton ikb = new InlineKeyboardButton();
//            ikb.setText("Salom");
//            ikb.setCallbackData("Hi");
//
//            InlineKeyboardButton ikb2 = new InlineKeyboardButton();
//            ikb2.setText("Salom 2");
//            ikb2.setUrl("https://t.me/anasxonmaxmudogli_bot");
//
//            InlineKeyboardButton ikb3 = new InlineKeyboardButton();
//            ikb3.setText("Salom 3");
//            ikb3.setWebApp(new WebAppInfo("https://evos.uz/"));
//
//            inlineKeyboardMarkup.setKeyboard(List.of(
//                    List.of(ikb),
//                    List.of(ikb2),
//                    List.of(ikb3)
//            ));
//            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
//
//
//            execute(sendMessage);
        }

//        if (update.hasCallbackQuery()) {
//            CallbackQuery callbackQuery = update.getCallbackQuery();
//            String data = callbackQuery.getData();
//            System.out.println(data);
//        }
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
