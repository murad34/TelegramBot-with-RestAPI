package com.telegrambot_datarest.telegramBot;

import com.telegrambot_datarest.telegramBot.reports.Full_report;
import com.telegrambot_datarest.telegramBot.reports.Small_report;
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

                case "c":

                    break;

                //----------------------------------------------------------------------------

                case "d":

                    break;

                //----------------------------------------------------------------------------

                case "Info":

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
