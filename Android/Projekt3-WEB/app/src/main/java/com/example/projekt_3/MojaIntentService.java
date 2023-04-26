package com.example.projekt_3;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MojaIntentService extends IntentService {
    private static final String ACTION_DOWNLOAD = "com.example.projekt_3.action.download";
    public static final String ACTION_BROADCAST = "com.example.projekt_3.broadcast";
    public static final String URL_ADDRESS_PARAMETER = "com.example.projekt_3.extra.url_address_parameter";
    public static final String FILE_SIZE_PARAMETER = "com.example.projekt_3.extra.file_size_parameter";
    public static final String FILE_TYPE_PARAMETER = "com.example.projekt_3.extra.file_type_parameter";

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "com.example.projekt_3.channel1";
    public static final String PROGRESS_INFO = "com.example.projekt_3.progressInfo";

    private NotificationManager notificationManager;
    private int totalSize;
    private int mPobranychBajtow;
    private String urlAddress;
    private String fileSize;
    private String fileType;

    public static void startService(Context context, String urlAddress, String fileSize, String fileType){
        Intent intent = new Intent(context, MojaIntentService.class);
        intent.setAction(ACTION_DOWNLOAD);
        intent.putExtra(URL_ADDRESS_PARAMETER, urlAddress);
        intent.putExtra(FILE_SIZE_PARAMETER, fileSize);
        intent.putExtra(FILE_TYPE_PARAMETER, fileType);
        context.startService(intent);
    }

    public MojaIntentService(){
        super("Moja Intent Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        prepareNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification());

        if(intent != null){
            final String action = intent.getAction();
            if(ACTION_DOWNLOAD.equals(action)){
                urlAddress = intent.getStringExtra(URL_ADDRESS_PARAMETER);
                fileSize = intent.getStringExtra(FILE_SIZE_PARAMETER);
                fileType = intent.getStringExtra(FILE_TYPE_PARAMETER);
                doTask();
            }else { Log.e("intent_service", "nieznana akcja"); }
        }
        Log.d("intent_service","usługa wykonała zadanie");
    }

    private void doTask() {
        FileOutputStream strumienDoPliku = null;
        InputStream strumienZSieci = null;
        HttpsURLConnection polaczenie = null;
        ProgressInfo progressInfo = new ProgressInfo();

        try {
            URL url = new URL(urlAddress);
            File plikRoboczy = new File(url.getFile());
            File plikWyjsciowy = new File(Environment.getExternalStorageDirectory() + File.separator + plikRoboczy.getName());
            if (plikWyjsciowy.exists()) {
                plikWyjsciowy.delete();
            }
            polaczenie = (HttpsURLConnection) url.openConnection();
            polaczenie.setRequestMethod("GET");
            progressInfo.mRozmiar = totalSize = polaczenie.getContentLength();
            DataInputStream czytnik = new DataInputStream(polaczenie.getInputStream());
            strumienDoPliku = new FileOutputStream(plikWyjsciowy.getPath());
            byte[] bufor = new byte[totalSize];
            int pobrano = czytnik.read(bufor, 0, totalSize);
            while (pobrano != -1)
            {
                strumienDoPliku.write(bufor, 0, pobrano);
                progressInfo.mPobranychBajtow = mPobranychBajtow += pobrano;
                pobrano = czytnik.read(bufor, 0, totalSize);
                progressInfo.mStatus = ProgressInfo.Status.TRWA;
                notificationManager.notify(NOTIFICATION_ID, createNotification());
                sendMyBroadcast(progressInfo);
            }
            progressInfo.mStatus = ProgressInfo.Status.ZAKONCZONE;
        } catch (MalformedURLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            progressInfo.mStatus = ProgressInfo.Status.BLAD;
            e.printStackTrace();
        }finally {
            if (polaczenie != null)
            {
                polaczenie.disconnect();
            }
            if (strumienZSieci != null)
            {
                try
                {
                    strumienZSieci.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendMyBroadcast(ProgressInfo progressInfo) {
        Intent intent = new Intent(ACTION_BROADCAST);
        intent.putExtra(PROGRESS_INFO, progressInfo);
        sendBroadcast(intent);
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.putExtra(MainActivity.FILE_SIZE, fileSize);
        notificationIntent.putExtra(MainActivity.FILE_TYPE, fileType);
        notificationIntent.putExtra(MainActivity.URL_ADDRESS, urlAddress);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this)
                        .setContentTitle("Pobieranie pliku...")
                        .setProgress(100, progressValue(), false)
                        .setContentIntent(pendingIntent)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setWhen(System.currentTimeMillis());

        if(mPobranychBajtow != totalSize){ builder.setOngoing(false);
        }else{ builder.setOngoing(true); }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ builder.setChannelId(CHANNEL_ID); }

        return builder.build();
    }

    private int progressValue() { return totalSize != 0 ? (int) ((long) mPobranychBajtow * 100 / totalSize) : 0; }

    private void prepareNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
