package br.usjt.locsaver.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Locale;

import br.usjt.locsaver.R;
import br.usjt.locsaver.helper.Permissoes;
import br.usjt.locsaver.model.Localizacao;

import static br.usjt.locsaver.helper.UsuarioFirebase.getIdentificadorUsuario;

public class LocalizacoesActivity extends AppCompatActivity {
    private final String TAG = "FIREBASE";
    private RecyclerView localizacaoRecyclerView;
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter<Localizacao, LocalizacoesActivity.LocalizacaoViewHolder> adapter;
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacoes);

        db = FirebaseFirestore.getInstance();
        localizacaoRecyclerView = findViewById(R.id.localizacaoRecyclerView);

        //Validar Permissoes
        Permissoes.validarPermissoes(permissoes, this, 1);

        Query query = db.collection("usuarios")
                        .document(getIdentificadorUsuario())
                        .collection("locais")
                        .orderBy("createdAt");

        FirestoreRecyclerOptions<Localizacao> options = new FirestoreRecyclerOptions.Builder<Localizacao>()
                .setQuery(query, Localizacao.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Localizacao, LocalizacoesActivity.LocalizacaoViewHolder>(options) {
            @NonNull
            @Override
            public LocalizacoesActivity.LocalizacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_localizacao, parent, false);
                return new LocalizacoesActivity.LocalizacaoViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull LocalizacoesActivity.LocalizacaoViewHolder holder,
                                            int position, @NonNull Localizacao localizacao) {
                Locale localeBrazil = new Locale("pt","BR");

                Log.d(TAG, localizacao.toString());

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {


                        db.collection("usuarios")
                                .document(getIdentificadorUsuario())
                                .collection("locais")
                                .document(localizacao.getId())
                                .delete()
                                .addOnSuccessListener(aVoid -> Log.d(TAG, "Localização Deletada!"))
                                .addOnFailureListener(e -> Log.w(TAG, "Erro ao Deletado", e));

                        return true;

                    }
                });

                holder.textViewDescricao.setText(localizacao.getDescription());
                holder.textViewDataCriacao.setText(
                        new SimpleDateFormat("dd/MM/yyyy HH:mm",localeBrazil).format(localizacao.getCreatedAt().toDate())
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
        startActivity(new Intent(this, AddLocalActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults) {
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

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

