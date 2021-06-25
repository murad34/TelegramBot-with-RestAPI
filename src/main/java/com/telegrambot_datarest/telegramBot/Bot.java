package com.telegrambot_datarest.telegramBot;

import com.telegrambot_datarest.DataBase_JDBC.DataBase;
import com.telegrambot_datarest.telegramBot.commands.Full_report;
import com.telegrambot_datarest.telegramBot.commands.GetWholeList;
import com.telegrambot_datarest.telegramBot.commands.Orders;
import com.telegrambot_datarest.telegramBot.commands.Small_report;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bot extends TelegramLongPollingBot {

    public void sendMsg(Message message, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            Buttons buttons = new Buttons();
            buttons.setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        //---------------------------------------

        if (message != null && message.hasText()) {

            if (message.getText().startsWith("id = ") || message.getText().startsWith("id=")) {

                Statement statement;
                DataBase connectDB = new DataBase();
                ResultSet rs;

                try {

                    String sql = "select * from zadagan where " + message.getText();
                    statement = connectDB.connecting().createStatement();
                    rs = statement.executeQuery(sql);

                    while (rs.next()) {

                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String surname = rs.getString("surname");
                        String phone_number = rs.getString("phone_number");
                        String products_code = rs.getString("products_code");
                        String items = rs.getString("items");
                        Date date = rs.getDate("date");
                        String status = rs.getString("status");

                        String mm = "Id : " + id +
                                "\n Name : " + name +
                                "\n Surname : " + surname +
                                "\n Phone Number : " + phone_number +
                                "\n Products Code : " + products_code +
                                "\n Items : " + items +
                                "\n Date : " + date +
                                "\n Status : " + status;

                        sendMsg(message, mm);

                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else {

                switch (message.getText()) {

                    case "/start":

                        sendMsg(message, "Hi,I am Telegram Bot of Zadagan company \n" +
                                "Please press button 'Commands' below");

                        break;

                    //------------------- COMMANDS -------------------------------------------------

                    case "Commands":

                        sendMsg(message, "In order to see all commands enter /");

                        break;

                    //------------------- LIST -------------------------------------------------


                    case "/list":

                        GetWholeList getWholeList = new GetWholeList();

                        getWholeList.methodForList(update);

                        break;

                    // --------------------------------------------------------------------------

                    case "/small_report":

                        Small_report small_report = new Small_report();

                        small_report.methodSmallReport1(update);
                        small_report.methodSmallReport2(update);
                        small_report.methodSmallReport3(update);

                        break;

                    //----------------------------------------------------------------------------

                    case "/full_report":

                        Full_report full_report = new Full_report();

                        full_report.methodFullReport1(update);
                        full_report.methodFullReport2(update);
                        full_report.methodFullReport3(update);
                        full_report.methodFullReport4(update);
                        full_report.methodFullReport5(update);
                        full_report.methodFullReport6(update);
                        full_report.methodFullReport7(update);

                        break;

                    //----------------------------------------------------------------------------

                    case "/orders":

                        sendMsg(message, "Orders-1 : Последние 10 заказов \n" +
                                "Orders-2 : Последние 20 открытых заказов \n" +
                                "Orders-3 : Последние 20 закрытых заказов \n" +
                                "Orders-4 : Последние 20 заказов в обработке \n" +
                                "Orders-5 : Закрытые заказы за 31 день \n" +
                                "\n Введите : Orders-n \n" +
                                "(вместо n используйте числа 1-5)");

                        break;

                    //----------------------------------------------------------------------------

                    case "Orders-1":

                        Orders orders1 = new Orders();

                        sendMsg(message, "Последние 10 заказов : ");
                        orders1.methodOrders1(update);

                        break;

                    //----------------------------------------------------------------------------

                    case "Orders-2":

                        Orders orders2 = new Orders();

                        sendMsg(message, "Последние 20 открытых заказов : ");
                        orders2.methodOrders2(update);

                        break;

                    //----------------------------------------------------------------------------

                    case "Orders-3":

                        Orders orders3 = new Orders();

                        sendMsg(message, "Последние 20 закрытых заказов : ");
                        orders3.methodOrders3(update);

                        break;

                    //----------------------------------------------------------------------------

                    case "Orders-4":

                        Orders orders4 = new Orders();

                        sendMsg(message, "Последние 20 заказов в обработке : ");
                        orders4.methodOrders4(update);

                        break;

                    //----------------------------------------------------------------------------

                    case "Orders-5":

                        Orders orders5 = new Orders();

                        sendMsg(message, "Закрытые заказы за 31 день : ");
                        orders5.methodOrders5(update);

                        break;

                    //----------------------------------------------------------------------------

                    case "Info":

                        sendMsg(message, "Telegram Bot of Zadagan Company !");

                        break;

                    //----------------------------------------------------------------------------

                    case "Status":

                        sendMsg(message, "If you want to change status of any order,you should enter : \n" +
                                "\n id,status,order \n" +
                                "\n Вместо id введите id заказа \n" +
                                "\n Вместо status введите одно из перечисленных : \n" +
                                "открыт,закрыт,в обработке \n" +
                                "\n Example : \n" +
                                "7,закрыт,order");

                        break;

                    //----------------------------------------------------------------------------

                    case "Search":

                        sendMsg(message, "Enter please : \n" +
                                "\n id = n \n" +
                                "\n Вместо n введите id заказа");

                        break;

                    default:

//                        sendMsg(message, "I am sorry,but I can not understand you !");

                }

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
