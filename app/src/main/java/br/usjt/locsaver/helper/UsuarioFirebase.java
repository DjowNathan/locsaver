package br.usjt.locsaver.helper;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.usjt.locsaver.config.ConfiguracoesFirebase;
import br.usjt.locsaver.activity.LocalizacoesActivity;

public class UsuarioFirebase {
    public static FirebaseUser getUsuarioAtual(){
        FirebaseAuth usuario = ConfiguracoesFirebase.getFirebaseAutenticacao();
        return usuario.getCurrentUser();
    }


    public static void redirecionaUsuarioLogado(Activity activity){

        FirebaseUser user = getUsuarioAtual();
        if(user != null ){

            Intent i = new Intent(activity, LocalizacoesActivity.class);
            activity.startActivity(i);

        }

    }

    public static String getIdentificadorUsuario(){

        return getUsuarioAtual().getUid();
    }
}
