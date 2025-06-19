package com.example.tcc_qldon_dat_truoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ql_don_dat_truoc);

        // Đảm bảo bạn gọi findViewById trong phương thức onCreate
        Button OrderStatistics = findViewById(R.id.btn_OrderStatistics);
        Button ViewOrders = findViewById(R.id.btn_ViewOrders);


        // Thiết lập sự kiện  xem thống kê đơn hàng
        OrderStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang MenuActivity
                Intent intent = new Intent(MainActivity.this,   TK_donhang_activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
        // Thiết lập sự kiện  xem danh sách đơn hàng
        ViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang MenuActivity
                Intent intent = new Intent(MainActivity.this,   X_ds_donhang_activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
    }
}