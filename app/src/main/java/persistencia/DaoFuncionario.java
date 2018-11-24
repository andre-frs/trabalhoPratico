package persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import entidade.Funcionario;

public class DaoFuncionario extends Dao {

    public DaoFuncionario(Context context) { super(context); }

    public void insertFuncionario(Funcionario f) throws SQLiteException {
        String values = String.format(Locale.US, "'%s', '%s', '%s', '%s', %d, %d, %d", f.getNome(), f.getRg(), f.getCpf(), f.getEndereco(), f.getDataDeAdimissao().getTime(), f.getDataDeDemissao().getTime(), f.getSupervisor() ? 1 : 0);
        execSQL(String.format(Locale.US, "INSERT INTO FUNCIONARIO (NOME, RG, CPF, ENDERECCO, DATAADIMISSAO, DATADEMISSAO, SUPERVISOR) VALUES (%s);", values));
    }

    public void updateFuncionario(Funcionario f) throws SQLiteException {
        String values = String.format(Locale.US, "NOME = '%s', RG = '%s', CPF = '%s', ENDERECCO = '%s', DATAADIMISSAO = %d, DATADEMISSAO = %d, SUPERVISOR = %d", f.getNome(), f.getRg(), f.getCpf(), f.getEndereco(), f.getDataDeDemissao().getTime(), f.getDataDeDemissao().getTime(), f.getSupervisor() ? 1 : 0);
        execSQL(String.format(Locale.US, "UPDATE FUNCIONARIO SET %s WHERE ID = %d;", values, f.getId()));
    }

    public void deleteFuncionario(Funcionario f) throws SQLiteException {
        execSQL(String.format(Locale.US, "DELETE FROM FUNCIONARIO WHERE ID = %d;", f.getId()));
    }

    public LinkedList<Funcionario> selectFuncionarios(String where) throws RuntimeException {
        LinkedList<Funcionario> funcionarios = new LinkedList();
        where = where.equals("") ? where : " where " + where;

        SQLiteDatabase readableBD = this.getReadableDatabase();
        try {
            Cursor res = readableBD.rawQuery("SELECT * FROM FUNCIONARIO" + where, null);
            if (res.getCount() > 0 ) {
                res.moveToFirst();
                do {
                    Funcionario f = new Funcionario();
                    f.setId(res.getInt(res.getColumnIndexOrThrow("ID")));
                    f.setNome(res.getString(res.getColumnIndexOrThrow("NOME")));
                    f.setRg(res.getString(res.getColumnIndexOrThrow("RG")));
                    f.setCpf(res.getString(res.getColumnIndexOrThrow("CPF")));
                    f.setEndereco(res.getString(res.getColumnIndexOrThrow("ENDERECO")));
                    f.setDataDeAdimissao(new Date(res.getInt(res.getColumnIndexOrThrow("DATAADIMISSAO"))));
                    f.setDataDeDemissao(new Date(res.getInt(res.getColumnIndexOrThrow("DATADEMISSAO"))));
                    f.setSupervisor(res.getInt(res.getColumnIndexOrThrow("SUPERVISOR")) == 1);
                    funcionarios.add(f);
                } while (res.moveToNext());
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            readableBD.close();
        }
        return funcionarios;
    }
}
