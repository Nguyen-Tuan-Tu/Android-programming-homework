package com.example.tlucanteenconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu_dexuat_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dexuat); // Đảm bảo sử dụng đúng layout của Menu_dexuat_activity

        // Lấy tham chiếu đến các TextView cho các tab
        TextView txtBreakfast = findViewById(R.id.txt_breakfast);  // TextView cho Bữa sáng
        TextView txtDrink = findViewById(R.id.txt_drink);          // TextView cho Đồ uống (nếu có)

        // Thiết lập sự kiện click cho "Bữa sáng"
        txtBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Bữa sáng
                Intent intent = new Intent(Menu_dexuat_activity.this, Menu_buasang_activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });

        // Thiết lập sự kiện click cho "Đồ uống"
        txtDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đồ uống
                Intent intent = new Intent(Menu_dexuat_activity.this, Menu_douong_activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
    }
}