package br.usjt.locsaver.activity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.usjt.locsaver.R;
import br.usjt.locsaver.service.LocalizacaoService;

public class AddLocalActivity extends AppCompatActivity {

    private LocalizacaoService localizacaoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlocal);

        localizacaoService = LocalizacaoService.getInstance();

    }

    public void cadastraLocal(View view) {
        EditText descricao = findViewById(R.id.editTextDescricao);

        localizacaoService.cadastraLocal(
                descricao,
                (LocationManager) this.getSystemService(Context.LOCATION_SERVICE)
        );

        startActivity(new Intent(this, LocalizacoesActivity.class));
    }
}