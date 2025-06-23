package com.example.login;

public class Menu {

    private String day;
    private String dish1;
    private String dish2;
    private String dish3;

    // Default constructor for Firebase
    public Menu() {}

    // Constructor with parameters
    public Menu(String dish1, String dish2, String dish3) {
        this.dish1 = dish1;
        this.dish2 = dish2;
        this.dish3 = dish3;
    }

    // Getter and Setter for 'day'
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    // Getter and Setter for 'dish1'
    public String getDish1() {
        return dish1;
    }

    public void setDish1(String dish1) {
        this.dish1 = dish1;
    }

    // Getter and Setter for 'dish2'
    public String getDish2() {
        return dish2;
    }

    public void setDish2(String dish2) {
        this.dish2 = dish2;
    }

    // Getter and Setter for 'dish3'
    public String getDish3() {
        return dish3;
    }

    public void setDish3(String dish3) {
        this.dish3 = dish3;
    }
}
