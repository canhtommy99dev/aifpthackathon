package com.fptsoft.aifpthackathon.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fptsoft.aifpthackathon.Model.Employee;
import com.fptsoft.aifpthackathon.R;

public class Change1 extends Fragment {

    private static final String LOG_TAG = "AndroidExample";

    private Employee employee;

    private TextView textViewEmail;
    private TextView textViewPosition;
    private TextView textViewFullName;

    private static int counter = 0;

    public Change1() {
    }

    public Change1(Employee employee) {
        this.employee = employee;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change1, container, false);
        counter++;
        if(counter % 2 == 0) {
            view.setBackgroundColor(Color.parseColor("#ebdef0"));
        } else  {
            view.setBackgroundColor(Color.parseColor("#e8f8f5"));
        }

        this.textViewFullName = view.findViewById(R.id.textView_fullName);
        this.textViewPosition = view.findViewById(R.id.textView_position);
        this.textViewEmail = view.findViewById(R.id.textView_email);
        return view;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(LOG_TAG, "onSaveInstanceState: save employee data to Bundle");
        // Convert employee object to Bundle.
        Bundle dataBundle = this.employeeToBundle(this.employee);
        outState.putAll(dataBundle);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onViewStateRestored");

        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        if(this.employee == null)  {
            Log.i(LOG_TAG, "Get employee data from savedInstanceState");
            // The state was saved by onSaveInstanceState(Bundle outState) method.
            this.employee = this.bundleToEmployee(savedInstanceState);
        }
        this.showInGUI(this.employee);
    }
    private void showInGUI(Employee employee)  {
        this.textViewFullName.setText(employee.getFullName());
        this.textViewPosition.setText(employee.getPosition());
        this.textViewEmail.setText(employee.getEmail());
    }


    private Bundle employeeToBundle(Employee employee)  {
        Bundle bundle = new Bundle();
        bundle.putString("fullName", employee.getFullName());
        bundle.putString("position", employee.getPosition());
        bundle.putString("email", employee.getEmail());

        return bundle;
    }

    private Employee bundleToEmployee(Bundle savedInstanceState) {
        String fullName = savedInstanceState.getString("fullName");
        String position = savedInstanceState.getString("position");
        String email = savedInstanceState.getString("email");
        return new Employee(fullName, email, position);
    }


}