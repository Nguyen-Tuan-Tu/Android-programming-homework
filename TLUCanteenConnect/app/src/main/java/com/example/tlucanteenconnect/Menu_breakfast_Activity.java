package com.example.tlucanteenconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu_breakfast_Activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gán layout cho activity này
        setContentView(R.layout.activity_menu_breakfast);  // Đảm bảo rằng bạn đã tạo layout activity_menu_buasang.xml
        // Lấy tham chiếu đến các TextView cho các tab
        TextView txtRecomment = findViewById(R.id.txt_recomment);          // TextView cho Bữa trưa (nếu có)
        TextView txtDrink = findViewById(R.id.txt_drink);          // TextView cho Đồ uống (nếu có)


        // Thiết lập sự kiện click cho "Đồ uống"
        txtDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đồ uống
                Intent intent = new Intent(Menu_breakfast_Activity.this, Menu_drink_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });

        // Thiết lập sự kiện click cho "Đề xuất" (nếu cần)
        txtRecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đề xuất (nếu có)
                Intent intent = new Intent(Menu_breakfast_Activity.this, Menu_foryou_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
        Button Login = findViewById(R.id.btn_Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đồ uống
                Intent intent = new Intent(Menu_breakfast_Activity.this, Login_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
    }
}
