package com.example.dang_nhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils; // Import này cần thiết cho TextUtils.isEmpty
import android.util.Patterns; // Import này cần thiết cho Patterns.EMAIL_ADDRESS
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnDangNhap;
    private TextView tvQuenMatKhau, tvErrorMessage;

    // Đây là thông tin đăng nhập mẫu, trong thực tế bạn sẽ xác thực từ server
    private static final String CORRECT_EMAIL = "test@example.com";
    private static final String CORRECT_PASSWORD = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnDangNhap = findViewById(R.id.btn_dang_nhap);
        tvQuenMatKhau = findViewById(R.id.tv_quen_mat_khau);
        tvErrorMessage = findViewById(R.id.tv_error_message);

        // Xử lý sự kiện nút Đăng Nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                tvErrorMessage.setVisibility(View.GONE); // Luôn ẩn lỗi cũ trước khi kiểm tra mới

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    tvErrorMessage.setText("Vui lòng nhập đầy đủ Email và Mật khẩu.");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                } else if (!isValidEmail(email)) { // Kiểm tra định dạng email
                    tvErrorMessage.setText("Định dạng Email không hợp lệ.");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                }
                else if (email.equals(CORRECT_EMAIL) && password.equals(CORRECT_PASSWORD)) {
                    // Đăng nhập thành công, ẩn lỗi (nếu có)
                    tvErrorMessage.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    // Bạn có thể chuyển sang màn hình chính của ứng dụng ở đây
                    // Ví dụ: startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    // Đăng nhập thất bại, hiện thông báo lỗi
                    tvErrorMessage.setText("Tên tài khoản hoặc mật khẩu không đúng.");
                    tvErrorMessage.setVisibility(View.VISIBLE);
                }
            }
        });

        // Xử lý sự kiện text Quên Mật Khẩu
        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    // Hàm kiểm tra định dạng email đơn giản
    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}