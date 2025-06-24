package com.example.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticalOder_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Đặt layout cho activity bằng cách sử dụng setContentView
        setContentView(R.layout.activity_statisticaloder);  // Trỏ đến file XML tương ứng
        ImageButton Back = findViewById(R.id.btn_Back);
        // Thiết lập sự kiện  xem danh sách đơn hàng
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang MenuActivity
                Intent intent = new Intent(StatisticalOder_Activity.this,   ManageOder_Activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });
        TextView statusBM = findViewById(R.id.tvStatusBM);
        String status = getIntent().getStringExtra("BM_STATUS");
        if (status != null) {
            statusBM.setText(status);
        }
    }
}
