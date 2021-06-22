package com.telegrambot_datarest;

import com.telegrambot_datarest.telegramBot.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class TelegrambotDatarestApplication {

    public static void main(String[] args) {

        //---- Zadagan

        SpringApplication.run(TelegrambotDatarestApplication.class, args);

        //---- Bot

        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }

}
