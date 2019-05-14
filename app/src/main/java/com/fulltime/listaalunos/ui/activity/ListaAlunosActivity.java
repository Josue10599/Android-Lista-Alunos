package com.fulltime.listaalunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fulltime.listaalunos.R;
import com.fulltime.listaalunos.dao.AlunoDAO;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.ui.ListViewAlunos;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String ALUNO = "aluno";
    private ListViewAlunos listViewAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        this.listViewAlunos = new ListViewAlunos(this, new AlunoDAO());
        configuraActionBar();
        configuraLista();
        configuraBotaoAdicionar();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.activity_lista_alunos_menu_remover) {
            this.listViewAlunos.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.titulo_app_bar_lista_alunos);
        }
    }

    private void configuraLista() {
        ListView listViewAlunos = findViewById(R.id.main_lista_alunos);
        this.listViewAlunos.configuraAdapter(listViewAlunos);
        configuraCliqueNoItem(listViewAlunos);
        registerForContextMenu(listViewAlunos);
    }

    private void configuraCliqueNoItem(ListView listViewAlunos) {
        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                telaAdicionaAluno(aluno);
            }
        });
    }

    private void telaAdicionaAluno(Aluno aluno) {
        Intent telaAdicionaAluno = new Intent(ListaAlunosActivity.this,
                AdicionaAlunoActivity.class);
        telaAdicionaAluno.putExtra(ALUNO, aluno);
        startActivity(telaAdicionaAluno);
    }

    private void configuraBotaoAdicionar() {
        FloatingActionButton botaoAdicionar = findViewById(R.id.main_botao_adicionar);
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaAdicionaAluno();
            }
        });
    }

    private void telaAdicionaAluno() {
        Intent telaAdicionaAluno = new Intent(this,
                AdicionaAlunoActivity.class);
        startActivity(telaAdicionaAluno);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.listViewAlunos.atualizaLista();
    }
}
