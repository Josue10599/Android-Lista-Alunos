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
import com.fulltime.listaalunos.model.TipoTelefone;

import java.util.List;

import static com.fulltime.listaalunos.model.TipoTelefone.TELEFONE_CELULAR;
import static com.fulltime.listaalunos.model.TipoTelefone.TELEFONE_FIXO;

public class AdicionaAlunoActivity extends AppCompatActivity {

    private static final String ALUNO = "aluno";

    private EditText campoNome;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private Aluno aluno;
    private TelefoneDao telefoneDao;
    private AlunoDao alunoDao;
    private List<Telefone> telefones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_aluno);
        telefoneDao = DataBase.getInstance(this).getTelefoneDao();
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
            alunoDao = DataBase.getInstance(this).getAlunoDao();
            telefoneDao = DataBase.getInstance(this).getTelefoneDao();
            aluno = getAluno();
            if (aluno.idValido()) {
                alunoDao.edita(aluno);
                telefoneDao.atualizaTelefone(criaTelefoneFixo(aluno.getId()), criaTelefoneCelular(aluno.getId()));
            } else {
                long idAluno = alunoDao.salva(aluno);
                telefoneDao.salvarTelefone(criaTelefoneFixo(idAluno), criaTelefoneCelular(idAluno));
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
            preencheTelefone(aluno);
        } else {
            aluno = new Aluno();
        }
    }

    private void preencheTelefone(Aluno aluno) {
        telefones = telefoneDao.getTelefones(aluno.getId());
        for (Telefone telefone : telefones) {
            if (telefone.getTipo() == TELEFONE_FIXO) {
                campoTelefoneFixo.setText(telefone.getNumero());
            } else {
                campoTelefoneCelular.setText(telefone.getNumero());
            }
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
        campoEmail.setText(aluno.getEmail());
    }

    private Aluno getAluno() {
        aluno.setNome(getNome());
        aluno.setEmail(getEmail());
        return aluno;
    }

    private Telefone criaTelefoneFixo(long idAluno) {
        Telefone telefoneFixo;
        if (telefones == null) {
            telefoneFixo = new Telefone(getTelefoneFixo(), TELEFONE_FIXO, idAluno);
            return telefoneFixo;
        } else {
            telefoneFixo = pegaTelefone(TELEFONE_FIXO);
            if (telefoneFixo != null) {
                telefoneFixo.setNumero(getTelefoneFixo());
            }
            return telefoneFixo;
        }
    }

    private Telefone criaTelefoneCelular(long idAluno) {
        Telefone telefoneCelular;
        if (telefones == null) {
            telefoneCelular = new Telefone(getTelefoneCelular(), TELEFONE_CELULAR, idAluno);
            return telefoneCelular;
        } else {
            telefoneCelular = pegaTelefone(TELEFONE_CELULAR);
            if (telefoneCelular != null) {
                telefoneCelular.setNumero(getTelefoneCelular());
            }
            return telefoneCelular;
        }
    }

    private Telefone pegaTelefone(TipoTelefone tipoTelefone) {
        for (Telefone telefone : telefones) {
            if (telefone.getTipo() == tipoTelefone) {
                return telefone;
            }
        }
        return null;
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
