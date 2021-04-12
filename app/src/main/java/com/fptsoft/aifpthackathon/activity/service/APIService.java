package com.fptsoft.aifpthackathon.activity.service;

import android.content.Context;
import android.widget.Toast;

import com.fptsoft.aifpthackathon.activity.HomeActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIService {

    private Context contextOfApplication;

    public APIService() {
    }

    public APIService(Context contextOfApplication) {
        this.contextOfApplication = contextOfApplication;
    }

    public Response postRequest(String postUrl, RequestBody postBody) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .build();

        Response response = client.newCall(request).execute();

        return response;

//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                // Cancel the post on failure.
//                call.cancel();
//
//                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
//                new HomeActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(contextOfApplication.getApplicationContext(), "Failed to Connect to Server", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
//                new HomeActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Toast.makeText(contextOfApplication.getApplicationContext(), response.body().string(), Toast.LENGTH_LONG).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
    }
}
