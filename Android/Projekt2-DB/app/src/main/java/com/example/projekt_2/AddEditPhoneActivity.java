package com.example.projekt_2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditPhoneActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.projekt_2.EXTRA_ID";
    public static final String EXTRA_PRODUCENT =
            "com.example.projekt_2.EXTRA_PRODUCENT";
    public static final String EXTRA_MODEL =
            "com.example.projekt_2.EXTRA_MODEL";
    public static final String EXTRA_ANDROID_VERSION =
            "com.example.projekt_2.EXTRA_ANDROID_VERSION";
    public static final String EXTRA_WEBPAGE =
            "com.example.projekt_2.EXTRA_WEBPAGE";

    private EditText editTextProducent;
    private EditText editTextModel;
    private EditText editTextAndroid_version;
    private EditText editTextWebpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        editTextProducent = findViewById(R.id.edit_text_producent);
        editTextModel = findViewById(R.id.edit_text_model);
        editTextAndroid_version = findViewById(R.id.edit_text_android_version);
        editTextWebpage = findViewById(R.id.edit_text_webpage);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle(R.string.edit_phone_title);

            editTextProducent.setText(intent.getStringExtra(EXTRA_PRODUCENT));
            editTextModel.setText(intent.getStringExtra(EXTRA_MODEL));
            editTextAndroid_version.setText(String.valueOf(intent.getIntExtra(EXTRA_ANDROID_VERSION, 1)));
            editTextWebpage.setText(intent.getStringExtra(EXTRA_WEBPAGE));
        } else {
            setTitle(R.string.add_phone_title);
        }

        Button buttonSavePhone = findViewById(R.id.button_save_phone);
        buttonSavePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePhone();
            }
        });
        Button buttonAbort = findViewById(R.id.button_abort);
        buttonAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button buttonWWW = findViewById(R.id.button_www);
        buttonWWW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adres_string = editTextWebpage.getText().toString();
                if (!adres_string.startsWith("http://") && !adres_string.startsWith("https://"))
                {adres_string = "http://" + adres_string;}
                Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(adres_string.toString()));
                startActivity(browserIntent);

            }
        });
    }

    private void savePhone() {
        String producent = editTextProducent.getText().toString();
        String model = editTextModel.getText().toString();
        String android_version = editTextAndroid_version.getText().toString();
        String webpage = editTextWebpage.getText().toString();

        if (producent.trim().isEmpty()) {
            Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            editTextProducent.setError(getResources().getString(R.string.error_empty));
            return;
        }
        if (model.trim().isEmpty()) {
            Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            editTextModel.setError(getResources().getString(R.string.error_empty));
            return;
        }
        if (android_version.trim().isEmpty()) {
            Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            editTextAndroid_version.setError(getResources().getString(R.string.error_empty));
            return;
        }
        if (webpage.trim().isEmpty()) {
            Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            editTextWebpage.setError(getResources().getString(R.string.error_empty));
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_PRODUCENT, producent);
        data.putExtra(EXTRA_MODEL, model);
        data.putExtra(EXTRA_ANDROID_VERSION, android_version);
        data.putExtra(EXTRA_WEBPAGE, webpage);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}