package com.example.login;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountInfoActivity extends AppCompatActivity {

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_admin); // Tên file layout XML thứ hai

        // Nút quay lại
//        backButton = findViewById(R.id.back_button);
//        backButton.setOnClickListener(v -> {
//            // Đóng Activity này, quay về màn trước
//            finish();
//        });

        // TODO: Thêm các thao tác với EditText, ImageView, v.v. nếu muốn load data
    }
}
