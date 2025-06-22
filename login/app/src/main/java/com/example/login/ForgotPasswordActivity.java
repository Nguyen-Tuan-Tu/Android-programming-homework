package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.et_email);
        confirmButton = findViewById(R.id.btn_confirm);

        confirmButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(ForgotPasswordActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            } else {
                // Kiểm tra email có tồn tại trong Firebase (Firebase logic here)
                boolean emailExists = checkEmailExistsInFirebase(email);
                if (emailExists) {
                    // Chuyển đến màn hình nhập mã OTP
                    Intent intent = new Intent(ForgotPasswordActivity.this, VerificationCodeActivity.class);
                    intent.putExtra("email", email);  // Truyền email qua màn hình OTP
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Email không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkEmailExistsInFirebase(String email) {
        // Logic kiểm tra email tồn tại trong Firebase
        return true;  // Giả sử email tồn tại
    }
}
