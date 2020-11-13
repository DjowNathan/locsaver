package br.usjt.locsaver.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;

import br.usjt.locsaver.R;
import br.usjt.locsaver.model.Localizacao;

public class CadastroActivity extends AppCompatActivity {

    EditText descricao;
    FirebaseFirestore db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        
        db = FirebaseFirestore.getInstance();
        descricao = findViewById(R.id.editTextDescricao);

    }
    
    protected void cadastraLocal(){
        Localizacao localizacao = new Localizacao();
        localizacao.setDescricao(descricao.getText().toString());
        localizacao.setCriadoEm(LocalDate.now());
        // TODO: set longitude, set latitude

        db.collection("locais")
                .add(localizacao)
                .addOnSuccessListener(documentReference -> Log.d("FIRESTORE", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("FIRESTORE", "Error adding document", e));
    }
}