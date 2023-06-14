package com.ispc.ispcappvoto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String NOMBRE_BASE_DE_DATOS = "votaciones",
            NOMBRE_TABLA_VOTACIONES = "votaciones";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public DatabaseHelper(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, nombre text, descripcion text, valoracion int)", NOMBRE_TABLA_VOTACIONES));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
