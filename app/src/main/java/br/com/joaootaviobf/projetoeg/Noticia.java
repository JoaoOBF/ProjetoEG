package br.com.joaootaviobf.projetoeg;

/**
 * Created by joao otavio on 19/04/2017.
 */

public class Noticia {

    String link;
    String manchete;

    public Noticia(String link, String manchete) {
        this.link = link;
        this.manchete = manchete;
    }

    public String getLink() {
        return link;
    }

    public String getManchete() {
        return manchete;
    }


}