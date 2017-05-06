package br.com.joaootaviobf.projetoeg;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by joao otavio on 19/04/2017.
 */

public class FragmentBrLinux extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MineradorBRLinux mineradorBRLinux;
    private ArrayList<Noticia> listaDeNoticiasFinais;
    private SwipeRefreshLayout swipeRefresh;
    private int contNoticias;
    private boolean att;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.br_linux,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_diolux);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               att=true;
                new UpdateAppsAsyncTask().execute();
                swipeRefresh.setRefreshing(false);
            }
        });
        //swipeRefresh.setColorSchemeResources(R.color.vermelho, R.color.blue, R.color.orange);
        new UpdateAppsAsyncTask().execute();

        return view;
    }

    public class UpdateAppsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
           mineradorBRLinux = new MineradorBRLinux();
            try {
                mineradorBRLinux.conectaBrLinux();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listaDeNoticiasFinais = mineradorBRLinux.getListaDeNoticiasFinais();
            if (listaDeNoticiasFinais!=null){
                if (contNoticias == listaDeNoticiasFinais.size() && att) {
                   att=false;
                    Toast.makeText(getContext(), "Não há novas nóticias", Toast.LENGTH_SHORT).show();
                    contNoticias = listaDeNoticiasFinais.size();
                } else {
                    att=false;
                    contNoticias = listaDeNoticiasFinais.size();
                }
                mRecyclerView.setAdapter(new MyAdapter(listaDeNoticiasFinais,getContext()));
            }

        }

    }
}
