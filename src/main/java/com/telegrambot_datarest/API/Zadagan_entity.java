package com.telegrambot_datarest.API;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "zadagan")
public class Zadagan_entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "products_code")
    private String products_code;

    @Column(name = "items")
    private String items;

    @Column(name = "date")
    private Date date;

    public Zadagan_entity() {
    }

    public Zadagan_entity(String name, String surname, String address, String phone_number, String products_code, String items,Date date) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone_number = phone_number;
        this.products_code = products_code;
        this.items = items;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getProducts_code() {
        return products_code;
    }

    public void setProducts_code(String products_code) {
        this.products_code = products_code;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Zadagan_entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", products_code='" + products_code + '\'' +
                ", items='" + items + '\'' +
                ", date=" + date +
                '}';
    }

}
