package br.usjt.locsaver.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.usjt.locsaver.R;
import br.usjt.locsaver.model.Localizacao;

public class LocalizacaoAdapter extends RecyclerView.Adapter<LocalizacaoAdapter.LocalizacaoHolder> {
    private List<Localizacao> results = new ArrayList<>();
    private static ItemClickListener itemClickListener;
    @NonNull
    @Override
    public LocalizacaoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_localizacao, parent, false);
        return new LocalizacaoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalizacaoHolder holder, int position) {
        Localizacao localizacao = results.get(position);
        holder.textViewDescricao.setText(localizacao.getDescricao());
        holder.textViewDataCriacao.setText(
                localizacao.getCriadoEm().format(ofPattern("dd/MM/yyyy"))
        );
        holder.textViewLatitude.setText(
                String.format(
                        new Locale("pt","BR"),
                        "%2.5f",
                        localizacao.getLatitude()
                )
        );
        holder.textViewLongitude.setText(
                String.format(
                        new Locale("pt","BR"),
                        "%2.5f",
                        localizacao.getLongitude()
                )
        );
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    public void setResults(List<Localizacao> results) {
        this.results = results;
        notifyDataSetChanged();
    }
    class LocalizacaoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewDescricao;
        private TextView textViewDataCriacao;
        private TextView textViewLatitude;
        private TextView textViewLongitude;

        public LocalizacaoHolder(@NonNull View itemView) {
            super(itemView);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
            textViewDataCriacao = itemView.findViewById(R.id.textViewDataCriacao);
            textViewLatitude = itemView.findViewById(R.id.textViewLatitude);
            textViewLongitude = itemView.findViewById(R.id.textViewLongitude);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition(), results.get(getAdapterPosition()));
            }
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(int position, Localizacao localizacao);
    }
}