package com.fulltime.listaalunos.asynctask;

import android.content.Context;

import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.model.Telefone;

public class EditaAlunoAsyncTask extends BaseSalvaEditaAluno {
    private final AlunoDao alunoDao;
    private final TelefoneDao telefoneDao;
    private final Aluno aluno;
    private final Telefone[] telefones;

    public EditaAlunoAsyncTask(Context context, Aluno aluno,
                               ProcessoFinalizado listener, Telefone... telefone) {
        super(listener);
        alunoDao = DataBase.getInstance(context).getAlunoDao();
        telefoneDao = DataBase.getInstance(context).getTelefoneDao();
        this.aluno = aluno;
        this.telefones = telefone;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDao.edita(aluno);
        telefoneDao.atualizaTelefone(telefones);
        return null;
    }
}
