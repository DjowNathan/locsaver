package br.usjt.locsaver.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocalizacaoViewModel extends AndroidViewModel {
    private LocalizacaoRepository localizacaoRepository;
    private LiveData<List<Localizacao>> contatosResponseLiveData;
    private LiveData<Boolean> saveWithSuccessLiveData;
    public LocalizacaoViewModel(@NonNull Application application) {
        super(application);
        Log.d("RESPOSTA","CRIACAO DA VIEWMODEL");
        localizacaoRepository = new LocalizacaoRepository();
        contatosResponseLiveData = localizacaoRepository.getAllLocais();
        saveWithSuccessLiveData = localizacaoRepository.getSaveSucess();
    }
    public void getLocais() {
        localizacaoRepository.getLocais();
    }
    public LiveData<List<Localizacao>> getLocaisResponseLiveData() {
        return contatosResponseLiveData;
    }
    public LiveData<Boolean> getSaveSuccess() {
        return saveWithSuccessLiveData;
    }
    public void saveLocalizacao(Localizacao localizacao){
        localizacaoRepository.saveLocalizacao(localizacao);
    }
    public void updateLocalizacao(Localizacao localizacao){
        localizacaoRepository.updateLocalizacao(localizacao);
    }
}