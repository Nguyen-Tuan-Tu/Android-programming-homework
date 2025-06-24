package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class VerificationCodeActivityAdmin extends AppCompatActivity {

    private EditText verificationCodeEditText;
    private Button confirmButton;
    private TextView otpTextView;
    private String generatedOtp;  // Biến lưu mã OTP
    private String email;  // Email được truyền qua Intent từ ForgotPasswordActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code_admin);

        verificationCodeEditText = findViewById(R.id.et_verification_code);
        confirmButton = findViewById(R.id.btn_confirm);
        otpTextView = findViewById(R.id.tv_generated_otp);

        // Lấy email từ Intent
        email = getIntent().getStringExtra("email");

        // Sinh mã OTP ngẫu nhiên
        generatedOtp = generateOtp();
        otpTextView.setText("Mã OTP của bạn là: " + generatedOtp);  // Hiển thị mã OTP trên giao diện

        confirmButton.setOnClickListener(v -> {
            String enteredOtp = verificationCodeEditText.getText().toString().trim();
            if (enteredOtp.equals(generatedOtp)) {
                // Nếu mã OTP đúng
                Toast.makeText(VerificationCodeActivityAdmin.this, "Mã OTP đúng! Thực hiện thao tác tiếp theo.", Toast.LENGTH_SHORT).show();
                // Chuyển sang màn hình thay đổi mật khẩu
                Intent intent = new Intent(VerificationCodeActivityAdmin.this, ChangePasswordActivityAdmin.class);
                intent.putExtra("email", email);  // Truyền email qua màn hình thay đổi mật khẩu
                startActivity(intent);
            } else {
                // Nếu mã OTP sai
                Toast.makeText(VerificationCodeActivityAdmin.this, "Mã OTP sai, vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Phương thức sinh mã OTP ngẫu nhiên
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);  // Sinh OTP 6 chữ số
        return String.valueOf(otp);
    }
}
