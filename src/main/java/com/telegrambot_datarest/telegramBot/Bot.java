package com.telegrambot_datarest.telegramBot;

import com.telegrambot_datarest.DataBase_JDBC.DataBase;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.Date;
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

        keyboardFirstRow.add(new KeyboardButton("/List"));
        keyboardFirstRow.add(new KeyboardButton("/Reports"));
        keyboardFirstRow.add(new KeyboardButton("/Info"));

        keyboardSecondRow.add(new KeyboardButton("/1 day"));
        keyboardSecondRow.add(new KeyboardButton("/1 week"));
        keyboardSecondRow.add(new KeyboardButton("/1 month"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

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
                Date date = rs.getDate("date");

                System.out.printf("%d. %s %s :\n Phone Number : %s \n Products Code : %s \n Items : %s  \n \n",
                        id, name, surname, phone_number, products_code, items);

                String mm = "Id : " + id +
                        "\n Name : " + name +
                        "\n Surname : " + surname +
                        "\n Phone Number : " + phone_number +
                        "\n Products Code : " + products_code +
                        "\n Items : " + items +
                        "\n Date : " + date;

                sendMsg(message, mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        //---------------------------------------

        if (message != null && message.hasText()) {

            switch (message.getText()) {

                case "/start":

                    sendMsg(message, "Hi,I am Telegram Bot of Elektro Park \n" +
                            "Choose please command");
                    break;

                //------------------- LIST -------------------------------------------------

                case "/list":

                    methodForList(update);

                    break;

                // ----------------- SEARCH ------------------------------------------------

                case "a":

                    break;

                //----------------------------------------------------------------------------

                case "b":



                    break;

                //----------------------------------------------------------------------------

                case "c":



                    break;

                //----------------------------------------------------------------------------

                case "d":



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

    //---------------------------------------------------------------------------------------------

    public String getBotUsername() {
        return "ElektroPark_bot";
    }

    public String getBotToken() {
        return "1570630985:AAH_IuQtUuzRccZctKOfSjHSpx89XrpiFpw";
    }

}
