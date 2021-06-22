package com.telegrambot_datarest.telegramBot;

import com.telegrambot_datarest.DataBase_JDBC.DataBase;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public void sendMsg(Message message, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void setButtons(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/Список"));
        keyboardFirstRow.add(new KeyboardButton("/Поиск"));
        keyboardFirstRow.add(new KeyboardButton("/Info"));

        keyboardSecondRow.add(new KeyboardButton("/1 day"));
        keyboardSecondRow.add(new KeyboardButton("/1 week"));
        keyboardSecondRow.add(new KeyboardButton("/1 month"));

        keyboardRowList.add(keyboardFirstRow);
//        keyboardRowList.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

    public static SendMessage sendInlineKeyBoardMessage(long chatId) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;

        //--------------------------------

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("1 day");

        try {

            String sql = "select * from zadagan where id = 1";
            statement = connectDB.connecting().createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone_number = rs.getString("phone_number");
                String products_code = rs.getString("products_code");
                String items = rs.getString("items");

                String mm = "Id : " + id +
                        "\n Name : " + name +
                        "\n Surname : " + surname ;
//                        "\n Phone Number : " + phone_number ;
//                        "\n Products Code : " + products_code +
//                        "\n Items : " + items ;

                inlineKeyboardButton1.setCallbackData(mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        inlineKeyboardButton2.setText("1 month");
        inlineKeyboardButton2.setCallbackData(" Last 30 days ");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("1 week").setCallbackData(" Last 7 days "));

        keyboardButtonsRow2.add(inlineKeyboardButton2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return new SendMessage().setChatId(chatId).setText("Please choose").setReplyMarkup(inlineKeyboardMarkup);
    }

    public void methodForList(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;

        try {

            String sql = "select * from zadagan";
            statement = connectDB.connecting().createStatement();
            rs = statement.executeQuery(sql);

            Message message = update.getMessage();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone_number = rs.getString("phone_number");
                String products_code = rs.getString("products_code");
                String items = rs.getString("items");

                System.out.printf("%d. %s %s :\n Phone Number : %s \n Products Code : %s \n Items : %s  \n \n",
                        id, name, surname, phone_number, products_code, items);

                String mm = "Id : " + id +
                        "\n Name : " + name +
                        "\n Surname : " + surname +
                        "\n Phone Number : " + phone_number +
                        "\n Products Code : " + products_code +
                        "\n Items : " + items;

                sendMsg(message, mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void methodForSearch(Update update) {

        if(update.hasMessage()){
            if(update.getMessage().hasText()){
                if(update.getMessage().getText().equals("/Поиск")){
                    try {
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else if(update.hasCallbackQuery()){
            try {
                execute(new SendMessage().setText(
                        update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onUpdateReceived(Update update) {

        methodForSearch(update);

        Message message = update.getMessage();

        if (message != null && message.hasText()) {

            switch (message.getText()) {

                case "/start":

                    sendMsg(message, "Hi,I am Telegram Bot of Elektro Park \n" +
                            "Choose please command");
                    break;

                //------------------- LIST -------------------------------------------------

                case "/Список":

                    methodForList(update);

                    break;

                // ----------------- SEARCH ------------------------------------------------

                case "/Поиск":

//                    methodForSearch(update);

                    break;

                //----------------------------------------------------------------------------

                case "/Info":

                    sendMsg(message, "Telegram Bot of Zadagan Company !");

                    break;

                default:

                    sendMsg(message, "I am sorry,but I can not understand you !");

            }

        }

    }


    public String getBotUsername() {
        return "ElektroPark_bot";
    }

    public String getBotToken() {
        return "1570630985:AAH_IuQtUuzRccZctKOfSjHSpx89XrpiFpw";
    }

}
