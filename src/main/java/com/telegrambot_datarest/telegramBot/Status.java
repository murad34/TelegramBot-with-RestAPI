package com.telegrambot_datarest.telegramBot;

import com.telegrambot_datarest.DataBase_JDBC.DataBase;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Status extends Bot {

    public void methodForStatus(Update update) {

        Message message = update.getMessage();

        String text = message.getText();

        int length = text.length();

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;
        String id_substring = text.substring(0, length - 2);

        switch (text.substring(length - 1, length)) {

            case "a":

                try {

                    String sql = "update zadagan set status = 'открыт' where id = " + id_substring;
                    statement = connectDB.connecting().createStatement();
                    statement.executeUpdate(sql);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                sendMsg(message, "Done !");

                updateOrder(update);

                break;

            case "b":

                try {

                    String sql = "update zadagan set status = 'в обработке' where id = " + id_substring;
                    statement = connectDB.connecting().createStatement();
                    statement.executeUpdate(sql);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                sendMsg(message, "Done ✓");

                updateOrder(update);

                break;

            case "c":

                try {

                    String sql = "update zadagan set status = 'закрыт' where id = " + id_substring;
                    statement = connectDB.connecting().createStatement();
                    statement.executeUpdate(sql);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                sendMsg(message, "Done ✓");

                updateOrder(update);

                break;

        }

    }

    public void updateOrder(Update update) {

        Message message = update.getMessage();

        String text = message.getText();

        int length = text.length();

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;
        String id_substring = text.substring(0, length - 2);

        try {

            String sql = "select * from zadagan where id = " + id_substring;
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
                String address = rs.getString("address");
                int price = rs.getInt("price");

                String mm = "\uD83E\uDD16 Id : " + id +
                        "\n \uD83D\uDE4B\u200D♂️ Name : " + name + " " +surname +
                        "\n ☎️ Phone Number : " + phone_number +
                        "\n 🏠 Address : " + address +
                        "\n \uD83D\uDCE6 Products Code : " + products_code +
                        "\n ✨ Items : " + items +
                        "\n 🏷️ Price : " + price +
                        "\n \uD83D\uDCC5 Date : " + date +
                        "\n ❕ Status : " + status ;

                sendMsg(message, mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
