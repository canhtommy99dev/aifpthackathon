package com.fptsoft.aifpthackathon.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.fptsoft.aifpthackathon.R;
import com.fptsoft.aifpthackathon.activity.ui.camera.CameraFragment;
import com.fptsoft.aifpthackathon.activity.ui.gallery.GalleryFragment;
import com.fptsoft.aifpthackathon.activity.ui.home.HomeFragment;
import com.fptsoft.aifpthackathon.activity.ui.slideshow.SlideshowFragment;
import com.fptsoft.aifpthackathon.activity.ui.upload.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.navigation);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.icons8_menu_24);

        drawer = findViewById(R.id.drawer_layout);

        loadFragment(new HomeFragment());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                System.out.println("Navigation");
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        toolbar.setTitle("Home");
                        fragment = new HomeFragment();
                        loadFragment(fragment);

                        drawer.closeDrawers();

                        return true;
                    case R.id.nav_gallery:
                        toolbar.setTitle("Gallery");
                        fragment = new GalleryFragment();
                        loadFragment(fragment);

                        drawer.closeDrawers();

                        return true;
                    case R.id.nav_slideshow:
                        toolbar.setTitle("SlideShow");
                        fragment = new SlideshowFragment();
                        loadFragment(fragment);

                        drawer.closeDrawers();

                        return true;
                }
                return false;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                System.out.println("Bottom Navigation" + item + " - " + item.getItemId());
                System.out.println(R.id.navigation_upload + " - " + R.id.navigation_camera + " - " + R.id.navigation_gallery);
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_upload:
                        toolbar.setTitle("Upload");
                        fragment = new UploadFragment();
                        setTitleForMenuItemInNavBottom(R.id.navigation_upload, R.string.title_upload);
                        loadFragment(fragment);

                        return true;
                    case R.id.navigation_camera:
                        toolbar.setTitle("Camera");
                        fragment = new CameraFragment();
                        setTitleForMenuItemInNavBottom(R.id.navigation_camera, R.string.title_camera);
                        loadFragment(fragment);

                        return true;
                    case R.id.navigation_gallery:
                        toolbar.setTitle("Gallery");
                        fragment = new GalleryFragment();
                        setTitleForMenuItemInNavBottom(R.id.navigation_gallery, R.string.title_gallery);
                        loadFragment(fragment);

                        return true;
                }

                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    public void loadFragment(Fragment fragment) {
        // load fragment

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.root_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    // open drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTitleForMenuItemInNavBottom(int idMenuItem, int idTitle){
        Menu menu = bottomNavigationView.getMenu();
        for(int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            if(menuItem.getItemId() == idMenuItem){
                menuItem.setTitle(idTitle);
            }else{
                menuItem.setTitle("");
            }
        }
    }
}