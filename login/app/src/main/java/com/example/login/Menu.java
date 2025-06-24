package com.example.login;

import java.util.List;

public class Menu {

    private String day;
    private String name;
    private List<String> food;
    private List<String> notes;

    // --- THÊM VÀO ---
    // Constructor rỗng bắt buộc cho Firebase
    public Menu() {
    }
    // --- KẾT THÚC THÊM VÀO ---

    // Constructor để tạo đối tượng từ code
    public Menu(String day, String name, List<String> food, List<String> notes) {
        this.day = day;
        this.name = name;
        this.food = food;
        this.notes = notes;
    }

    // Getter và Setter (Giữ nguyên như của bạn)
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFood() {
        return food;
    }

    public void setFood(List<String> food) {
        this.food = food;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
}