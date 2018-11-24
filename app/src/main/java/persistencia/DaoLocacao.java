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
    public DaoLocacao(Context context) { super(context); }

    public void insertLocacao(Locacao l) throws SQLiteException {
        String values = String.format(Locale.US, "%d, %d, %f, %d, %d", l.getDataDeLocacao().getTime(), l.getDataDeDevolucao().getTime(), l.getQuilometragem(), l.getIdCliente(), l.getIdCarro());
        execSQL(String.format(Locale.US, "INSERT INTO LOCACAO (DATALOCACAO, DATADEVOLUCAO, QUILOMETRAGEM, IDCLIENTE, IDCARRO) VALUES (%s);", values));
    }

    public void updateLocacao(Locacao l) throws SQLiteException {
        String values = String.format(Locale.US, "DATALOCACAO = %d, DATADEVOLUCAO = %d, QUILOMETRAGEM = %f, IDCLIENTE = %d, IDCARRO = %d", l.getDataDeLocacao().getTime(), l.getDataDeDevolucao().getTime(), l.getQuilometragem(), l.getIdCliente(), l.getIdCarro());
        execSQL(String.format(Locale.US, "UPDATE LOCACAO SET %s WHERE ID = %d;", values, l.getId()));
    }

    public void deleteLocacao(Locacao l) throws SQLiteException {
        execSQL(String.format(Locale.US, "DELETE FROM LOCACAO WHERE ID = %d;", l.getId()));
    }

    public LinkedList<Locacao> selectLocacoes(String where) throws RuntimeException {
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

