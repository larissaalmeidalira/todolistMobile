package com.example.todolistapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tarefa {
    @PrimaryKey(autoGenerate = true)
    private Long idTarefa;
    private String titulo;
    private String descricao;
    private Long dataCriacao;
    private Long dataPrevista;
    private Long dataFinalizada;


    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Long dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Long dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Long getDataFinalizada() {
        return dataFinalizada;
    }

    public void setDataFinalizada(Long dataFinalizada) {
        this.dataFinalizada = dataFinalizada;
    }

    public boolean isConcluida(){
        return dataFinalizada != 0;
    }
}
