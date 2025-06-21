package com.example.user_dtu;

import android.os.Bundle;
import android.util.Log; // Quan trọng: Đảm bảo đã import Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
// Không cần import lại Fragment ở đây nếu đã có ở trên

public class StartFragment extends Fragment {
    private ImageView IconUser;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // 1. Luôn inflate layout của Fragment trước tiên
        View view = inflater.inflate(R.layout.fragment_start_layout, container, false);

        // 2. Tìm ImageView với ID profile_icon trong layout đã inflate
        IconUser = view.findViewById(R.id.profile_icon);

        // 3. RẤT QUAN TRỌNG: KIỂM TRA XEM IconUser CÓ BỊ NULL HAY KHÔNG
        if (IconUser != null) {
            // Nếu IconUser được tìm thấy, thì mới thiết lập OnClickListener
            IconUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Thêm log để xác nhận rằng sự kiện click đang hoạt động
                    Log.d("StartFragment", "Profile icon clicked! Navigating to user fragment.");

                    // Đảm bảo getActivity() không phải là null trước khi thao tác với FragmentManager
                    if (getActivity() != null) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new user()) // Đảm bảo "new user()" là tên lớp Fragment chính xác của bạn
                                .commit(); // Hoặc commitAllowingStateLoss() nếu bạn gặp lỗi IllegalStateException
                    } else {
                        Log.e("StartFragment", "getActivity() is null, cannot navigate.");
                    }
                }
            });
        } else {
            // Nếu IconUser là null, ghi log lỗi để biết nguyên nhân crash
            Log.e("StartFragment", "Error: ImageView with ID R.id.profile_icon not found in fragment_start_layout.xml!");
            // Log.e("StartFragment", "Check your fragment_start_layout.xml to ensure the ImageView has id=@+id/profile_icon and is correctly placed.");
        }

        return view;
    }
}