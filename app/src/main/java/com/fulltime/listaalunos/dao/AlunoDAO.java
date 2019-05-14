package com.fulltime.listaalunos.dao;

import com.fulltime.listaalunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int id = 1;

    public void salva(Aluno aluno) {
        aluno.setId(id);
        alunos.add(aluno);
        id++;
    }

    public void edita(Aluno aluno) {
        int posicao = alunos.size() + 1;
        for (Aluno estudante : alunos) {
            if (estudante.getId() == aluno.getId()) {
                posicao = alunos.indexOf(estudante);
            }
        }
        if (posicao != alunos.size() + 1) {
            alunos.set(posicao, aluno);
        }
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        if(aluno != null) {
            alunos.remove(aluno);
        }
    }
}
