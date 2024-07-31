package uz.sicnt.telegrambottest.bot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Baxodir_Pardaboyev
 * Date: 7/30/2024 8:39 PM
 * Project:telegramBotTest
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "bot")
public class BotConfig {
    private String token;
    private String username;

}
