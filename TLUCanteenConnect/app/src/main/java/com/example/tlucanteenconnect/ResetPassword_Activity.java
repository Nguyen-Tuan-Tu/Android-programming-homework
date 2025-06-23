package com.example.tlucanteenconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView; // Thêm import này

public class ResetPassword_Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etNewPassword, etConfirmNewPassword;
    private Button btnXacNhan;
    private TextView tvNewPasswordErrorMessage; // Khai báo TextView lỗi
    private TextView tvConfirmPasswordErrorMessage; // Khai báo TextView lỗi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btnBack = findViewById(R.id.btn_back_reset_password);
        etNewPassword = findViewById(R.id.et_new_password);
        etConfirmNewPassword = findViewById(R.id.et_confirm_new_password);
        btnXacNhan = findViewById(R.id.btn_xac_nhan_reset);
        tvNewPasswordErrorMessage = findViewById(R.id.tv_new_password_error_message); // Ánh xạ ID
        tvConfirmPasswordErrorMessage = findViewById(R.id.tv_confirm_password_error_message); // Ánh xạ ID

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = etNewPassword.getText().toString().trim();
                String confirmNewPassword = etConfirmNewPassword.getText().toString().trim();

                tvNewPasswordErrorMessage.setVisibility(View.GONE); // Ẩn lỗi cũ
                tvConfirmPasswordErrorMessage.setVisibility(View.GONE); // Ẩn lỗi cũ

                boolean isValid = true;

                if (TextUtils.isEmpty(newPassword)) {
                    tvNewPasswordErrorMessage.setText("Vui lòng nhập mật khẩu mới.");
                    tvNewPasswordErrorMessage.setVisibility(View.VISIBLE);
                    isValid = false;
                }
                // Có thể thêm kiểm tra độ dài/độ phức tạp của mật khẩu ở đây
                // else if (newPassword.length() < 6) { ... }


                if (TextUtils.isEmpty(confirmNewPassword)) {
                    tvConfirmPasswordErrorMessage.setText("Vui lòng xác nhận mật khẩu.");
                    tvConfirmPasswordErrorMessage.setVisibility(View.VISIBLE);
                    isValid = false;
                } else if (!newPassword.equals(confirmNewPassword)) {
                    tvConfirmPasswordErrorMessage.setText("Mật khẩu xác nhận không khớp.");
                    tvConfirmPasswordErrorMessage.setVisibility(View.VISIBLE);
                    isValid = false;
                }

                if (isValid) {
                    // Mật khẩu hợp lệ, chuyển sang màn hình thành công
                    // (Trong thực tế, bạn sẽ gửi mật khẩu mới này lên server để cập nhật)
                    // Toast.makeText(ResetPasswordActivity.this, "Đang đặt lại mật khẩu...", Toast.LENGTH_SHORT).show(); // Bỏ Toast
                    Intent intent = new Intent(ResetPassword_Activity.this, PasswordChangedSuccess_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}