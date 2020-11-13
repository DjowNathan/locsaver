package br.usjt.locsaver.model;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LocalizacaoRepository {
    //TODO: Colher dados do firebase

    /**
     * @Exemplo
     * db.collection("users")
     *         .get()
     *         .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
     *             @Override
     *             public void onComplete(@NonNull Task<QuerySnapshot> task) {
     *                 if (task.isSuccessful()) {
     *                     for (QueryDocumentSnapshot document : task.getResult()) {
     *                         Log.d(TAG, document.getId() + " => " + document.getData());
     *                     }
     *                 } else {
     *                     Log.w(TAG, "Error getting documents.", task.getException());
     *                 }
     *             }
     *         });
     *
     */

    public LiveData<List<Localizacao>> getAllLocais() {
        return null;
    }

    public LiveData<Boolean> getSaveSucess() {
        return null;
    }

    public void getLocais() {
    }

    public void saveLocalizacao(Localizacao localizacao) {
    }

    public void updateLocalizacao(Localizacao localizacao) {
    }
}
