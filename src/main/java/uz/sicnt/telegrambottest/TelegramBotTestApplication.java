package uz.sicnt.telegrambottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.sicnt.telegrambottest.bot.BotConfig;

@SpringBootApplication
@EnableConfigurationProperties(BotConfig.class)
public class TelegramBotTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotTestApplication.class, args);
    }

}
