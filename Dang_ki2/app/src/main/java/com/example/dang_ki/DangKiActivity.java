package com.example.dang_ki; // Đảm bảo dòng này khớp với tên package của bạn

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DangKiActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etPhone;
    private TextView tvEmailError;
    private TextView tvPhoneError;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        tvEmailError = findViewById(R.id.tvEmailError);
        tvPhoneError = findViewById(R.id.tvPhoneError);
        btnRegister = findViewById(R.id.btnRegister);

        // Xử lý sự kiện click cho nút "Đăng ký"
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Xử lý sự kiện click cho nút back (nếu có, bạn có thể đã thêm trong XML)
        // ImageView backButton = findViewById(R.id.backButton); // Uncomment nếu bạn có nút back trong XML
        // if (backButton != null) {
        //     backButton.setOnClickListener(new View.OnClickListener() {
        //         @Override
        //         public void onClick(View v) {
        //             onBackPressed(); // Gọi hành vi nút back mặc định
        //         }
        //     });
        // }
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        boolean isValid = true;

        // Reset error messages (ẩn các thông báo lỗi nếu có)
        tvEmailError.setVisibility(View.GONE);
        tvPhoneError.setVisibility(View.GONE);

        // Kiểm tra validation từng trường
        if (username.isEmpty()) {
            etUsername.setError("Tên người dùng không được để trống");
            isValid = false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Mật khẩu không được để trống");
            isValid = false;
        }

        // Kiểm tra email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tvEmailError.setVisibility(View.VISIBLE);
            isValid = false;
        }

        // Kiểm tra số điện thoại (ví dụ: bắt đầu bằng 0 và có 10 chữ số)
        if (phone.isEmpty() || !phone.matches("^0[0-9]{9}$")) {
            tvPhoneError.setVisibility(View.VISIBLE);
            isValid = false;
        }

        if (isValid) {
            // Nếu tất cả thông tin hợp lệ, chuyển sang màn hình xác nhận email
            Intent intent = new Intent(this, ConfirmEmailActivity.class);
            startActivity(intent);
            // Bạn có thể không finish() MainActivity ở đây nếu muốn người dùng có thể quay lại
            // finish();
        } else {
            // Nếu có lỗi, hiển thị thông báo chung
            Toast.makeText(this, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}