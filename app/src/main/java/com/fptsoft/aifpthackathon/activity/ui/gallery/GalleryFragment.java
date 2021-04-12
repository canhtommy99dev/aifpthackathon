package com.fptsoft.aifpthackathon.activity.ui.gallery;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.fptsoft.aifpthackathon.R;
import com.fptsoft.aifpthackathon.activity.HomeActivity;
import com.fptsoft.aifpthackathon.activity.adapter.GalleryAdapter;
import com.fptsoft.aifpthackathon.activity.model.Image;
import com.fptsoft.aifpthackathon.activity.ui.upload.UploadFragment;
import com.fptsoft.aifpthackathon.activity.utils.FileUtil;

import java.util.List;

public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GalleryViewModel galleryViewModel;
    private Context contextOfApplication;
    private GalleryAdapter galleryAdapter;
    private List<Image> images;
    private GridView gridView;
    private AppCompatActivity appCompatActivity;

    public GalleryFragment() {
    }
    public GalleryFragment(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        contextOfApplication = HomeActivity.getContextOfApplication();
        images = FileUtil.getListImageFromStorage();


        galleryAdapter = new GalleryAdapter(contextOfApplication, images);
        gridView = root.findViewById(R.id.list_image);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkPermission()) {
                    requestPermission(); // Code for permission
                }
            }
        }

        gridView.setAdapter(galleryAdapter);

        gridView.setOnItemClickListener(this);

        return root;
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(contextOfApplication, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(new HomeActivity(), "Write External Storage permission allows us to read  files. Please allow this permission in App Settings."
                    , Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {android.Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object o = gridView.getItemAtPosition(position);
        Image image = (Image) o;
        Log.d("Item Click", image.getName());
        Toast.makeText(getContext(), "Selected :"
                + " " + image.getName(), Toast.LENGTH_LONG).show();
        Bundle bundle = new Bundle();
        bundle.putString("imageSelect", image.getName());

        UploadFragment uploadFragment = new UploadFragment(appCompatActivity);
        uploadFragment.setArguments(bundle);

        ((HomeActivity) appCompatActivity).loadFragment(uploadFragment);
    }

}