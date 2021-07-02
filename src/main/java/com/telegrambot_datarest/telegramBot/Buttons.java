package com.telegrambot_datarest.telegramBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Buttons {

    public void setButtons(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Commands"));
        keyboardFirstRow.add(new KeyboardButton("Orders"));
        keyboardFirstRow.add(new KeyboardButton("Status"));

        keyboardRowList.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

    public void setButtons2(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add(new KeyboardButton("\uD83D\uDD1F"));
        keyboardSecondRow.add(new KeyboardButton("\uD83D\uDFE2"));
        keyboardSecondRow.add(new KeyboardButton("\uD83D\uDD34"));
        keyboardSecondRow.add(new KeyboardButton("\uD83D\uDFE1"));
        keyboardSecondRow.add(new KeyboardButton("\uD83D\uDFE5"));
        keyboardSecondRow.add(new KeyboardButton("back"));

        keyboardRowList.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

}
