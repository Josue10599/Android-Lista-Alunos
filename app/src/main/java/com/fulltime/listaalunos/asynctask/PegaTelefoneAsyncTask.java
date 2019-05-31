package com.fulltime.listaalunos.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Telefone;

public class PegaTelefoneAsyncTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDao telefoneDao;
    private final EncontradoPrimeiroTelefoneListener listener;
    private final long idAluno;

    public PegaTelefoneAsyncTask(Context context, EncontradoPrimeiroTelefoneListener listener, long id) {
        telefoneDao = DataBase.getInstance(context).getTelefoneDao();
        this.listener = listener;
        this.idAluno = id;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDao.getTelefone(idAluno);
    }

    @Override
    protected void onPostExecute(Telefone telefone) {
        super.onPostExecute(telefone);
        listener.encontradoTelefone(telefone);
    }

    public interface EncontradoPrimeiroTelefoneListener {
        void encontradoTelefone(Telefone telefone);
    }
}
