package com.fptsoft.aifpthackathon.activity.ui.camera;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fptsoft.aifpthackathon.R;
import com.fptsoft.aifpthackathon.activity.HomeActivity;
import com.fptsoft.aifpthackathon.activity.service.APIService;
import com.fptsoft.aifpthackathon.activity.service.ImageService;
import com.fptsoft.aifpthackathon.activity.ui.upload.UploadViewModel;
import com.fptsoft.aifpthackathon.activity.utils.FileUtil;

import java.io.IOException;
import java.util.Date;

import okhttp3.Response;

public class CameraFragment extends Fragment implements View.OnClickListener{

    private CameraViewModel cameraViewModel;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String nameImageUpload;
    private Bitmap bitmap;
    private Button btnOpenCamera;
    private Button btnUpload;
    private EditText eTxtNameImg;
    private ImageView imageSelected;
    private View root;

    private Context contextOfApplication;
    private ImageService imageService;
    private APIService apiService;

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cameraViewModel =
                new ViewModelProvider(this).get(CameraViewModel.class);
        root = inflater.inflate(R.layout.fragment_camera, container, false);

        contextOfApplication = HomeActivity.getContextOfApplication();
        apiService = new APIService();
        imageService = new ImageService(contextOfApplication, apiService);

        eTxtNameImg = root.findViewById(R.id.eTxtNameImg);
        btnOpenCamera = root.findViewById(R.id.btnOpenCamera);
        btnUpload = root.findViewById(R.id.btnUpload);
        imageSelected = root.findViewById(R.id.img);

        btnOpenCamera.setOnClickListener(this);
        btnUpload.setOnClickListener(this);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cameraViewModel = new ViewModelProvider(this).get(CameraViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        if (v == btnOpenCamera) {
            openCamera();
        }
        if (v == btnUpload) {
            uploadImage();
        }
    }

    //open camera
    public void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    void uploadImage(){
        String nameImage = eTxtNameImg.getText().toString();
        String nameImageUpload = nameImage.length() > 0 ? nameImage : "DefaultName.jpg";

        try {
            Response response = imageService.uploadImage(bitmap, nameImageUpload);
            System.out.println("Response upload image " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Read URI");
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == HomeActivity.RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");

            imageSelected.setImageBitmap(bitmap);
            boolean isSaved = FileUtil.saveBitmapToFile(bitmap, "default" + new Date().getTime());
            if(isSaved){
                Toast.makeText(contextOfApplication.getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(contextOfApplication.getApplicationContext(), "Cannot save", Toast.LENGTH_LONG).show();
            }
        }
    }
}