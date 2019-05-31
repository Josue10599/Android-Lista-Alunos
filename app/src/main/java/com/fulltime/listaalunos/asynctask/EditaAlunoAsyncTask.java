package com.fulltime.listaalunos.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.model.Telefone;

public class EditaAlunoAsyncTask extends AsyncTask<Void, Void, Void> {

    private final AlunoDao alunoDao;
    private final TelefoneDao telefoneDao;
    private final Aluno aluno;
    private final Telefone[] telefones;
    private final AlunoEditadoListener listener;

    public EditaAlunoAsyncTask(Context context, Aluno aluno, AlunoEditadoListener listener, Telefone... telefone) {
        alunoDao = DataBase.getInstance(context).getAlunoDao();
        telefoneDao = DataBase.getInstance(context).getTelefoneDao();
        this.aluno = aluno;
        this.listener = listener;
        this.telefones = telefone;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDao.edita(aluno);
        telefoneDao.atualizaTelefone(telefones);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.alunoEditado();
    }

    public interface AlunoEditadoListener {
        void alunoEditado();
    }
}
