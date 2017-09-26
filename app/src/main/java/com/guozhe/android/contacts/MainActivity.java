package com.guozhe.android.contacts;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<Data> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            checkPermission();
        }else {
            init();
        }

    }
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission(){
        if(checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            init();
        }else {
            String permission[] = {Manifest.permission.READ_CONTACTS};
            requestPermissions(permission,100);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                init();
            }else {
                Toast.makeText(getApplicationContext(),"권한승인을 하셔야 앱을 샤용할수 있읍니다",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        datas = Contacts.getContects(getBaseContext());
        RecyclerAdapter adapter = new RecyclerAdapter(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
