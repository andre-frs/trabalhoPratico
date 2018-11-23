package persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.LinkedList;
import java.util.Locale;

import entidade.Carro;

public class DaoCarro extends Dao{
    DaoCarro(Context context) { super(context); }

    public void insertFuncionario(Carro c) throws SQLiteException {
        SQLiteDatabase writtableDB = this.getWritableDatabase();

        try {
            String values = String.format(Locale.US, "'%s', '%s', '%s', '%s', %f, %f, '%s', %d", c.getPlaca(), c.getNome(), c.getMarca(), c.getModelo(), c.getValorDoSeguro(), c.getValorDaLocacao(), c.getCor(), c.getAtivo() ? 1 : 0);
            writtableDB.execSQL(String.format(Locale.US, "INSERT INTO CARRO (PLACA, NOME, MARCA, MODELO, VALORSEGURO, VALORLOCACAO, COR, ATIVO) VALUES (%s);", values));

        } catch(SQLiteException e) { throw e;
        } finally { writtableDB.close(); }
    }

    public LinkedList<Carro> selectClientes(String where) throws RuntimeException {
        LinkedList<Carro> carros = new LinkedList();
        where = where.equals("") ? where : " where " + where;
        SQLiteDatabase readableBD = this.getReadableDatabase();

        try {
            Cursor res = readableBD.rawQuery("SELECT * FROM CARRO" + where, null);
            if (res.getCount() > 0 ) {
                res.moveToFirst();
                do {

                    Carro c = new Carro();
                    c.setId(res.getInt(res.getColumnIndexOrThrow("ID")));
                    c.setPlaca(res.getString(res.getColumnIndexOrThrow("PLACA")));
                    c.setNome(res.getString(res.getColumnIndexOrThrow("NOME")));
                    c.setMarca(res.getString(res.getColumnIndexOrThrow("MARCA")));
                    c.setModelo(res.getString(res.getColumnIndexOrThrow("MODELO")));
                    c.setValorDoSeguro(res.getFloat(res.getColumnIndexOrThrow("VALORSEGURO")));
                    c.setValorDaLocacao(res.getFloat(res.getColumnIndexOrThrow("VALORLOCACAO")));
                    c.setCor(res.getString(res.getColumnIndexOrThrow("COR")));
                    c.setAtivo(res.getInt(res.getColumnIndexOrThrow("ATIVO")) == 1);
                    carros.add(c);
                } while (res.moveToNext());
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            readableBD.close();
        }
        return carros;
    }
}
