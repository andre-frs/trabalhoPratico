package com.example.andres.trabalho_pratico;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Date;

import entidade.Funcionario;
import persistencia.DaoFuncionario;

public class CadastroFuncionario extends AppCompatActivity {

    private DaoFuncionario daoBD;
    private EditText inputNome;
    private EditText inputRG;
    private EditText inputCPF;
    private EditText inputEndereco;
    private Switch switchSupervisor;
    private Funcionario funcionario;
    private enum Opcao { SALVAR, DEMITIR }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        daoBD = new DaoFuncionario(getApplicationContext());
        inputNome = findViewById(R.id.inputNome);
        inputRG = findViewById(R.id.inputRG);
        inputCPF = findViewById(R.id.inputCPF);
        inputEndereco = findViewById(R.id.inputEndereco);
        switchSupervisor = findViewById(R.id.switchSupervisor);

        funcionario = (Funcionario) getIntent().getSerializableExtra("funcionario");
    }

    private void salvar(Opcao opcao) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(inputNome.getText().toString());
        funcionario.setRg(inputRG.getText().toString());
        funcionario.setCpf(inputCPF.getText().toString());
        funcionario.setEndereco(inputEndereco.getText().toString());
        funcionario.setSupervisor(switchSupervisor.isChecked());

        try {
            if (this.funcionario == null) {
                funcionario.setDataDeAdimissao(new Date());
                daoBD.insertFuncionario(funcionario);
            } else {
                funcionario.setId(this.funcionario.getId());
                if (opcao == Opcao.DEMITIR) funcionario.setDataDeDemissao(new Date());
                daoBD.updateFuncionario(funcionario);
            }
            finish();

        } catch (Exception e) {
            AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
            mensagem.setTitle("Erro");
            mensagem.setMessage(e.toString());
            mensagem.show();
        }
    }

    public void salvar(View view) { salvar(Opcao.SALVAR); }
    public void demitir(View view) { salvar(Opcao.DEMITIR); }

}
