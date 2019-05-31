package com.fulltime.listaalunos.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.fulltime.listaalunos.R;
import com.fulltime.listaalunos.asynctask.BuscaAlunosAsyncTask;
import com.fulltime.listaalunos.asynctask.RemoveAlunoAsyncTask;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.ui.adapter.AlunosAdapter;

public class ListViewAlunos {

    private final AlunosAdapter alunosAdapter;
    private final Context context;

    public ListViewAlunos(Context context) {
        this.context = context;
        this.alunosAdapter = new AlunosAdapter(context);
    }

    public void configuraAdapter(ListView listViewAlunos) {
        listViewAlunos.setAdapter(alunosAdapter);
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context).setTitle(R.string.alert_dialog_title_remove_aluno)
                .setMessage(R.string.alert_dialog_message_remove_aluno).setPositiveButton(R.string.sim,
                (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                            item.getMenuInfo();
                    Aluno aluno = alunosAdapter.getItem(menuInfo.position);
                    removeAluno(aluno);
                }).setNegativeButton(R.string.nao, null).show();
    }

    private void removeAluno(Aluno aluno) {
        new RemoveAlunoAsyncTask(context, alunosAdapter, aluno).execute();
    }

    public void atualizaLista() {
        new BuscaAlunosAsyncTask(context, alunosAdapter).execute();
    }
}
