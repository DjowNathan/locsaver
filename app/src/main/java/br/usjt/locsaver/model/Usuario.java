package br.usjt.locsaver.model;

import android.util.Log;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;

import br.usjt.locsaver.config.ConfiguracoesFirebase;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;


    public Usuario() {

    }

    public void Salvar(){
        FirebaseFirestore firebaseRef = ConfiguracoesFirebase.getDatabase();
        firebaseRef.collection("usuarios")
                .add(this)
                .addOnSuccessListener(documentReference -> Log.d("FIRESTORE", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("FIRESTORE", "Error adding document", e));
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
