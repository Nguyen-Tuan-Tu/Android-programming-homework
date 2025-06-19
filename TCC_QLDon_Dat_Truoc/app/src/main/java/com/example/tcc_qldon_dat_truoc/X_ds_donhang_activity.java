package com.example.tcc_qldon_dat_truoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class X_ds_donhang_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemds_donhang); // Trỏ đến file XML tương ứng

        // Nhận trạng thái từ Intent
        boolean isVisible = getIntent().getBooleanExtra("CHECK_ICON_STATE", false);

        ImageView checkIcon = findViewById(R.id.checkIconBM);

        // Thay đổi trạng thái visibility của checkIcon
        checkIcon.setVisibility(isVisible ? View.VISIBLE : View.GONE);  // Nếu true thì hiển thị, ngược lại ẩn

        // Thiết lập các sự kiện khác
        ImageButton Back = findViewById(R.id.btn_Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(X_ds_donhang_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button InfoBM = findViewById(R.id.btn_infoBM);
        InfoBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến Thong_tin_mon_activity
                Intent intent = new Intent(X_ds_donhang_activity.this, Thong_tin_mon_activity.class);
                startActivity(intent);
            }
        });

    }
}

