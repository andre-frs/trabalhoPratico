package com.example.andres.trabalho_pratico;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import entidade.Carro;
import persistencia.DaoCarro;

public class CadastroCarro extends AppCompatActivity {

    private DaoCarro daoBD;
    private EditText inputPlaca;
    private EditText inputNome;
    private EditText inputMarca;
    private EditText inputModelo;
    private EditText inputValorSeguro;
    private EditText inputValorLocacao;
    private EditText inputCor;
    private Switch switchAtivo;
    private Carro carro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_carro);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        daoBD = new DaoCarro(getApplicationContext());
        inputPlaca = findViewById(R.id.inputPlaca);
        inputNome = findViewById(R.id.inputNome);
        inputMarca = findViewById(R.id.inputMarca);
        inputModelo = findViewById(R.id.inputModelo);
        inputValorSeguro = findViewById(R.id.InputValorSeguro);
        inputValorLocacao = findViewById(R.id.inputValorLocacao);
        inputCor = findViewById(R.id.inputCor);
        switchAtivo = findViewById(R.id.switchAtivo);

        carro = (Carro) getIntent().getSerializableExtra("carro");
    }

    public void salvar(View view) {

        Carro carro = new Carro();
        carro.setPlaca(inputPlaca.getText().toString());
        carro.setNome(inputNome.getText().toString());
        carro.setMarca(inputMarca.getText().toString());
        carro.setModelo(inputModelo.getText().toString());
        carro.setValorDoSeguro(Float.parseFloat(inputValorSeguro.getText().toString()));
        carro.setValorDaLocacao(Float.parseFloat(inputValorLocacao.getText().toString()));
        carro.setCor(inputCor.getText().toString());
        carro.setAtivo(switchAtivo.isChecked());

        try {
            if (this.carro == null) {
                daoBD.insertCarro(carro);
            } else {
                carro.setId(this.carro.getId());
                daoBD.updateCarro(carro);
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
