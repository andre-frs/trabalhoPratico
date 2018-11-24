package com.example.andres.trabalho_pratico;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import entidade.Cliente;
import persistencia.DaoCliente;

public class CadastroCliente extends AppCompatActivity {

    private DaoCliente daoBD;
    private EditText inputNome;
    private EditText inputRG;
    private EditText inputCPF;
    private EditText inputEndereco;
    private EditText inputCNH;
    private EditText inputNumeroDependentes;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        daoBD = new DaoCliente(getApplicationContext());
        inputNome = findViewById(R.id.inputNome);
        inputRG = findViewById(R.id.inputRG);
        inputCPF = findViewById(R.id.inputCPF);
        inputEndereco = findViewById(R.id.inputEndereco);
        inputCNH = findViewById(R.id.inputCNH);
        inputNumeroDependentes = findViewById(R.id.inputNumeroDependentes);

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");
    }

    public void salvar(View view) {

        Cliente cliente = new Cliente();
        cliente.setNome(inputNome.getText().toString());
        cliente.setRg(inputRG.getText().toString());
        cliente.setCpf(inputCPF.getText().toString());
        cliente.setEndereco(inputEndereco.getText().toString());
        cliente.setCnh(inputCNH.getText().toString());
        cliente.setNumeroDeDependentes(Integer.parseInt(inputNumeroDependentes.getText().toString()));

        try {
            if (this.cliente == null) {
                daoBD.insertCliente(cliente);
            } else {
                cliente.setId(this.cliente.getId());
                daoBD.updateCliente(cliente);
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
