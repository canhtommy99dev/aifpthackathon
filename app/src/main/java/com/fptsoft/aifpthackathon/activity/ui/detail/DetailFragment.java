package com.fptsoft.aifpthackathon.activity.ui.detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.fptsoft.aifpthackathon.R;
import com.fptsoft.aifpthackathon.activity.model.Sick;
import com.fptsoft.aifpthackathon.activity.ui.result.ResultViewModel;
import com.fptsoft.aifpthackathon.activity.utils.FileUtil;

public class DetailFragment extends Fragment {

    private ImageView imageDetail;
    private TextView headerDetail;
    private TextView contentDetail;

    private Sick sick;

    public DetailFragment() {
    }

    public DetailFragment(Sick sick) {
        this.sick = sick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

        imageDetail = root.findViewById(R.id.image_detail);
        headerDetail = root.findViewById(R.id.header_detail);
        contentDetail = root.findViewById(R.id.content_detail);

        if(sick != null){
            Bitmap bitmap = FileUtil.getBitmapFromNameImage(sick.getImage());
            imageDetail.setImageBitmap(bitmap);
            headerDetail.setText(sick.getName());
            contentDetail.setText(sick.getContent());
        }

        return root;
    }
}