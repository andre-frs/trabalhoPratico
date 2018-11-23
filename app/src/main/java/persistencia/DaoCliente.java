package persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.LinkedList;
import java.util.Locale;

import entidade.Cliente;

public class DaoCliente extends Dao {

    DaoCliente(Context context) { super(context); }

    public void insertFuncionario(Cliente c) throws SQLiteException {
        SQLiteDatabase writtableDB = this.getWritableDatabase();

        try {
            String values = String.format(Locale.US, "'%s', '%s', '%s', '%s', '%s', %d", c.getNome(), c.getRg(), c.getCpf(), c.getEndereco(), c.getCnh(), c.getNumeroDeDependentes());
            writtableDB.execSQL(String.format(Locale.US, "INSERT INTO CLIENTE (NOME, RG, CPF, ENDERECCO, CNH, NUMERODEPENDENTES) VALUES (%s);", values));

        } catch(SQLiteException e) { throw e;
        } finally { writtableDB.close(); }
    }

    public LinkedList<Cliente> selectClientes(String where) throws RuntimeException {
        LinkedList<Cliente> clientes = new LinkedList();
        where = where.equals("") ? where : " where " + where;
        SQLiteDatabase readableBD = this.getReadableDatabase();

        try {
            Cursor res = readableBD.rawQuery("SELECT * FROM CLIENTE" + where, null);
            if (res.getCount() > 0 ) {
                res.moveToFirst();
                do {
                    Cliente c = new Cliente();
                    c.setId(res.getInt(res.getColumnIndexOrThrow("ID")));
                    c.setNome(res.getString(res.getColumnIndexOrThrow("NOME")));
                    c.setRg(res.getString(res.getColumnIndexOrThrow("RG")));
                    c.setCpf(res.getString(res.getColumnIndexOrThrow("CPF")));
                    c.setEndereco(res.getString(res.getColumnIndexOrThrow("ENDERECO")));
                    c.setCnh(res.getString(res.getColumnIndexOrThrow("CNH")));
                    c.setNumeroDeDependentes(res.getInt(res.getColumnIndexOrThrow("NUMERODEPENDENTES")));
                    clientes.add(c);
                } while (res.moveToNext());
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            readableBD.close();
        }
        return clientes;
    }
}
