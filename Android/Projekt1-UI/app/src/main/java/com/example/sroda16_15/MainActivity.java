package com.example.sroda16_15;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.sroda16_15.MESSAGE";

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Button oceny = findViewById(R.id.oceny);
        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);
        EditText marks = findViewById(R.id.marks);

        if (marks.getError() == null
                && name.getError() == null
                && surname.getError() == null
                && !name.getText().toString().isEmpty()
                && !marks.getText().toString().isEmpty()
                && !surname.getText().toString().isEmpty()
                && !(Integer.parseInt(marks.getText().toString()) > 15)
                && !(Integer.parseInt(marks.getText().toString()) < 5)) {
            oceny.setVisibility(View.VISIBLE);
        } else {
            oceny.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Button oceny = findViewById(R.id.oceny);

        Button button_finish = findViewById(R.id.button_finish);
        TextView response_label = findViewById(R.id.response_label);
        outState.putInt("button_visibility", oceny.getVisibility());
        outState.putInt("button_finish_visibility", button_finish.getVisibility());
        outState.putInt("response_label_visibility", response_label.getVisibility());
        outState.putString("button_finish_text", button_finish.getText().toString());
        outState.putString("response_label_text", response_label.getText().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button oceny = findViewById(R.id.oceny);

        Button button_finish = findViewById(R.id.button_finish);
        TextView response_label = findViewById(R.id.response_label);


        if (savedInstanceState != null) {
            int buttonVisibility = savedInstanceState.getInt("button_visibility", 0);
            oceny.setVisibility(buttonVisibility == View.VISIBLE ? View.VISIBLE : View.GONE);
            int button_finish_visibility = savedInstanceState.getInt("button_finish_visibility", 0);
            button_finish.setVisibility(button_finish_visibility == View.VISIBLE ? View.VISIBLE : View.GONE);
            int response_label_visibility = savedInstanceState.getInt("response_label_visibility", 0);
            response_label.setVisibility(response_label_visibility == View.VISIBLE ? View.VISIBLE : View.GONE);
            response_label.setText(savedInstanceState.getString("response_label_text"));
            button_finish.setText(savedInstanceState.getString("button_finish_text"));

        }

        EditText name = findViewById(R.id.name);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String nameS = name.getText().toString();
                if (!hasFocus && nameS.isEmpty()) {
                    name.setError(getString(R.string.error_empty));
                    Toast.makeText(MainActivity.this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show();

                }
            }
        });


        EditText surname = findViewById(R.id.surname);
        surname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String surnameS = surname.getText().toString();

                if (!hasFocus && surnameS.isEmpty()) {
                    surname.setError(getString(R.string.error_empty));
                    Toast.makeText(MainActivity.this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show();
                }
            }
        });

        EditText marks = findViewById(R.id.marks);
        marks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String marksS = marks.getText().toString();

                if (!hasFocus && !marksS.isEmpty()) {
                    Integer numer = Integer.parseInt(marksS.trim());
                    if (numer < 5 || numer > 15) {
                        marks.setError(getString(R.string.error_value));
                        Toast.makeText(MainActivity.this, getString(R.string.error_value), Toast.LENGTH_SHORT).show();

                    }
                } else if (!hasFocus && marksS.isEmpty()) {
                    marks.setError(getString(R.string.error_empty));
                    Toast.makeText(MainActivity.this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show();

                }
            }
        });
        oceny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MarksPanel.class);
                EditText ileOcen = (EditText) findViewById(R.id.marks);
                String message = ileOcen.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivityForResult(intent, 1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            double result = data.getDoubleExtra("EXTRA_AVERAGE", 0);
            Button button_finish = findViewById(R.id.button_finish);
            TextView response_label = findViewById(R.id.response_label);
            response_label.setVisibility(View.VISIBLE);
            button_finish.setVisibility(View.VISIBLE);
            response_label.setText(getString(R.string.show_result) + String.format(" %.2f", result));
            if (resultCode == RESULT_OK) {
                button_finish.setText(R.string.button_finish_good);
                button_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, R.string.toast_finish_good, Toast.LENGTH_SHORT).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    }
                });
            }
            if (resultCode == 2) {
                button_finish.setText(R.string.button_finish_bad);
                button_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, R.string.toast_finish_bad, Toast.LENGTH_SHORT).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    }
                });
            }
        }
    }
}
