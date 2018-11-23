package persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Locale;

import entidade.Funcionario;

class Dao extends SQLiteOpenHelper {

    public Dao(Context context) {
        super(context, "BANCO", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String funcionario =
                "CREATE TABLE IF NOT EXISTS FUNCIONARIO ( " +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "NOME VARCHAR(50)," +
                        "RG VARCHAR(11)," +
                        "CPF VARCHAR(14)," +
                        "ENDERECO VARCHAR(50)," +
                        "DATAADIMISSAO INTEGER," +
                        "DATADEMISSAO INTEGER," +
                        "SUPERVISOR BOOLEAN);";
        sqLiteDatabase.execSQL(funcionario);

        String cliente =
                "CREATE TABLE IF NOT EXISTS CLIENTE ( " +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "NOME VARCHAR(50)," +
                        "RG VARCHAR(11)," +
                        "CPF VARCHAR(14)," +
                        "ENDERECO VARCHAR(50)," +
                        "CNH VARCHAR(3)," +
                        "NUMERODEPENDENTES INTEGER);";
        sqLiteDatabase.execSQL(cliente);

        String locacao =
                "CREATE TABLE IF NOT EXISTS LOCACAO ( " +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "DATALOCACAO INTEGER," +
                        "DATADEVOLUCAO INTEGER," +
                        "QUILOMETRAGEM REAL," +
                        "IDCLIENTE INTEGER," +
                        "IDCARRO INTEGER);";
        sqLiteDatabase.execSQL(locacao);

        String carro =
                "CREATE TABLE IF NOT EXISTS CARRO ( " +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "PLACA VARCHAR(30)," +
                        "NOME VARCHAR(15)," +
                        "MARCA VARCHAR(15)," +
                        "MODELO VARCHAR(15)," +
                        "VALORSEGURO REAL," +
                        "VALORLOCACAO REAL," +
                        "COR VARCHAR(10)," +
                        "ATIVO INTEGER);";
        sqLiteDatabase.execSQL(carro);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

}
