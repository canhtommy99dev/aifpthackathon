package com.fptsoft.aifpthackathon.activity.ui.result;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fptsoft.aifpthackathon.R;
import com.fptsoft.aifpthackathon.activity.model.Result;
import com.fptsoft.aifpthackathon.activity.ui.detail.DetailFragment;
import com.fptsoft.aifpthackathon.activity.utils.FileUtil;

public class ResultFragment extends Fragment {

    private ResultViewModel resultViewModel;
    private Result result;
    private Fragment sickDetailLayout;

    private ImageView imageCheck;
    private ProgressBar percentResultProgress;
    private TextView sickName;
    private TextView percentResult;
    private TextView dateResult;
    private TextView doctorName;
    private TextView phoneDoctor;
    private FrameLayout includeFragment;

    public ResultFragment() {
    }

    public ResultFragment(Result result) {
        this.result = result;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        resultViewModel =
                new ViewModelProvider(this).get(ResultViewModel.class);
        View root = inflater.inflate(R.layout.fragment_result, container, false);

        imageCheck = root.findViewById(R.id.imageCheck);
        percentResultProgress = root.findViewById(R.id.percentResultProgress);
        sickName = root.findViewById(R.id.sickName);
        percentResult = root.findViewById(R.id.percentResult);
        dateResult = root.findViewById(R.id.dateResult);
        doctorName = root.findViewById(R.id.doctorName);
        phoneDoctor = root.findViewById(R.id.phoneDoctor);
        includeFragment = root.findViewById(R.id.includeFragment);

        if(result != null){
            Bitmap bitmap = FileUtil.getBitmapFromNameImage(result.getImage());
            imageCheck.setImageBitmap(bitmap);

            percentResultProgress.setProgress((int) result.getMaxPercent());
            sickName.setText(result.getSicks().get(0).getName());
            percentResult.setText(String.valueOf(result.getMaxPercent()));
            dateResult.setText(result.getDate().toString());
            doctorName.setText("Bac si");
            phoneDoctor.setText("0773314448");

            loadFragment(new DetailFragment(result.getSicks().get(0)));
        }
//        loadFragment(new DetailFragment());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        // TODO: Use the ViewModel
    }
    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.includeFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}