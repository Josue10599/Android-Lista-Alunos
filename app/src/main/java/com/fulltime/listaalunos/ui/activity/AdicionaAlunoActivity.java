package com.fulltime.listaalunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fulltime.listaalunos.R;
import com.fulltime.listaalunos.asynctask.BuscaTelefonesAsyncTask;
import com.fulltime.listaalunos.asynctask.EditaAlunoAsyncTask;
import com.fulltime.listaalunos.asynctask.SalvaAlunoAsyncTask;
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
    private List<Telefone> telefones;

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
            Telefone[] telefones = {getTelefoneFixo(aluno.getId()), getTelefoneCelular(aluno.getId())};
            aluno = getAluno();
            if (aluno.idValido()) {
                new EditaAlunoAsyncTask(this, aluno, this::finish, telefones).execute();
            } else {
                new SalvaAlunoAsyncTask(this, aluno, this::finish, telefones).execute();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void verificaIntent() {
        Intent recuperaDados = getIntent();
        if (recuperaDados.hasExtra(ALUNO)) {
            aluno = (Aluno) recuperaDados.getSerializableExtra(ALUNO);
            preencheAluno(aluno);
            pegaTelefones(aluno);
        } else {
            aluno = new Aluno();
        }
    }

    private void pegaTelefones(Aluno aluno) {
        new BuscaTelefonesAsyncTask(this, aluno.getId(), this::preencheTelefone).execute();
    }

    private void preencheTelefone(List<Telefone> telefoneList) {
        telefones = telefoneList;
        for (Telefone telefone : telefoneList) {
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

    private Telefone getTelefoneFixo(long idAluno) {
        if (telefones == null)
            return criaTelefone(idAluno, getTelefoneFixo(), TELEFONE_FIXO);
        else
            return modificaTelefone(TELEFONE_FIXO, getTelefoneFixo());
    }

    private Telefone getTelefoneCelular(long idAluno) {
        if (telefones == null)
            return criaTelefone(idAluno, getTelefoneCelular(), TELEFONE_CELULAR);
        else
            return modificaTelefone(TELEFONE_CELULAR, getTelefoneCelular());
    }

    private Telefone criaTelefone(long idAluno, String numeroTelefone, TipoTelefone tipoTelefone) {
        return new Telefone(numeroTelefone, tipoTelefone, idAluno);
    }

    private Telefone modificaTelefone(TipoTelefone tipoTelefone, String numeroTelefone) {
        Telefone telefone = pegaTelefone(tipoTelefone);
        if (telefone != null) {
            telefone.setNumero(numeroTelefone);
        }
        return telefone;
    }

    private Telefone pegaTelefone(TipoTelefone tipoTelefone) {
        for (Telefone telefone : telefones)
            if (telefone.getTipo() == tipoTelefone)
                return telefone;
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
