package br.usjt.locsaver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Locale;

import br.usjt.locsaver.R;
import br.usjt.locsaver.model.Localizacao;

public class MainActivity extends AppCompatActivity {
    private RecyclerView localizacaoRecyclerView;
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter<Localizacao, LocalizacaoViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        localizacaoRecyclerView = findViewById(R.id.localizacaoRecyclerView);


        Query query = db.collection("locais").orderBy("createdAt");
        FirestoreRecyclerOptions<Localizacao> options = new FirestoreRecyclerOptions.Builder<Localizacao>()
                .setQuery(query, Localizacao.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Localizacao, LocalizacaoViewHolder>(options) {
            @NonNull
            @Override
            public LocalizacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_localizacao, parent, false);
                return new LocalizacaoViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull LocalizacaoViewHolder holder, int position, @NonNull Localizacao localizacao) {
                Locale localeBrazil = new Locale("pt","BR");

                Log.d("FIRESTORE", localizacao.toString());

                holder.textViewDescricao.setText(localizacao.getDescription());
                holder.textViewDataCriacao.setText(
                        new SimpleDateFormat("dd/MM/yyyy hh:mm",localeBrazil).format(localizacao.getCreatedAt().toDate())
                );
                holder.textViewLatitude.setText(
                        String.format(
                                localeBrazil,
                                "%2.5f",
                                localizacao.getCoordinates().getLatitude()
                        )
                );
                holder.textViewLongitude.setText(
                        String.format(
                                localeBrazil,
                                "%2.5f",
                                localizacao.getCoordinates().getLongitude()
                        )
                );
            }
        };

        localizacaoRecyclerView.hasFixedSize();
        localizacaoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        localizacaoRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void novaLocalizacao(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }



    private class LocalizacaoViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDescricao, textViewLatitude, textViewDataCriacao, textViewLongitude;

        public LocalizacaoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
            textViewDataCriacao = itemView.findViewById(R.id.textViewDataCriacao);
            textViewLongitude = itemView.findViewById(R.id.textViewLongitude);
            textViewLatitude = itemView.findViewById(R.id.textViewLatitude);

        }
    }
}

