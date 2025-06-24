package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ManageOder_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_oder);

        // Đảm bảo bạn gọi findViewById trong phương thức onCreate
        Button OrderStatistics = findViewById(R.id.btn_OrderStatistics);
        Button ViewOrders = findViewById(R.id.btn_ViewOrders);


        // Thiết lập sự kiện  xem thống kê đơn hàng
        OrderStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang MenuActivity
                Intent intent = new Intent(ManageOder_Activity.this,   StatisticalOder_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
        // Thiết lập sự kiện  xem danh sách đơn hàng
        ViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang MenuActivity
                Intent intent = new Intent(ManageOder_Activity.this,   ViewListOder_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
    }
}