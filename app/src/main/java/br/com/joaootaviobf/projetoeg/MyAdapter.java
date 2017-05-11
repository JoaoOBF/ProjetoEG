package br.com.joaootaviobf.projetoeg;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joao otavio on 26/04/2017.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private ArrayList<Noticia> listaDados;
    private Context mContext;

    public MyAdapter(ArrayList<Noticia> listaDados, Context ctx) {
        this.listaDados = listaDados;
        this.mContext = ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView manchete, link;
        private LinearLayout linearLayout;


        public ViewHolder(View v) {
            super(v);
            manchete = (TextView) v.findViewById(R.id.Manchete);
            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(), "FuturaBkBTBook.ttf");
            manchete.setTypeface(custom_font);
            linearLayout = (LinearLayout) v.findViewById(R.id.linearlayout);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inf_itens, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder Viewholder, int position) {

        ViewHolder holder = (ViewHolder) Viewholder;
        final Noticia dados = listaDados.get(position);
        holder.manchete.setText(dados.getManchete());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = dados.getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listaDados.size();
    }
}
