package br.usjt.locsaver.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import br.usjt.locsaver.R;
import br.usjt.locsaver.model.Localizacao;

import static br.usjt.locsaver.helper.UsuarioFirebase.getIdentificadorUsuario;

public class AddLocalActivity extends AppCompatActivity {

    private final String TAG = "FIREBASE";
    private EditText descricao;
    private FirebaseFirestore db;
    private double latitude, longitude;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlocal);
        
        db = FirebaseFirestore.getInstance();
        descricao = findViewById(R.id.editTextDescricao);
        recuperarLocalizacaoUsuario();

    }
    
    public void cadastraLocal(View view){
        Localizacao localizacao = new Localizacao();
        localizacao.setDescription(descricao.getText().toString());
        localizacao.setCreatedAt(Timestamp.now());
        localizacao.setCoordinates(latitude, longitude);

        db.collection("usuarios")
                .document(getIdentificadorUsuario())
                .collection("locais")
                .add(localizacao)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

        startActivity(new Intent(this, LocalizacoesActivity.class));
    }

    private void recuperarLocalizacaoUsuario() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = location -> {

            latitude = location.getLatitude();
            longitude = location.getLongitude();

        };

        //Solicitar atualização de localização
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                10000,
                10,
                locationListener);

    }
}