package persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import entidade.Locacao;

public class DaoLocacao extends Dao {
    DaoLocacao(Context context) { super(context); }

    public void insertFuncionario(Locacao l) throws SQLiteException {
        SQLiteDatabase writtableDB = this.getWritableDatabase();

        try {
            String values = String.format(Locale.US, "%d, %d, %f, %d, %d", l.getDataDeLocacao().getTime(), l.getDataDeDevolucao().getTime(), l.getQuilometragem(), l.getIdCliente(), l.getIdCarro());
            writtableDB.execSQL(String.format(Locale.US, "INSERT INTO LOCACAO (DATALOCACAO, DATADEVOLUCAO, QUILOMETRAGEM, IDCLIENTE, IDCARRO) VALUES (%s);", values));

        } catch(SQLiteException e) { throw e;
        } finally { writtableDB.close(); }
    }

    public LinkedList<Locacao> selectClientes(String where) throws RuntimeException {
        LinkedList<Locacao> locacoes = new LinkedList();
        where = where.equals("") ? where : " where " + where;
        SQLiteDatabase readableBD = this.getReadableDatabase();

        try {
            Cursor res = readableBD.rawQuery("SELECT * FROM LOCACAO" + where, null);
            if (res.getCount() > 0 ) {
                res.moveToFirst();
                do {
                    Locacao l = new Locacao();
                    l.setId(res.getInt(res.getColumnIndexOrThrow("ID")));
                    l.setDataDeLocacao(new Date(res.getInt(res.getColumnIndexOrThrow("DATALOCACAO"))));
                    l.setDataDeDevolucao(new Date(res.getInt(res.getColumnIndexOrThrow("DATADEVOLUCAO"))));
                    l.setQuilometragem(res.getFloat(res.getColumnIndexOrThrow("QUILOMETRAGEM")));
                    l.setIdCliente(res.getInt(res.getColumnIndexOrThrow("IDCLIENTE")));
                    l.setIdCarro(res.getInt(res.getColumnIndexOrThrow("IDCARRO")));
                    locacoes.add(l);
                } while (res.moveToNext());
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            readableBD.close();
        }
        return locacoes;
    }

}

