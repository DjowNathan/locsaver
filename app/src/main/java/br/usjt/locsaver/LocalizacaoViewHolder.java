package br.usjt.locsaver;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LocalizacaoViewHolder extends RecyclerView.ViewHolder {
    TextView textViewDescricao;
    TextView textViewDataCriacao;
    TextView textViewLatitude;
    TextView textViewLongitude;

    LocalizacaoViewHolder (View raiz){
        super(raiz);
        textViewDescricao = raiz.findViewById(R.id.textViewDescricao);
        textViewDataCriacao = raiz.findViewById(R.id.textViewDataCriacao);
        textViewLatitude = raiz.findViewById(R.id.textViewLatitude);
        textViewLongitude = raiz.findViewById(R.id.textViewLongitude);
    }
}
