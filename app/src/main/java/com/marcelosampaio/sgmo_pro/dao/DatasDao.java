package com.marcelosampaio.sgmo_pro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_pro.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_pro.dataHelper.DataHelper;

import java.util.Date;


public class DatasDao {


    private final SQLiteDatabase banco;

    //==============================================================================================
    public DatasDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    public int contarDatas() {

        long dia = System.currentTimeMillis();
        int contador = 0;
        Cursor cursor = banco.rawQuery("Select * from datas", null);
        cursor.moveToNext();
        contador = cursor.getCount();

        if (contador == 0) {
        inserir(dia);
        }


        return contador;
    }

    //==============================================================================================
    public long dataApp() {
        long dia = 0;
        Cursor cursor = banco.rawQuery("Select * from datas", null);
        cursor.moveToNext();
        try {
            dia = cursor.getLong(0);
        } catch (CursorIndexOutOfBoundsException e) {
            DataHelper dataHelper = new DataHelper();
            Date date = new Date();
            String x = dataHelper.converteDataEmString(date);
            dia = dataHelper.converteStringDataEmLong(x);
        }
        cursor.close();
        return dia;
    }
    //==============================================================================================

    public void inserir(Long dia) {
        ContentValues values = new ContentValues();
        values.put("dia", dia);
        banco.insert("datas", null, values);
    }

    //==============================================================================================
}
