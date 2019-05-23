package com.fulltime.listaalunos.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.fulltime.listaalunos.R;
import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.ui.adapter.AlunosAdapter;

public class ListViewAlunos {

    private final AlunosAdapter alunosAdapter;
    private final Context context;
    private final AlunoDao db;

    public ListViewAlunos(Context context) {
        this.context = context;
        this.db = DataBase.getInstance(context).getAlunoDao();
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
        alunosAdapter.atualizaAlunos(db.getAlunos());
    }
}
