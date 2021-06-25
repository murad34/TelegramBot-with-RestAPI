package com.telegrambot_datarest.telegramBot.commands;

import com.telegrambot_datarest.DataBase_JDBC.DataBase;
import com.telegrambot_datarest.telegramBot.Bot;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetWholeList extends Bot {

    public void methodForList(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;

        try {

            String sql = "select * from zadagan order by id";
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
                String status = rs.getString("status");

//                System.out.printf("%d. %s %s :\n Phone Number : %s \n Products Code : %s \n Items : %s  \n \n",
//                        id, name, surname, phone_number, products_code, items);

                String mm = "Id : " + id +
                        "\n Name : " + name +
                        "\n Surname : " + surname +
                        "\n Phone Number : " + phone_number +
                        "\n Products Code : " + products_code +
                        "\n Items : " + items +
                        "\n Date : " + date +
                        "\n Status : " + status ;

                sendMsg(message, mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
