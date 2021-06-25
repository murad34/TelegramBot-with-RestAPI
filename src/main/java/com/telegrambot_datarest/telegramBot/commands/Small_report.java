package com.telegrambot_datarest.telegramBot.commands;

import com.telegrambot_datarest.DataBase_JDBC.DataBase;
import com.telegrambot_datarest.telegramBot.Bot;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Small_report extends Bot {

    public void methodSmallReport1(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs1;

        try {

            String sql1 = "select count(*) from zadagan where status='открыт' and date BETWEEN NOW() - INTERVAL '31 days' AND NOW();";
            statement = connectDB.connecting().createStatement();
            rs1 = statement.executeQuery(sql1);

            Message message = update.getMessage();

            while (rs1.next()) {

                sendMsg(message,"Количество открытых заказов за последние 31 день : " + rs1.getString("count"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void methodSmallReport2(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs2;

        try {

            String sql2 = "select count(*) from zadagan where status='закрыт' and date BETWEEN NOW() - INTERVAL '31 days' AND NOW();";
            statement = connectDB.connecting().createStatement();
            rs2 = statement.executeQuery(sql2);

            Message message = update.getMessage();

            while (rs2.next()) {

                sendMsg(message,"Количество закрытых заказов за последние 31 день : " + rs2.getString("count"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void methodSmallReport3(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs3;

        try {

            String sql3 = "select count(*) from zadagan where status='в обработке' and date BETWEEN NOW() - INTERVAL '31 days' AND NOW();";
            statement = connectDB.connecting().createStatement();
            rs3 = statement.executeQuery(sql3);

            Message message = update.getMessage();

            while (rs3.next()) {

                sendMsg(message,"Количество заказов в обработке за последние 31 день : " + rs3.getString("count"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
