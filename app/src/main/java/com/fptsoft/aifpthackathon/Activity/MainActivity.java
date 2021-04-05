package com.fptsoft.aifpthackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fptsoft.aifpthackathon.R;
import com.fptsoft.aifpthackathon.adapter.EmployeeFragmentStateAdapter;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2Employee;
    EmployeeFragmentStateAdapter adapter;
    LinearLayout layoutOnboradingIndicator;
    MaterialButton buttonAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.viewPager2Employee = findViewById(R.id.view_pager2);

        // Employee FragmentStateAdapter.
        layoutOnboradingIndicator = findViewById(R.id.layoutOnboarding);
        buttonAction = findViewById(R.id.buttonAction);
        adapter = new EmployeeFragmentStateAdapter(this);
        viewPager2Employee.setAdapter(adapter);
        setupOnBoarding();
        setCurrentOnboardingIndicator(0);
        viewPager2Employee.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        setCurrentOnboardingIndicator(position);
                    }
                }
        );
        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager2Employee.getCurrentItem() + 1 < adapter.getItemCount()){
                    viewPager2Employee.setCurrentItem(viewPager2Employee.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(),HomeAcitvity.class));
                    finish();
                }
            }
        });
    }

    private void setupOnBoarding(){
        ImageView[] indicator = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicator.length; i++){
            indicator[i] = new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),R.drawable.onboarding_indicator_inactive
            ));
            indicator[i].setLayoutParams(layoutParams);
            layoutOnboradingIndicator.addView(indicator[i]);
        }
    }
    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboradingIndicator.getChildCount();
        for (int i = 0; i < childCount;i++){
            ImageView imageView = (ImageView) layoutOnboradingIndicator.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            }
        }
        if (index == adapter.getItemCount() - 1){
            buttonAction.setText("Bắt Đầu");
        }else {
            buttonAction.setText("Tiếp tục");
        }
    }
}