package br.usjt.locsaver.service;

import android.annotation.SuppressLint;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.EditText;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Query;

import br.usjt.locsaver.model.Localizacao;

public final class LocalizacaoService {

    public static LocalizacaoService instance;

    private double latitude, longitude;
    private final LocalizacaoRepository localizacaoRepository = new LocalizacaoRepository();

    private LocalizacaoService() {
    }

    public static synchronized LocalizacaoService getInstance() {
        if (instance == null) {
            return new LocalizacaoService();
        }
        return instance;
    }

    public void cadastraLocal(EditText descricao, LocationManager locationManager) {
        recuperarLocalizacaoUsuario(locationManager);

        Localizacao localizacao = new Localizacao();
        localizacao.setDescription(descricao.getText().toString());
        localizacao.setCreatedAt(Timestamp.now());
        localizacao.setCoordinates(latitude, longitude);

        localizacaoRepository.cadastraLocal(localizacao);

    }

    public void deletaLocal(String id) {
        localizacaoRepository.deletaLocal(id);
    }

    public Query getListaLocaisDoUsuario() {
        return localizacaoRepository.getQueryListaLocais();
    }

    @SuppressLint("MissingPermission")
    private void recuperarLocalizacaoUsuario(LocationManager locationManager) {

        LocationListener locationListener = location -> {

            latitude = location.getLatitude();
            longitude = location.getLongitude();

        };

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                10000,
                10,
                locationListener);


    }
}
