package com.fulltime.listaalunos.ui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fulltime.listaalunos.R;
import com.fulltime.listaalunos.dao.AlunoDAO;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.ui.adapter.AlunosAdapter;

public class ListViewAlunos {

    private final AlunosAdapter alunosAdapter;
    private final Context context;
    private final AlunoDAO db;

    public ListViewAlunos(Context context, AlunoDAO db) {
        this.context = context;
        this.db = db;
        this.alunosAdapter = new AlunosAdapter(context, db.getAlunos());
    }

    public void configuraAdapter(ListView listViewAlunos) {
        listViewAlunos.setAdapter(alunosAdapter);
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context).setTitle(R.string.alert_dialog_title_remove_aluno)
                .setMessage(R.string.alert_dialog_message_remove_aluno).setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                        item.getMenuInfo();
                Aluno aluno = alunosAdapter.getItem(menuInfo.position);
                removeAluno(aluno);
            }
        }).setNegativeButton(R.string.nao, null).show();
    }

    private void removeAluno(Aluno aluno) {
        db.remove(aluno);
        alunosAdapter.remove(aluno);
    }

    public void atualizaLista() {
        alunosAdapter.atualizaAlunos(new AlunoDAO().getAlunos());
    }
}
