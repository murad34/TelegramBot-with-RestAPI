package com.telegrambot_datarest.telegramBot;

import com.telegrambot_datarest.telegramBot.commands.Full_report;
import com.telegrambot_datarest.telegramBot.commands.GetWholeList;
import com.telegrambot_datarest.telegramBot.commands.Orders;
import com.telegrambot_datarest.telegramBot.commands.Small_report;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    public void sendMsg(Message message, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            Buttons buttons = new Buttons();
            buttons.setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg2(Message message, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);

        try {
            Buttons buttons = new Buttons();
            buttons.setButtons2(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        Integer private_bot = message.getFrom().getId();

        System.out.println(private_bot);

        //---------------------------------------

//        if (message.getFrom().getId().equals(67332)) {

            if (message != null && message.hasText()) {

                if (Character.isDigit(message.getText().charAt(0))) {

                    Status status = new Status();
                    status.methodForStatus(update);

                } else {

                    switch (message.getText()) {

                        case "/start":

                            sendMsg(message, "Hi,I am Telegram Bot of Zadagan company \n" +
                                    "Please press button 'Commands' below");

                            break;

                        //------------------- COMMANDS -------------------------------------------------

                        case "Commands":

                            sendMsg(message, "In order to see all commands enter :  /  ");

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

                        case "Orders":

                            sendMsg2(message, "\uD83D\uDD1F : Последние 10 заказов \n" +
                                    "\uD83D\uDFE2 : Последние 20 открытых заказов \n" +
                                    "\uD83D\uDD34 : Последние 20 закрытых заказов \n" +
                                    "\uD83D\uDFE1 : Последние 20 заказов в обработке \n" +
                                    "\uD83D\uDFE5  : Закрытые заказы за 31 день ");

                            break;

                        //----------------------------------------------------------------------------

                        case "\uD83D\uDD1F":

                            Orders orders1 = new Orders();

                            sendMsg2(message, "Последние 10 заказов : ");
                            orders1.methodOrders1(update);

                            break;

                        //----------------------------------------------------------------------------

                        case "\uD83D\uDFE2":

                            Orders orders2 = new Orders();

                            sendMsg2(message, "Последние 20 открытых заказов : ");
                            orders2.methodOrders2(update);

                            break;

                        //----------------------------------------------------------------------------

                        case "\uD83D\uDD34":

                            Orders orders3 = new Orders();

                            sendMsg2(message, "Последние 20 закрытых заказов : ");
                            orders3.methodOrders3(update);

                            break;

                        //----------------------------------------------------------------------------

                        case "\uD83D\uDFE1":

                            Orders orders4 = new Orders();

                            sendMsg2(message, "Последние 20 заказов в обработке : ");
                            orders4.methodOrders4(update);

                            break;

                        //----------------------------------------------------------------------------

                        case "\uD83D\uDFE5":

                            Orders orders5 = new Orders();

                            sendMsg2(message, "Закрытые заказы за 31 день : ");
                            orders5.methodOrders5(update);

                            break;

                        //----------------------------------------------------------------------------

                        case "back":

                            sendMsg(message, "Please choose a button");

                            break;

                        //----------------------------------------------------------------------------

                        case "Status":

                            sendMsg(message, "Для того чтобы изменить статус введите : \n" +
                                    "\n id,status \n" +
                                    "\n Status : \n" +
                                    "a - Открыт \n" +
                                    "b - В обрабокте \n" +
                                    "c - Закрыт \n" +
                                    "\n Пример : " +
                                    "\n 7,b");

                            break;

                        //----------------------------------------------------------------------------

                        case "wthtr":

                            sendMsg(message, "srbsg");

                            break;

                        //----------------------------------------------------------------------------

                        case "hiol":

                            break;

                        default:

                            sendMsg(message, "I am sorry,but I can not understand you !");

                    }

                }

            }

        }

//    }

    //---------------------------------------------------------------------------------------------

    public String getBotUsername() {
        return "ElektroPark_bot";
    }

    public String getBotToken() {
        return "1570630985:AAH_IuQtUuzRccZctKOfSjHSpx89XrpiFpw";
    }

}
