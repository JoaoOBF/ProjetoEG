package br.com.joaootaviobf.projetoeg;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by joao otavio on 05/05/2017.
 */

public class MineradorOpenSourcer {
    private Document d;
    private ArrayList<Noticia> listaDeNoticiasFinais;

    public MineradorOpenSourcer() {
        listaDeNoticiasFinais = new ArrayList<>();
    }

    public void conectaOpenSource() throws IOException {
        d = Jsoup.connect("https://opensource.com/").get();
        processaDados();
    }

    public void processaDados() {
        Elements listaLinks = d.select("div.pane-node-title h2 a");
        for (int i = 0; i < listaLinks.size(); i++) {
            String link = listaLinks.get(i).attr("href");
            String manchete = listaLinks.get(i).toString();
            link = completarLink(link);
            manchete = filtraMachete(manchete);
            this.listaDeNoticiasFinais.add(new Noticia(link, manchete));
        }
    }
    /**
     * @return the listaDeNoticiasFinais
     */
    public ArrayList<Noticia> getListaDeNoticiasFinais() {
        return listaDeNoticiasFinais;
    }

    private String completarLink(String link) {
        return "https://opensource.com"+link;
    }

    private String filtraMachete(String manchete) {
        while (manchete.charAt(0) != '>') {
            manchete = manchete.substring(1);
        }
        manchete = manchete.substring(1);
        while (manchete.charAt(manchete.length() - 1) != '<') {
            manchete = manchete.substring(0, manchete.length() - 1);
        }
        manchete = manchete.substring(0, manchete.length() - 1);
        return manchete;
    }

}
