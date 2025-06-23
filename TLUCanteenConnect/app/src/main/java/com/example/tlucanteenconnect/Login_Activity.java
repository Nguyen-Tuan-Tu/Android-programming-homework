package com.example.tlucanteenconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils; // Import này cần thiết cho TextUtils.isEmpty
import android.util.Patterns; // Import này cần thiết cho Patterns.EMAIL_ADDRESS
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnDangNhap;
    private TextView tvQuenMatKhau, tvErrorMessage;

    // Đây là thông tin đăng nhập mẫu, trong thực tế bạn sẽ xác thực từ server
    private static final String CORRECT_EMAIL = "test@example.com";
    private static final String CORRECT_PASSWORD = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.tb_email);
        etPassword = findViewById(R.id.tb_password);
        btnDangNhap = findViewById(R.id.btn_dang_nhap);
        tvQuenMatKhau = findViewById(R.id.tv_quen_mat_khau);
        tvErrorMessage = findViewById(R.id.tv_error_message);

        // Xử lý sự kiện nút Đăng Nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                tvErrorMessage.setVisibility(View.GONE); // Ẩn lỗi cũ trước khi kiểm tra mới

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    tvErrorMessage.setText("Vui lòng nhập đầy đủ Email và Mật khẩu.");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                } else if (!isValidEmail(email)) { // Kiểm tra định dạng email
                    tvErrorMessage.setText("Định dạng Email không hợp lệ.");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                } else {
                    // Kiểm tra thông tin đăng nhập
                    if (email.equals("admin@gmail.com") && password.equals("123")) {
                        // Đăng nhập thành công với admin
                        tvErrorMessage.setVisibility(View.GONE);
                        Intent intent = new Intent(Login_Activity.this, AdminMore_Activity.class);
                        startActivity(intent);
                    } else if (email.equals("user@gmail.com") && password.equals("123")) {
                        // Đăng nhập thành công với user
                        tvErrorMessage.setVisibility(View.GONE);
                        Intent intent = new Intent(Login_Activity.this, HomePage_Activity.class);
                        startActivity(intent);
                    } else {
                        // Đăng nhập thất bại, hiện thông báo lỗi
                        tvErrorMessage.setText("Tên tài khoản hoặc mật khẩu không đúng.");
                        tvErrorMessage.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        // Xử lý sự kiện text Quên Mật Khẩu
        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, ForgotPassword_Activity.class);
                startActivity(intent);
            }
        });
        ImageButton Back = findViewById(R.id.ic_back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Menu_foryou_Activity.class);
                startActivity(intent);
            }
        });
    }

    // Hàm kiểm tra định dạng email đơn giản
    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}