package com.fptsoft.aifpthackathon.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import com.fptsoft.aifpthackathon.R;
import com.fptsoft.aifpthackathon.activity.ui.camera.CameraFragment;
import com.fptsoft.aifpthackathon.activity.ui.gallery.GalleryFragment;
import com.fptsoft.aifpthackathon.activity.ui.upload.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavBottomFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActionBar toolbar;
    private BottomNavigationView navigation;



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_upload:

                fragment = new UploadFragment();

                return true;
            case R.id.navigation_camera:
                fragment = new CameraFragment();

                return true;
            case R.id.navigation_gallery:
                fragment = new GalleryFragment();

                return true;
        }
        return true;
    }

}