package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner; // THÊM MỚI
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddMenuActivityAdmin extends AppCompatActivity {

    private EditText etMenuName, etNotes;
    private MultiAutoCompleteTextView multiSpinnerFoods;
    private Button btnAddMenu;
    private DatabaseReference menuRef, foodRef;
    private List<String> foodList;

    // --- THÊM CÁC BIẾN MỚI ---
    private Spinner spinnerDays;
    private String[] dayKeys;
    // --- KẾT THÚC THÊM BIẾN MỚI ---


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_admin);

        // Khởi tạo các view
        etMenuName = findViewById(R.id.et_menu_name);
        etNotes = findViewById(R.id.et_notes);
        multiSpinnerFoods = findViewById(R.id.multi_spinner_foods);
        btnAddMenu = findViewById(R.id.btn_add_menu);

        // --- KHỞI TẠO SPINNER ---
        spinnerDays = findViewById(R.id.spinner_days);

        // Lấy danh sách key (monday, tuesday,...) từ strings.xml
        dayKeys = getResources().getStringArray(R.array.days_of_week_keys);

        // Tạo adapter để hiển thị tên các ngày (Chủ Nhật, Thứ Hai,...)
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this,
                R.array.days_of_week_display, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(dayAdapter);
        // --- KẾT THÚC KHỞI TẠO SPINNER ---

        // Khởi tạo Firebase Database reference
        foodRef = FirebaseDatabase.getInstance().getReference("foods");
        menuRef = FirebaseDatabase.getInstance().getReference("menu"); // Di chuyển lên đây cho gọn

        // Lấy danh sách món ăn từ Firebase
        foodList = new ArrayList<>();
        foodRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                foodList.clear();
                for (com.google.firebase.database.DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
                    // Giả sử node "foods" của bạn là một danh sách các chuỗi
                    String foodName = foodSnapshot.getValue(String.class);
                    foodList.add(foodName);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(AddMenuActivityAdmin.this, android.R.layout.simple_dropdown_item_1line, foodList);
                multiSpinnerFoods.setAdapter(adapter);
                multiSpinnerFoods.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            }

            @Override
            public void onCancelled(com.google.firebase.database.DatabaseError databaseError) {
                Toast.makeText(AddMenuActivityAdmin.this, "Lỗi khi tải danh sách món ăn!", Toast.LENGTH_SHORT).show();
            }
        });

        // --- CẬP NHẬT LOGIC LƯU TRỮ ---
        // --- CẬP NHẬT LOGIC LƯU TRỮ ---
        btnAddMenu.setOnClickListener(v -> {
            String menuName = etMenuName.getText().toString().trim();
            String notesString = etNotes.getText().toString().trim(); // Đổi tên để rõ ràng hơn
            String selectedFoods = multiSpinnerFoods.getText().toString().trim();

            // Lấy ngày được chọn từ Spinner
            int selectedDayPosition = spinnerDays.getSelectedItemPosition();
            String selectedDayKey = dayKeys[selectedDayPosition];

            if (menuName.isEmpty() || selectedFoods.isEmpty()) {
                Toast.makeText(AddMenuActivityAdmin.this, "Vui lòng nhập tên thực đơn và chọn món ăn.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Chuyển đổi chuỗi thành List<String>
            List<String> foodsList = Arrays.asList(selectedFoods.split(",\\s*"));
            List<String> notesList = Arrays.asList(notesString.split(",\\s*")); // Chuyển ghi chú thành List

            // Tạo đối tượng Menu mới với CONSTRUCTOR ĐÚNG
            // (String day, String name, List<String> food, List<String> notes)
            Menu newMenu = new Menu(selectedDayKey, menuName, foodsList, notesList);

            // Sử dụng key là ngày được chọn để lưu vào Firebase
            menuRef.child(selectedDayKey).setValue(newMenu)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(AddMenuActivityAdmin.this, "Thêm thực đơn cho " + spinnerDays.getSelectedItem().toString() + " thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddMenuActivityAdmin.this, MenuActivityAdmin.class);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AddMenuActivityAdmin.this, "Lỗi khi thêm thực đơn: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }

    // Bạn cần có một class Menu giống như thế này để code hoạt động
    // Nếu bạn chưa có, hãy tạo một file mới tên là Menu.java
    /*
    public class Menu {
        public String name;
        public List<String> food;
        public String notes; // Hoặc List<String> notes nếu bạn muốn lưu nhiều ghi chú

        public Menu() {
            // Cần constructor rỗng cho Firebase
        }

        public Menu(String name, List<String> food, String notes) {
            this.name = name;
            this.food = food;
            this.notes = notes;
        }
    }
    */
}