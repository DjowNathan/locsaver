package br.usjt.locsaver.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import br.usjt.locsaver.R;
import br.usjt.locsaver.model.Localizacao;
import br.usjt.locsaver.model.LocalizacaoViewModel;

import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocalizacaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocalizacaoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private LocalizacaoViewModel localizacaoViewModel;
    private LocalizacaoAdapter adapter;
    private ProgressBar progressBar;

    private String mParam1;
    private String mParam2;

    public LocalizacaoFragment() {}

    public static LocalizacaoFragment newInstance(String param1, String param2) {
        LocalizacaoFragment fragment = new LocalizacaoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        adapter = new LocalizacaoAdapter();
        // BUGFIX: contexto this não ta sendo aceito pelo construtor do ViewModelProvider
        localizacaoViewModel = new ViewModelProvider(this).get(LocalizacaoViewModel.class);
        localizacaoViewModel.getLocaisResponseLiveData().observe(this, (Observer<List<Localizacao>>) locais -> {
            if (locais != null) {
                adapter.setResults(locais);
            }
            progressBar.setVisibility(View.GONE);

        });
        // seta o click no card enviar pra outra fragment
//        adapter.setOnItemClickListener((position, localizacao) -> {
//            replaceFragment(R.id.frameLayout,
//                    LocalizacaoFragment.newInstance("",localizacao),
//                    "CONTACTFRAGMENT",
//                    "contato_click");
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_localizacao, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.localizacaoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        localizacaoViewModel.getLocais();
    }

    protected void novaLocalizacao(View view){
        startActivity(new Intent(getActivity(), CadastroActivity.class));
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }


}