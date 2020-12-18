package com.nathan.filmesapp.model;

import java.io.Serializable;

public class Filme implements Serializable {

    private String id;
    private int drawableId;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    private String poster;
    private String nome;
    private String descricao;
    private int ano;
    private String genero;

    public Filme (String id, int drawableId, String nome, String descricao, int ano, String genero) {
        this.id = id;
        this.drawableId = drawableId;
        this.nome = nome;
        this.descricao = descricao;
        this.ano = ano;
        this.genero = genero;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
