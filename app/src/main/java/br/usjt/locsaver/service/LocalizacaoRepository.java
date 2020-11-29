package br.usjt.locsaver.service;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.usjt.locsaver.model.Localizacao;

import static br.usjt.locsaver.helper.UsuarioFirebase.getIdentificadorUsuario;

final class LocalizacaoRepository {

    private final String TAG = "FIREBASE";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void cadastraLocal(Localizacao localizacao) {

        db.collection("usuarios")
                .document(getIdentificadorUsuario())
                .collection("locais")
                .add(localizacao)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

    }

    public void deletaLocal(String id) {

        db.collection("usuarios")
                .document(getIdentificadorUsuario())
                .collection("locais")
                .document(id)
                .delete()
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Localização Deletada!"))
                .addOnFailureListener(e -> Log.w(TAG, "Erro ao Deletado", e));

    }

    public Query getQueryListaLocais() {
        return db.collection("usuarios")
                .document(getIdentificadorUsuario())
                .collection("locais")
                .orderBy("createdAt");
    }
}
