package com.example.login; // Đảm bảo dòng này khớp với tên package của bạn

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView; // Đã thêm ImageView
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
    private Button btnRegister; // Đã đổi tên biến Register thành btnRegister để nhất quán

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        // Ánh xạ các View từ layout
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        tvEmailError = findViewById(R.id.tvEmailError);
        tvPhoneError = findViewById(R.id.tvPhoneError);
        btnRegister = findViewById(R.id.btnRegister); // Sử dụng lại biến đã khai báo

        // Xử lý sự kiện click cho nút "Đăng ký"
        // CHỈ GỌI registerUser() TỪ ĐÂY
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(); // Gọi phương thức kiểm tra validation và xử lý đăng ký
            }
        });

        // Xử lý sự kiện click cho nút back
        ImageView backButton = findViewById(R.id.ic_back); // Đảm bảo ID này khớp với XML của bạn
        if (backButton != null) {
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chuyển sang Activity Menu Đồ uống khi nhấn nút back (theo yêu cầu của bạn)
                    // Hoặc bạn có thể dùng onBackPressed(); để quay về màn hình trước đó
                    Intent intent = new Intent(DangKiActivity.this, Menu_foryou_Activity.class);
                    startActivity(intent);
                    finish(); // Tùy chọn: kết thúc Activity hiện tại nếu không muốn quay lại
                }
            });
        }

        // LOẠI BỎ ĐOẠN CODE BỊ TRÙNG LẶP NÀY
        // Button Register = findViewById(R.id.btnRegister);
        // Register.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // Intent intent = new Intent(DangKiActivity.this, Login_Activity.class);
        // startActivity(intent);
        // }
        // });

        // LOẠI BỎ ĐOẠN CODE NÀY NẾU backButton ĐÃ XỬ LÝ Ở TRÊN
        // ImageView Back = findViewById(R.id.ic_back); // ID này không khớp với XML bạn đã cho
        // Back.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         Intent intent = new Intent(DangKiActivity.this, Menu_foryou_Activity.class);
        //         startActivity(intent);
        //     }
        // });
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        boolean isValid = true; // Biến cờ để kiểm tra tổng thể

        // Ẩn tất cả các thông báo lỗi trước khi kiểm tra lại
        tvEmailError.setVisibility(View.GONE);
        tvPhoneError.setVisibility(View.GONE);
        etUsername.setError(null); // Xóa lỗi cũ
        etPassword.setError(null); // Xóa lỗi cũ

        // Kiểm tra validation từng trường
        if (username.isEmpty()) {
            etUsername.setError("Tên người dùng không được để trống");
            isValid = false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Mật khẩu không được để trống");
            isValid = false;
        }
        // Bạn có thể thêm các kiểm tra độ dài, độ mạnh mật khẩu ở đây
        // if (password.length() < 6) {
        //     etPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
        //     isValid = false;
        // }

        // Kiểm tra email
        if (email.isEmpty()) {
            tvEmailError.setText("*email không được để trống");
            tvEmailError.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tvEmailError.setText("*email không hợp lệ");
            tvEmailError.setVisibility(View.VISIBLE);
            isValid = false;
        }

        // Kiểm tra số điện thoại (ví dụ: bắt đầu bằng 0 và có 10 chữ số)
        if (phone.isEmpty()) {
            tvPhoneError.setText("*số điện thoại không được để trống");
            tvPhoneError.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (!phone.matches("^0[0-9]{9}$")) {
            tvPhoneError.setText("*số điện thoại không hợp lệ");
            tvPhoneError.setVisibility(View.VISIBLE);
            isValid = false;
        }


        if (isValid) {
            // NẾU TẤT CẢ THÔNG TIN HỢP LỆ, THÌ MỚI CHUYỂN SANG GIAO DIỆN LOGIN
            Toast.makeText(this, "Đăng ký thành công! Đang chuyển đến màn hình đăng nhập...", Toast.LENGTH_LONG).show();

            // Chuyển sang Login_Activity
            Intent intent = new Intent(DangKiActivity.this, AdminLoginActivity.class);
            startActivity(intent);
            finish(); // Kết thúc DangKiActivity để người dùng không thể quay lại màn hình đăng ký sau khi đã đăng ký thành công
        } else {
            // Nếu có lỗi, hiển thị thông báo chung
            Toast.makeText(this, "Vui lòng kiểm tra lại thông tin đăng ký", Toast.LENGTH_LONG).show();
        }
    }
}