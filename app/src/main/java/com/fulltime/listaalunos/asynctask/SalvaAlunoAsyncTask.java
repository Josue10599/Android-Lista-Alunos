package com.fulltime.listaalunos.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.model.Telefone;

public class SalvaAlunoAsyncTask extends AsyncTask<Void, Void, Void> {

    private final AlunoDao alunoDao;
    private final TelefoneDao telefoneDao;
    private final AlunoSalvoListener listener;
    private final Aluno aluno;
    private final Telefone[] telefones;

    public SalvaAlunoAsyncTask(Context context, AlunoSalvoListener listener, Aluno aluno, Telefone... telefones) {
        this.alunoDao = DataBase.getInstance(context).getAlunoDao();
        this.telefoneDao = DataBase.getInstance(context).getTelefoneDao();
        this.listener = listener;
        this.aluno = aluno;
        this.telefones = telefones;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        long idAluno = alunoDao.salva(aluno);
        for (Telefone telefone : telefones) {
            telefone.setIdAluno(idAluno);
            telefoneDao.salvarTelefone(telefone);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.alunoSalvo();
    }

    public interface AlunoSalvoListener {
        void alunoSalvo();
    }
}
