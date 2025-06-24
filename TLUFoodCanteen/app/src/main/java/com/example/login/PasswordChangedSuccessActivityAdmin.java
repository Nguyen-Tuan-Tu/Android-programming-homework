package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordChangedSuccessActivityAdmin extends AppCompatActivity {

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_changed_success_admin);

        confirmButton = findViewById(R.id.btn_confirm);

        confirmButton.setOnClickListener(v -> {
            // Quay lại màn hình đăng nhập
            Intent intent = new Intent(PasswordChangedSuccessActivityAdmin.this, AdminLoginActivity.class);
            startActivity(intent);
            finish();  // Đóng màn hình này để người dùng không quay lại được
        });
    }
}
