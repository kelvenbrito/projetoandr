package br.com.softlearn.prefsframework.Data;

import java.io.Serializable;

/**
 * Created by professor on 24/08/2017.
 */

public class Boletim implements Serializable{
    private int id;
    private String disciplina;
    private Double media;
    private int faltas;

    public Boletim(int id, String disciplina, Double media, int faltas) {
        this.id = id;
        this.disciplina = disciplina;
        this.media = media;
        this.faltas = faltas;
    }

    public Boletim(String disciplina, Double media, int faltas) {
        this.disciplina = disciplina;
        this.media = media;
        this.faltas = faltas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
}
