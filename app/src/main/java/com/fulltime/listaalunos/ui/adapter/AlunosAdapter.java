package com.fulltime.listaalunos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fulltime.listaalunos.R;
import com.fulltime.listaalunos.asynctask.PegaTelefoneAsyncTask;
import com.fulltime.listaalunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunosAdapter extends BaseAdapter {

    private final Context context;
    private final List<Aluno> alunos;

    public AlunosAdapter(Context context) {
        alunos = new ArrayList<>();
        this.context = context;
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
        final TextView textViewTelefone = view.findViewById(R.id.item_aluno_telefone);
        new PegaTelefoneAsyncTask(context,
                telefone -> {
                    if (telefone != null) textViewTelefone.setText
                            (String.format(context.getString(R.string.item_aluno_telefone), telefone));
                    else textViewTelefone.setText("");
                }, alunos.get(position).getId()).execute();
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
