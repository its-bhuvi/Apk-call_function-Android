package com.example.call_function;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  static final int REQUEST_CALL=1;
    public EditText etnum;
    public ImageView ivcall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnum=findViewById(R.id.Ednum);
        ivcall=findViewById(R.id.Ivcall);

        ivcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callactivity();
            }
        });
    }

    private void callactivity() {
        String number=etnum.getText().toString();
        if (number.trim().length()>0)
        {
             if (ContextCompat.checkSelfPermission(MainActivity.this,
                     Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
             {
                 ActivityCompat.requestPermissions(MainActivity.this,
                         new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
             }else{
                 String dial="tel:"+number;
                 startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
             }

        }else{
            Toast.makeText(this, "please Enter number", Toast.LENGTH_SHORT).show();
        }

    }
   @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callactivity();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
