package com.example.projekt_3;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


class Wrapper{
    public Integer file_size;
    public String file_type;
}

    class CheckFile extends AsyncTask<String, Integer, Wrapper> {
        public Activity activity;


        public CheckFile(Activity _activity) {
            this.activity = _activity;
        }

        @Override
    protected Wrapper doInBackground(String... params) {
        Wrapper w = new Wrapper();
        HttpsURLConnection polaczenie = null;
        try {
            EditText link = this.activity.findViewById(R.id.file_link);
            URL url = new URL(link.getText().toString());
            polaczenie = (HttpsURLConnection) url.openConnection();
            polaczenie.setRequestMethod("GET");
            w.file_type = polaczenie.getContentType();
            w.file_size = polaczenie.getContentLength();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (polaczenie != null) polaczenie.disconnect();
        }
        return w;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
    }
    @Override
    protected void onPostExecute(Wrapper result) {
        TextView file_size = this.activity.findViewById(R.id.file_size);
        TextView file_type = this.activity.findViewById(R.id.file_type);
        if (result.file_size==null){
            file_size.setText(R.string.file_not_found);
        }else if (result.file_size==-1){
            file_size.setText(R.string.file_is_encrypted);
        }
        else{
            file_size.setText(String.valueOf(result.file_size));
        }
        file_type.setText(result.file_type);
    }

}