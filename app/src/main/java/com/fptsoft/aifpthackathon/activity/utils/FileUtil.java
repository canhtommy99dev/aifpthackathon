package com.fptsoft.aifpthackathon.activity.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.fptsoft.aifpthackathon.activity.model.Image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static final String PATH_IMAGE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/";

    public static boolean saveBitmapToFile(Bitmap bitmap, String filename) {
        String path = PATH_IMAGE + filename + ".png";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static Uri getUriFromNameImage(String nameImage){
        File file = new File(PATH_IMAGE + nameImage);
        Uri uri = Uri.fromFile(file);

        return uri;
    }

    public static Bitmap getBitmapFromNameImage(String nameImage){
        Bitmap bitmap = BitmapFactory.decodeFile(PATH_IMAGE + nameImage);

        return bitmap;
    }

    public static List<Image> getListImageFromStorage() {
        List<Image> images = new ArrayList<>();
        File dir = new File(PATH_IMAGE);
        if (dir.exists()) {
            File[] list = dir.listFiles();
            for (int i = 0; i < list.length; i++) {
                String fileName = list[i].getName();
                String extension = MimeTypeMap.getFileExtensionFromUrl(fileName);
//                String[] extensionImg = {};
                if(null != extension && extension.equals("png")){
                    Image image = new Image();
                    image.setId(i);
                    image.setName(fileName);
                    Log.d("File name", list[i].getName());
                    images.add(image);
                }
            }
        }

        return images;
    }
}
