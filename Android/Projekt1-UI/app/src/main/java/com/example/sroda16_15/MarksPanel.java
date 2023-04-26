package com.example.sroda16_15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;


public class MarksPanel extends AppCompatActivity {
    private ArrayList<ModelOceny> mListaOcen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_panel);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mListaOcen = new ArrayList<>();
        String[] nazwyPrzedmiotow = getResources().getStringArray(R.array.przedmioty);
        for (int i = 0; i < Integer.parseInt(message); i++)
            mListaOcen.add(new ModelOceny(nazwyPrzedmiotow[i].toString(), 2));

        RecyclerView numberRecyclerView = findViewById(R.id.numberRecyclerView);
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, mListaOcen);
        numberRecyclerView.setAdapter(adapter);
        numberRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        Button averageButton = findViewById(R.id.meanButton);
        averageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sum = 0;
                for (ModelOceny o : mListaOcen)
                    sum += o.getOcena();
                double ileOcen = Double.parseDouble(message);

                Intent data = new Intent();
                data.putExtra("EXTRA_AVERAGE", sum / ileOcen);
                if ((sum / ileOcen) >= 3) {
                    setResult(RESULT_OK, data);
                } else {
                    setResult(2, data);
                }

                finish();
            }
        });
    }
}