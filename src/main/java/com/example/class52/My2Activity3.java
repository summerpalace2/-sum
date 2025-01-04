package com.example.class52;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class My2Activity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 vp2 = findViewById(R.id.tab_viewPager);
        ArrayList<FragmentInterface> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentInterface()
        {
            @Override
            public Fragment back() {
                return new FirstFragment();
            }
        });
        fragmentList.add(new FragmentInterface()
        {
            @Override
            public Fragment back() {
                return new SecondFragment();
            }
        });
        VPAdapter2 adapter = new VPAdapter2(this, fragmentList);
        vp2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, vp2, new
                TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int
                            position) {
                        if (position == 0) {
                            tab.setText("第一个页面😁");
                        } else {
                            tab.setText("第二个页面🫡");
                        }
                    }
                }).attach();
    }
    public static void StartActivity(Context content)
    {
        Intent intent= new Intent(content, My2Activity3.class);
        content.startActivity(intent);
    }
}
