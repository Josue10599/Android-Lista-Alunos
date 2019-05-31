package com.fulltime.listaalunos.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.ui.adapter.AlunosAdapter;

import java.util.List;

public class BuscaAlunosAsyncTask extends AsyncTask<Void, Void, List<Aluno>> {

    private final AlunoDao alunoDao;
    private AlunosAdapter alunosAdapter;

    public BuscaAlunosAsyncTask(Context context, AlunosAdapter alunosAdapter) {
        this.alunoDao = DataBase.getInstance(context).getAlunoDao();
        this.alunosAdapter = alunosAdapter;
    }


    @Override
    protected List<Aluno> doInBackground(Void... voids) {
        return alunoDao.getAlunos();
    }

    @Override
    protected void onPostExecute(List<Aluno> alunos) {
        alunosAdapter.atualizaAlunos(alunos);
    }
}
