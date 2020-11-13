package br.usjt.locsaver.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.usjt.locsaver.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, new LocalizacaoFragment())
                    .commit();
        }
    }
}