package br.usjt.locsaver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import br.usjt.locsaver.R;
import br.usjt.locsaver.model.Localizacao;

public class CadastroActivity extends AppCompatActivity {

    private EditText descricao;
    private FirebaseFirestore db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        
        db = FirebaseFirestore.getInstance();
        descricao = findViewById(R.id.editTextDescricao);

    }
    
    public void cadastraLocal(View view){
        Localizacao localizacao = new Localizacao();
        localizacao.setDescription(descricao.getText().toString());
        localizacao.setCreatedAt(Timestamp.now());
        // TODO: set longitude, set latitude

        db.collection("locais")
                .add(localizacao.toMap())
                .addOnSuccessListener(documentReference -> Log.d("FIRESTORE", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("FIRESTORE", "Error adding document", e));

        startActivity(new Intent(this, MainActivity.class));
    }
}