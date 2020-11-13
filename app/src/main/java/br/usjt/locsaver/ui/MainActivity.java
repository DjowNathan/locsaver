package br.usjt.locsaver.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import br.usjt.locsaver.R;
import br.usjt.locsaver.model.Localizacao;

public class MainActivity extends AppCompatActivity {
    private LocalizacaoViewModel localizacaoViewModel;
    private List<Localizacao> locais;
    private LocalizacaoAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new LocalizacaoAdapter();
        localizacaoViewModel = new ViewModelProvider(this).get(LocalizacaoViewModel.class);
        localizacaoViewModel.getLocaisResponseLiveData().observe(this, (Observer<List<Localizacao>>) locais -> {
            if (locais != null) {
                adapter.setResults(locais);
            }
            progressBar.setVisibility(View.GONE);

        });
        adapter.setOnItemClickListener((position, contact) -> {
            replaceFragment(R.id.frameLayout,
                    ContactFragment.newInstance("",contact),
                    "CONTACTFRAGMENT",
                    "contato_click");
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        localizacaoViewModel.getContacts();
    }
}