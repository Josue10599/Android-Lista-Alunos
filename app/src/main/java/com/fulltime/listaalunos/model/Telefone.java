package com.fulltime.listaalunos.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Telefone {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String numero;
    private TipoTelefone tipo;
    @ForeignKey(entity = Aluno.class,
            parentColumns = "id",
            childColumns = "idAluno",
            onDelete = CASCADE,
            onUpdate = CASCADE)
    private long idAluno;

    public Telefone(String telefone, TipoTelefone tipo, long idAluno) {
        this.numero = telefone;
        this.tipo = tipo;
        this.idAluno = idAluno;
    }

    public Telefone() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    @NonNull
    @Override
    public String toString() {
        return numero + "";
    }
}
