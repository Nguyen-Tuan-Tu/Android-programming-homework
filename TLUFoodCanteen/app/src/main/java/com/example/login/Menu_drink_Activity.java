package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Menu_drink_Activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drink);  // Gán layout cho Activity
        // Lấy tham chiếu đến các TextView cho các tab
        TextView txtBreakfast = findViewById(R.id.txt_breakfast);  // TextView cho Bữa sáng
        TextView txtRecomment = findViewById(R.id.txt_recomment);  // TextView cho Đề xuất
        // Thiết lập sự kiện click cho "Bữa sáng"
        txtBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đồ uống
                Intent intent = new Intent(Menu_drink_Activity.this, Menu_breakfast_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });

        // Thiết lập sự kiện click cho "Đề xuất" (nếu cần)
        txtRecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đề xuất (nếu có)
                Intent intent = new Intent(Menu_drink_Activity.this, Menu_foryou_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
        Button Login = findViewById(R.id.btn_Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đồ uống
                Intent intent = new Intent(Menu_drink_Activity.this, AdminLoginActivity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
    }
}
