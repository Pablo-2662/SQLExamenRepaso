package com.example.sqlexamenrepaso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelperAlumnos extends SQLiteOpenHelper {

    String sqlCreacion = "CREATE TABLE Alumno (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, APELLIDOS TEXT, EDAD INTEGER)";
    String sqlBorrado ="DROP TABLE IF EXISTS Alumno";



    public SQLiteHelperAlumnos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreacion);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(sqlBorrado);
        sqLiteDatabase.execSQL(sqlCreacion);

    }
}
