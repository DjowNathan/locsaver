package br.usjt.locsaver.model;

import android.util.Log;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;

public class Usuario {
    private final String TAG = "FIREBASE";

    @DocumentId
    private String id;
    private String nome;
    private String email;
    private String senha;


    public Usuario() {}

    public void Salvar(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios")
                .document(this.id)
                .set(this)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + this.id))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
