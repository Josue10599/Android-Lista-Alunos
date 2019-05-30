package com.fulltime.listaalunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fulltime.listaalunos.R;
import com.fulltime.listaalunos.database.DataBase;
import com.fulltime.listaalunos.database.dao.AlunoDao;
import com.fulltime.listaalunos.database.dao.TelefoneDao;
import com.fulltime.listaalunos.model.Aluno;
import com.fulltime.listaalunos.model.Telefone;

import static com.fulltime.listaalunos.model.TipoTelefone.TELEFONE_CELULAR;
import static com.fulltime.listaalunos.model.TipoTelefone.TELEFONE_FIXO;

public class AdicionaAlunoActivity extends AppCompatActivity {

    private static final String ALUNO = "aluno";

    private EditText campoNome;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_aluno);
        configuraComponentes();
        verificaIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_adiciona_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.activity_adiciona_aluno_menu_salvar) {
            AlunoDao dao = DataBase.getInstance(this).getAlunoDao();
            TelefoneDao telefoneDao = DataBase.getInstance(this).getTelefoneDao();
            aluno = getAluno();
            if (aluno.idValido()) {
                dao.edita(aluno);
            } else {
                long idAluno = dao.salva(aluno);
                telefoneDao.salvarTelefone(telefoneFixo(idAluno), telefoneCelular(idAluno));
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void verificaIntent() {
        Intent recuperaDados = getIntent();
        if (recuperaDados.hasExtra(ALUNO)) {
            aluno = (Aluno) recuperaDados.getSerializableExtra(ALUNO);
            preencheAluno(aluno);
        } else {
            aluno = new Aluno();
        }
    }

    private void configuraComponentes() {
        campoEmail = findViewById(R.id.adiciona_aluno_email);
        campoTelefoneFixo = findViewById(R.id.adiciona_aluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.adiciona_aluno_telefone_celular);
        campoNome = findViewById(R.id.adiciona_aluno_nome);
    }

    private void preencheAluno(Aluno aluno) {
        campoNome.setText(aluno.getNome());
//        campoTelefoneFixo.setText(aluno.getTelefoneFixo());
//        campoTelefoneCelular.setText(aluno.getTelefoneCelular());
        campoEmail.setText(aluno.getEmail());
    }

    private Aluno getAluno() {
        aluno.setNome(getNome());
        aluno.setEmail(getEmail());
        return aluno;
    }

    private Telefone telefoneFixo(long idAluno) {
        Telefone telefoneFixo = new Telefone();
        telefoneFixo.setIdAluno(idAluno);
        telefoneFixo.setNumero(getTelefoneFixo());
        telefoneFixo.setTipo(TELEFONE_FIXO);
        return telefoneFixo;
    }

    private Telefone telefoneCelular(long idAluno) {
        Telefone telefoneCelular = new Telefone();
        telefoneCelular.setIdAluno(idAluno);
        telefoneCelular.setNumero(getTelefoneCelular());
        telefoneCelular.setTipo(TELEFONE_CELULAR);
        return telefoneCelular;
    }

    private String getEmail() {
        return campoEmail.getText().toString();
    }

    private String getTelefoneFixo() {
        return campoTelefoneFixo.getText().toString();
    }

    private String getTelefoneCelular() {
        return campoTelefoneCelular.getText().toString();
    }

    private String getNome() {
        return campoNome.getText().toString();
    }
}
