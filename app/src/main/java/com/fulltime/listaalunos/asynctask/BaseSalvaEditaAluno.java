package com.fulltime.listaalunos.asynctask;

import android.os.AsyncTask;

abstract class BaseSalvaEditaAluno extends AsyncTask<Void, Void, Void> {
    private final ProcessoFinalizado listener;

    BaseSalvaEditaAluno(ProcessoFinalizado listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.acaoAposFinalizado();
    }

    public interface ProcessoFinalizado {
        void acaoAposFinalizado();
    }
}
