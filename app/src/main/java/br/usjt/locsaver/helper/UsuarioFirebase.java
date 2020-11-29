package br.usjt.locsaver.helper;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.usjt.locsaver.activity.LocalizacoesActivity;

public class UsuarioFirebase {
    public static FirebaseUser getUsuarioAtual(){

        FirebaseAuth usuario = FirebaseAuth.getInstance();
        return usuario.getCurrentUser();

    }


    public static void redirecionaUsuarioLogado(Activity activity){

        if( getUsuarioAtual() != null )
            activity.startActivity( new Intent(activity, LocalizacoesActivity.class) );

    }

    public static String getIdentificadorUsuario(){

        return getUsuarioAtual().getUid();

    }
}
