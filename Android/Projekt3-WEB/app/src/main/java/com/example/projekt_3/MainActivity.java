package com.example.projekt_3;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    ProgressBar progressBar;
    public static final String FILE_SIZE = "FILE_SIZE";
    public static final String FILE_TYPE = "FILE_TYPE";
    public static final String FILE_DOWNLOAD = "FILE_DOWNLOAD";
    public static final String URL_ADDRESS = "URL_ADDRESS";

    BroadcastReceiver broadcastReceiver;
    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(MojaIntentService.ACTION_BROADCAST));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView file_size = findViewById(R.id.file_size);
        TextView file_type = findViewById(R.id.file_type);
        TextView downloaded_size = findViewById(R.id.downloaded_size);

        outState.putString(FILE_SIZE, file_size.getText().toString());
        outState.putString(FILE_TYPE, file_type.getText().toString());
        outState.putString(FILE_DOWNLOAD, downloaded_size.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView file_size = findViewById(R.id.file_size);
        TextView file_type = findViewById(R.id.file_type);
        TextView downloaded_size = findViewById(R.id.downloaded_size);

        file_size.setText(savedInstanceState.getString("FILE_SIZE"));
        file_type.setText(savedInstanceState.getString("FILE_TYPE"));
        downloaded_size.setText(savedInstanceState.getString("FILE_DOWNLOAD"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }

        Button button_info = findViewById(R.id.button_get_info);
        Button button_download = findViewById(R.id.button_download);
        TextView file_size = findViewById(R.id.file_size);
        TextView file_type = findViewById(R.id.file_type);
        TextView file_downloaded = findViewById(R.id.downloaded_size);
        EditText file_link = findViewById(R.id.file_link);

        progressBar = findViewById(R.id.progressBar);

        file_size.setText("0");
        file_type.setText("0");
        file_downloaded.setText("0");
        file_link.setText(R.string.default_link);

        button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckFile checkFile = new CheckFile(MainActivity.this);
                checkFile.execute(file_link.getText().toString());
            }
        });
        button_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //downloadFile(file_link.getText().toString(), "downloaded_file");
                MojaIntentService.startService(MainActivity.this, file_link.getText().toString(), file_size.getText().toString(), file_type.getText().toString());

            }
        });
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ProgressInfo progressInfo = intent.getParcelableExtra(MojaIntentService.PROGRESS_INFO);
                int progress = progressInfo.mRozmiar != 0 ? (int) ((long) progressInfo.mPobranychBajtow * 100 / progressInfo.mRozmiar) : 0;
                progressBar.setProgress(progress, true);
                file_downloaded.setText(String.valueOf(progressInfo.mPobranychBajtow));
            }
        };
    }

//    public void downloadFile(String url, String outputFileName) {
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//        request.setTitle(fileName);
//        request.setDescription("Downloading " + fileName);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.allowScanningByMediaScanner();
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, outputFileName);
//        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//        manager.enqueue(request);
//
//    }
}
