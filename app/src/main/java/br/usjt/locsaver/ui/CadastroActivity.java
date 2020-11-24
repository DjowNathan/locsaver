package br.usjt.locsaver.ui;

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

public class CadastroActivity extends AppCompatActivity {

    private EditText descricao;
    private FirebaseFirestore db;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double latitude, longitude;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        
        db = FirebaseFirestore.getInstance();
        descricao = findViewById(R.id.editTextDescricao);
        recuperarLocalizacaoUsuario();

    }
    
    public void cadastraLocal(View view){
        Localizacao localizacao = new Localizacao();
        localizacao.setDescription(descricao.getText().toString());
        localizacao.setCreatedAt(Timestamp.now());
        localizacao.setCoordinates(latitude, longitude);

        db.collection("locais")
                .add(localizacao.toMap())
                .addOnSuccessListener(documentReference -> Log.d("FIRESTORE", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("FIRESTORE", "Error adding document", e));

        startActivity(new Intent(this, MainActivity.class));
    }

    private void recuperarLocalizacaoUsuario() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //recuperar latitude e longitude

                latitude = location.getLatitude();
                longitude = location.getLongitude();


            }


            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
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
                locationManager.GPS_PROVIDER,
                10000,
                10,
                locationListener);

    };
}