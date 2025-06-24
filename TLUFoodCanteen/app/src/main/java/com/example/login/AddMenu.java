package com.example.login;

public class AddMenu {
    public String name;
    public String foodChoice;
    public String notes;

    // Constructor mặc định yêu cầu cho Firebase
    public AddMenu() {
    }

    public AddMenu(String name, String foodChoice, String notes) {
        this.name = name;
        this.foodChoice = foodChoice;
        this.notes = notes;
    }
}
