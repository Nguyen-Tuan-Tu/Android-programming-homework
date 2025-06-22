package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class VerificationCodeActivity extends AppCompatActivity {

    private EditText verificationCodeEditText;
    private Button confirmButton;
    private TextView otpTextView;
    private String generatedOtp;  // Biến lưu mã OTP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);  // Đảm bảo bạn sử dụng layout của bạn

        verificationCodeEditText = findViewById(R.id.et_verification_code);
        confirmButton = findViewById(R.id.btn_confirm);
        otpTextView = findViewById(R.id.tv_generated_otp);

        // Sinh mã OTP ngẫu nhiên
        generatedOtp = generateOtp();
        otpTextView.setText("Mã OTP của bạn là: " + generatedOtp);  // Hiển thị mã OTP trên giao diện

        confirmButton.setOnClickListener(v -> {
            String enteredOtp = verificationCodeEditText.getText().toString().trim();
            if (enteredOtp.equals(generatedOtp)) {
                // Nếu mã OTP đúng
                Toast.makeText(VerificationCodeActivity.this, "Mã OTP đúng! Thực hiện thao tác tiếp theo.", Toast.LENGTH_SHORT).show();
                // Bạn có thể thực hiện tiếp các hành động như chuyển màn hình hoặc cập nhật thông tin
            } else {
                // Nếu mã OTP sai
                Toast.makeText(VerificationCodeActivity.this, "Mã OTP sai, vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
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
