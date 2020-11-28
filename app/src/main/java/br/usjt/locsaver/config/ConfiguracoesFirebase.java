package br.usjt.locsaver.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConfiguracoesFirebase {
    private static FirebaseFirestore database;
    private static FirebaseAuth auth;

    //retorna a instacia do FirebaseFireStore
    public static FirebaseFirestore getDatabase(){
        return FirebaseFirestore.getInstance();
    }

    //retorna a instacia do FirebaseAuth

    public static FirebaseAuth getFirebaseAutenticacao(){

        if(  auth == null ){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
