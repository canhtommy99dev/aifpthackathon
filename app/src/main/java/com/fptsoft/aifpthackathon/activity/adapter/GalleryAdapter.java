package com.fptsoft.aifpthackathon.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.fptsoft.aifpthackathon.activity.model.Image;
import com.fptsoft.aifpthackathon.activity.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends BaseAdapter {

    private List<Image> images;
    private Context contextOfApplication;

    public GalleryAdapter(Context contextOfApplication, List<Image> images) {
        this.contextOfApplication = contextOfApplication;
        this.images = images;

    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return images.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        File file = new File(FileUtil.PATH_IMAGE + images.get(position).getName());
        System.out.println("Position" + position);
        Uri uri = Uri.fromFile(file);
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(contextOfApplication);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(5, 10, 5, 10);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageURI(uri);

        return imageView;
    }
}
