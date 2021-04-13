package com.fptsoft.aifpthackathon.activity.ui.upload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fptsoft.aifpthackathon.R;
import com.fptsoft.aifpthackathon.activity.HomeActivity;
import com.fptsoft.aifpthackathon.activity.model.APIConstant;
import com.fptsoft.aifpthackathon.activity.model.Result;
import com.fptsoft.aifpthackathon.activity.service.APIService;
import com.fptsoft.aifpthackathon.activity.service.CheckService;
import com.fptsoft.aifpthackathon.activity.service.ImageService;
import com.fptsoft.aifpthackathon.activity.ui.result.ResultFragment;
import com.fptsoft.aifpthackathon.activity.utils.FileUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadFragment extends Fragment implements View.OnClickListener {
    private static final int PICK_IMAGE_REQUEST = 1;
    private UploadViewModel uploadViewModel;
    private String nameImageUpload;
    private Bitmap bitmap;
    private Button btnSelect;
    private Button btnUpload;
    private EditText eTxtNameImg;
    private ImageView imageSelected;
    private View root;
    private Bundle bundleImageSelect;

    private Context contextOfApplication;
    private AppCompatActivity appCompatActivity;
    private ImageService imageService;
    private APIService apiService;

    public UploadFragment() {
    }
    public UploadFragment(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }
    public UploadFragment(Bundle bundleImageSelect) {
        this.bundleImageSelect = bundleImageSelect;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uploadViewModel =
                new ViewModelProvider(this).get(UploadViewModel.class);
        root = inflater.inflate(R.layout.fragment_upload, container, false);


        contextOfApplication = HomeActivity.getContextOfApplication();
        apiService = new APIService();
        imageService = new ImageService(contextOfApplication, apiService);

//        final TextView textView = root.findViewById(R.id.text_home);
//        uploadViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        eTxtNameImg = root.findViewById(R.id.eTxtNameImg);
        btnSelect = root.findViewById(R.id.btnSelectImage);
        btnUpload = root.findViewById(R.id.btnUpload);
        imageSelected = root.findViewById(R.id.img);

        btnSelect.setOnClickListener(this);
        btnUpload.setOnClickListener(this);


        if(getArguments() != null){
            String nameImageInBundle = getArguments().get("imageSelect").toString();
            Bitmap bitmapImageSelect = FileUtil.getBitmapFromNameImage(nameImageInBundle);

            updateView(bitmapImageSelect, nameImageInBundle);
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        uploadViewModel = new ViewModelProvider(this).get(UploadViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        if (v == btnSelect) {
            selectImage(v);
        }
        if (v == btnUpload) {
            uploadImage();
        }
    }

    //select image
    public void selectImage(View v) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    void uploadImage(){
        String nameImage = eTxtNameImg.getText().toString();
        String nameImageUpload = nameImage.length() > 0 ? nameImage : "DefaultName.jpg";

//        try {
//            Response response = imageService.uploadImage(bitmap, nameImageUpload);
//            System.out.println("Response upload image " + response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Result result = CheckService.getResult(nameImageUpload);
        ((HomeActivity) appCompatActivity).loadFragment(new ResultFragment(result));

    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Read URI");
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == HomeActivity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contextOfApplication.getContentResolver(), uri);
                imageSelected.setImageBitmap(bitmap);
                String path = uri.getPath();
                nameImageUpload = path.substring(path.lastIndexOf("/") + 1);
                eTxtNameImg.setText(nameImageUpload);
                boolean isSaved = FileUtil.saveBitmapToFile(bitmap, nameImageUpload.substring(0, nameImageUpload.lastIndexOf(".")));
                if(isSaved){
                    Toast.makeText(contextOfApplication.getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(contextOfApplication.getApplicationContext(), "Cannot save", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String selectedImagePath = ImageService.getPath(contextOfApplication.getApplicationContext(), uri);
            System.out.println("Part select" + selectedImagePath);
            Toast.makeText(contextOfApplication.getApplicationContext(), selectedImagePath, Toast.LENGTH_LONG).show();
        }
    }

    public void updateView(Bitmap bitmap, String nameImage){
        this.nameImageUpload = nameImage;
        this.bitmap = bitmap;
        this.imageSelected.setImageBitmap(bitmap);
        this.eTxtNameImg.setText(nameImage);
    }

}