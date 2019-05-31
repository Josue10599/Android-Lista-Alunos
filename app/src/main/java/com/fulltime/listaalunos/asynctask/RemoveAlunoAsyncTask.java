package com.fulltime.listaalunos.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.ui.adapter.AlunosAdapter;

public class RemoveAlunoAsyncTask extends AsyncTask<Void, Void, Void> {

    private final AlunoDao alunoDao;
    private final AlunosAdapter adapter;
    private final Aluno aluno;

    public RemoveAlunoAsyncTask(Context context, AlunosAdapter adapter, Aluno aluno) {
        this.alunoDao = DataBase.getInstance(context).getAlunoDao();
        this.adapter = adapter;
        this.aluno = aluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        this.alunoDao.remove(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        adapter.remove(aluno);
    }
}
