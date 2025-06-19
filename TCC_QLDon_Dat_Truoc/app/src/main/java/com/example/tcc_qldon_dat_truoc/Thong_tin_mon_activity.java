package com.example.tcc_qldon_dat_truoc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Thong_tin_mon_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_mon); // Trỏ đến file XML tương ứng

        ImageButton Back = findViewById(R.id.btn_Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển về Activity X_ds_donhang_activity
                Intent intent = new Intent(Thong_tin_mon_activity.this, X_ds_donhang_activity.class);
                startActivity(intent);  // Chạy Activity mới
            }
        });

        Button CFfood = findViewById(R.id.btn_CFfoodBM);

        CFfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang X_ds_donhang_activity
                Intent intent = new Intent(Thong_tin_mon_activity.this, X_ds_donhang_activity.class);

                // ✅ Gửi thông tin cho biết là tích xanh cần hiển thị
                intent.putExtra("CHECK_ICON_STATE", true);
                intent.putExtra("BM_STATUS", "Đang chuẩn bị");
                // Chạy activity
                startActivity(intent);

            }
        });
    }
}

