package com.telegrambot_datarest.telegramBot.commands;

import com.telegrambot_datarest.DataBase_JDBC.DataBase;
import com.telegrambot_datarest.telegramBot.Bot;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Orders extends Bot {

    public void methodOrders1(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;

        try {

            String sql = "select * from zadagan order by id limit 10";
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

                sendMsg(message,mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void methodOrders2(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;

        try {

            String sql = "select * from zadagan where status = 'открыт' order by id limit 20";
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

                sendMsg(message,mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void methodOrders3(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;

        try {

            String sql = "select * from zadagan where status = 'закрыт' order by id limit 20";
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

                sendMsg(message,mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void methodOrders4(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;

        try {

            String sql = "select * from zadagan where status = 'в обработке' order by id limit 20";
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

                sendMsg(message,mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void methodOrders5(Update update) {

        Statement statement;
        DataBase connectDB = new DataBase();
        ResultSet rs;

        try {

            String sql = "select * from zadagan where status = 'закрыт' and date BETWEEN NOW() - INTERVAL '31 days' AND NOW()";
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

                sendMsg(message,mm);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
