package com.fulltime.listaalunos.asynctask;

import android.content.Context;

import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.model.Telefone;

public class SalvaAlunoAsyncTask extends BaseSalvaEditaAluno {
    private final AlunoDao alunoDao;
    private final TelefoneDao telefoneDao;
    private final Aluno aluno;
    private final Telefone[] telefones;

    public SalvaAlunoAsyncTask(Context context, Aluno aluno,
                               ProcessoFinalizado listener, Telefone... telefones) {
        super(listener);
        this.alunoDao = DataBase.getInstance(context).getAlunoDao();
        this.telefoneDao = DataBase.getInstance(context).getTelefoneDao();
        this.aluno = aluno;
        this.telefones = telefones;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        long idAluno = alunoDao.salva(aluno);
        salvaTelefoneDoAluno(idAluno);
        return null;
    }

    private void salvaTelefoneDoAluno(long idAluno) {
        for (Telefone telefone : telefones) {
            telefone.setIdAluno(idAluno);
            telefoneDao.salvarTelefone(telefone);
        }
    }
}
