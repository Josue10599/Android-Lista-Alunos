package com.fulltime.listaalunos.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nome;
    private String telefoneFixo;
    private String telefoneCelular;
    private String email;

    private Calendar momentoDeCriacao = Calendar.getInstance();
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return getNome();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean idValido() {
        return id > 0;
    }

    public Calendar getMomentoDeCriacao() {
        return momentoDeCriacao;
    }

    public void setMomentoDeCriacao(Calendar momentoDeCriacao) {
        this.momentoDeCriacao = momentoDeCriacao;
    }

}
