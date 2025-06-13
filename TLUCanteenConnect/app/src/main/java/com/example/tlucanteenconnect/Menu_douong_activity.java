package com.example.tlucanteenconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Menu_douong_activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_douong);  // Gán layout cho Activity
        // Lấy tham chiếu đến các TextView cho các tab
        TextView txtBreakfast = findViewById(R.id.txt_breakfast);  // TextView cho Bữa sáng
        TextView txtRecomment = findViewById(R.id.txt_recomment);  // TextView cho Đề xuất
        // Thiết lập sự kiện click cho "Bữa sáng"
        txtBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đồ uống
                Intent intent = new Intent(Menu_douong_activity.this, Menu_buasang_activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });

        // Thiết lập sự kiện click cho "Đề xuất" (nếu cần)
        txtRecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Activity Menu Đề xuất (nếu có)
                Intent intent = new Intent(Menu_douong_activity.this, Menu_dexuat_activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
    }
}
