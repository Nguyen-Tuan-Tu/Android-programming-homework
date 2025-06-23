package com.example.dang_nhap;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView; // Thêm import này

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etForgotEmail;
    private Button btnXacNhan;
    private TextView tvForgotErrorMessage; // Khai báo TextView lỗi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnBack = findViewById(R.id.btn_back_forgot_password);
        etForgotEmail = findViewById(R.id.et_forgot_email);
        btnXacNhan = findViewById(R.id.btn_xac_nhan_forgot);
        tvForgotErrorMessage = findViewById(R.id.tv_forgot_error_message); // Ánh xạ ID

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etForgotEmail.getText().toString().trim();
                tvForgotErrorMessage.setVisibility(View.GONE); // Luôn ẩn lỗi cũ trước khi kiểm tra

                if (TextUtils.isEmpty(email)) {
                    tvForgotErrorMessage.setText("Vui lòng nhập Email.");
                    tvForgotErrorMessage.setVisibility(View.VISIBLE);
                } else if (!isValidEmail(email)) {
                    tvForgotErrorMessage.setText("Định dạng Email không hợp lệ.");
                    tvForgotErrorMessage.setVisibility(View.VISIBLE);
                }
                else {
                    // Email hợp lệ, chuyển sang màn hình xác nhận
                    // (Trong thực tế, bạn sẽ gửi yêu cầu reset mật khẩu đến server qua email này)
                    // Toast.makeText(ForgotPasswordActivity.this, "Gửi mã xác nhận đến " + email, Toast.LENGTH_SHORT).show(); // Bỏ Toast
                    Intent intent = new Intent(ForgotPasswordActivity.this, ConfirmEmailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}