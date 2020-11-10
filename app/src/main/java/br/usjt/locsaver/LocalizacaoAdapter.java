package br.usjt.locsaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class LocalizacaoAdapter extends RecyclerView.Adapter<LocalizacaoViewHolder> {
    private List<Localizacao> localizacoes;
    private Context context;

    public LocalizacaoAdapter(List<Localizacao> localizacoes, Context context) {
        this.localizacoes = localizacoes;
        this.context = context;
    }

    @NonNull
    @Override
    public LocalizacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_localizacao, parent, false);
        return new LocalizacaoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalizacaoViewHolder holder, int position) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy hh:mm", new Locale("pt", "BR"));
        Localizacao l = localizacoes.get(position);
        holder.textViewDescricao.setText(l.getDescricao());
        holder.textViewDataCriacao.setText(formater.format(l.getCriadoEm()));
        holder.textViewLatitude.setText(String.format(new Locale("pt", "BR"), "%2.7f", l.getLatitude()));
        holder.textViewLongitude.setText(String.format(new Locale("pt", "BR"), "%2.7f", l.getLongitude()));
    }

    @Override
    public int getItemCount() {
        return localizacoes.size();
    }
}
