package com.example.sroda16_15;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InteraktywnyAdapterTablicy extends RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder> {
    private List<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;

    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen) {
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View wiersz = mPompka.inflate(R.layout.list_row, parent, false);
        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolder, int numerWiersza) {
        ModelOceny ocena = mListaOcen.get(numerWiersza);
        ocenyViewHolder.label.setText(ocena.getNazwa());
        ocenyViewHolder.grupaOceny.setTag(mListaOcen.get(numerWiersza));
        ocenyViewHolder.grupaOceny.check(R.id.r2);
    }

    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }

    public class OcenyViewHolder extends RecyclerView.ViewHolder {
        public RadioGroup grupaOceny;
        public TextView label;

        public OcenyViewHolder(@NonNull View glownyElementWiersza) {
            super(glownyElementWiersza);
            this.label = glownyElementWiersza.findViewById(R.id.label);
            this.grupaOceny = glownyElementWiersza.findViewById(R.id.grupaOceny);

            grupaOceny.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(checkedId);

                    boolean isChecked = checkedRadioButton.isChecked();
                    if (isChecked) {
                        ModelOceny element = (ModelOceny) grupaOceny.getTag();
                        element.setOcena(Integer.parseInt((String) checkedRadioButton.getText()));
                    }
                }
            });
        }

    }

}