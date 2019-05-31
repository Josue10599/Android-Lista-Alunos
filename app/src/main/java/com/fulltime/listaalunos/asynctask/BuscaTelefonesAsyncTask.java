package com.fulltime.listaalunos.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Telefone;

import java.util.List;

public class BuscaTelefonesAsyncTask extends AsyncTask<Void, Void, List<Telefone>> {
    private final TelefoneDao telefoneDao;
    private final TelefonesEncontradosListener listener;
    private final long idAluno;

    public BuscaTelefonesAsyncTask(Context context, long id, TelefonesEncontradosListener listener) {
        this.telefoneDao = DataBase.getInstance(context).getTelefoneDao();
        this.listener = listener;
        this.idAluno = id;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDao.getTelefones(idAluno);
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.telefonesEncontrados(telefones);
    }

    public interface TelefonesEncontradosListener {
        void telefonesEncontrados(List<Telefone> telefones);
    }
}
