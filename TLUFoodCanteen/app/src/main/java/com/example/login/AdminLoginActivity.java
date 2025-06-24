package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, forgotPasswordButton;

    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // Khởi tạo Firebase Realtime Database
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        // Ánh xạ các EditText và Button
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);

        // Sự kiện khi nhấn nút Đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AdminLoginActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else {
                    checkLogin(email, password);
                }
            }
        });

        // Sự kiện khi nhấn nút Quên mật khẩu
        forgotPasswordButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminLoginActivity.this, ForgotPasswordActivityAdmin.class);
            startActivity(intent);
        });
    }

    private void checkLogin(String email, String password) {
        // Kiểm tra dữ liệu người dùng trong Firebase
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isValidUser = false;
                String userRole = ""; // Biến lưu vai trò người dùng

                // Lặp qua tất cả người dùng trong Realtime Database
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String dbEmail = userSnapshot.child("email").getValue(String.class);
                    String dbPassword = userSnapshot.child("password").getValue(String.class);
                    userRole = userSnapshot.child("role").getValue(String.class); // Lấy vai trò người dùng

                    // So sánh email và password
                    if (email.equals(dbEmail) && password.equals(dbPassword)) {
                        isValidUser = true;
                        break;
                    }
                }

                // Kiểm tra xem có người dùng hợp lệ không
                if (isValidUser) {
                    Toast.makeText(AdminLoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    // Chuyển đến màn hình chính (MainScreenActivity) tùy vào vai trò người dùng
                    Intent intent;
                    if ("admin".equals(userRole)) {
                        intent = new Intent(AdminLoginActivity.this, MainScreenActivityAdmin.class);
                    } else {
                        intent = new Intent(AdminLoginActivity.this, HomePage_Activity.class);
                    }
                    startActivity(intent);
                    finish(); // Đóng Activity hiện tại để không quay lại màn hình đăng nhập
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Email hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminLoginActivity.this, "Lỗi khi kết nối đến Firebase.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
