package com.example.andres.trabalho_pratico;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import entidade.Locacao;
import persistencia.DaoLocacao;

public class CadastroLocacao extends AppCompatActivity {

    private DaoLocacao daoBD;
    private EditText inputDataLocacao;
    private EditText inputDataDevolucao;
    private EditText inputQuilometragem;
    private Locacao locacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_locacao);

        daoBD = new DaoLocacao(getApplicationContext());
        inputDataLocacao = findViewById(R.id.inputDataLocacao);
        inputDataDevolucao = findViewById(R.id.inputDataDevolucao);
        inputQuilometragem = findViewById(R.id.inputQuilometragem);

        locacao = (Locacao) getIntent().getSerializableExtra("locacao");
    }

    public void salvar(View view) {

        Locacao locacao = new Locacao();

        //locacao.setDataDeLocacao(inputDataLocacao.getText().toString());
        //locacao.setDataDeDevolucao(inputDataDevolucao.getText().toString());
        locacao.setQuilometragem(Float.parseFloat(inputQuilometragem.getText().toString()));

        try {
            if (this.locacao == null) {
                daoBD.insertLocacao(locacao);
            } else {
                locacao.setId(this.locacao.getId());
                daoBD.updateLocacao(locacao);
            }
            finish();

        } catch (Exception e) {
            AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
            mensagem.setTitle("Erro");
            mensagem.setMessage(e.toString());
            mensagem.show();
        }
    }
}
