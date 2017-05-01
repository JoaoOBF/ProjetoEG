package br.com.joaootaviobf.projetoeg;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by joao otavio on 19/04/2017.
 */

public class FragmentDiolinux extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diolinux, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_diolux);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new UpdateAppsAsyncTask().execute();
            }
        });
        new UpdateAppsAsyncTask().execute();
        return view;


    }

    public class UpdateAppsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Document d;
        private ArrayList<Noticia> listaDeNoticiasFinais;


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                d = Jsoup.connect("http://www.diolinux.com.br").get();
                listaDeNoticiasFinais = new ArrayList<>();
                processaDados();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mRecyclerView.setAdapter(new MyAdapter(getListaDeNoticias(),getContext()));
        }

        public String processaDados() {
            if (listaDeNoticiasFinais != null) {
                listaDeNoticiasFinais.clear();
            }

            Elements listaNoticiasDesordenadaTextoCompleto = d.select("div.item-title a ");
            Elements listaNoticiasOrdenadas = d.select("ul.posts li a ");
            String txt = new String();
            for (int i = 0; i < listaNoticiasOrdenadas.size(); i++) {
                Element noticia = (Element) listaNoticiasOrdenadas.get(i);
                String link = noticia.attr("href");
                String noticiaShortText = noticia.text();
                for (int j = 0; j < listaNoticiasDesordenadaTextoCompleto.size(); j++) {
                    Element noticiaMancheteCompleta = (Element) listaNoticiasDesordenadaTextoCompleto.get(j);
                    if (noticiaMancheteCompleta.attr("href").equals(link)) {
                        noticiaShortText = noticiaMancheteCompleta.text();
                    }
                }
                Noticia n = new Noticia(link, noticiaShortText);
                listaDeNoticiasFinais.add(n);
                txt += noticiaShortText + "\n" + link + "\n\n";
            }
            return txt;
        }

        public ArrayList<Noticia> getListaDeNoticias() {
            return listaDeNoticiasFinais;
        }
    }




    }

