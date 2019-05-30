package com.fulltime.listaalunos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fulltime.listaalunos.R;
import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Aluno;

import java.util.List;

public class AlunosAdapter extends BaseAdapter {

    private final Context context;
    private final List<Aluno> alunos;
    private final TelefoneDao telefoneDao;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
        telefoneDao = DataBase.getInstance(context).getTelefoneDao();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = criaView(parent);
        }
        getNome(position, convertView);
        getTelefone(position, convertView);
        return convertView;
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false);
    }

    private void getTelefone(int position, View view) {
        TextView textViewTelefone = view.findViewById(R.id.item_aluno_telefone);
        textViewTelefone.setText(String.format(context.getString(R.string.item_aluno_telefone),
                telefoneDao.getTelefone(alunos.get(position).getId())));
    }

    private void getNome(int position, View view) {
        TextView textViewAluno = view.findViewById(R.id.item_aluno_nome);
        textViewAluno.setText(String.format(context.getString(R.string.item_aluno_nome),
                alunos.get(position).getNome()));
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void atualizaAlunos(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }
}
