package com.example.parcial_2_yasserarrieta;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class BaseHelper extends SQLiteOpenHelper {

    private static final String DB = "DEMO.db";
    private static final int VERSION = 2;
    public static final String COL_1 = "IDENTIFICACION";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "ESTRATO";
    public static final String COL_4 = "SALARIO";
    public static final String COL_5 = "NIVEL";
    private static final String TABLA_PARCIAL = "CREATE TABLE PARCIAL (IDENTIFICACION TEXT PRIMARY KEY, NOMBRE TEXT, ESTRATO TEXT, SALARIO TEXT, NIVEL TEXT)";



    public BaseHelper(Context context){
        super(context, DB, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLA_PARCIAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLA_PARCIAL);
        db.execSQL(TABLA_PARCIAL);

    }


    public void agregarDatos(String identificacion,String nombre, String estrato, String salario, String nivel){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            db.execSQL("INSERT INTO PARCIAL VALUES('"+identificacion+"','"+nombre+"','"+estrato+"','"+salario+"','"+nivel+"')");
            db.close();
        }
    }

    public List<Persona> mostrarDatos(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PARCIAL", null);
        List<Persona> usuarios = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                usuarios.add(new Persona(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return usuarios;
    }

    public ArrayList<Persona> ListaPersonas(){
        ArrayList<Persona> datos= new ArrayList<Persona>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PARCIAL", null);
        if(c.moveToFirst()){
            do{
                datos.add(new Persona(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)));
            }while(c.moveToNext());

        }
        return datos;
    }

    public void buscar(Persona usuario,String identificacion) {

        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM PARCIAL WHERE IDENTIFICACION='"+identificacion+"'",null);
        if (c.moveToFirst()) {
            do {
                usuario.setCedula(c.getString(0));
                usuario.setNombre(c.getString(1));
                usuario.setEstrato(c.getString(2));
                usuario.setSalario(c.getString(3));
                usuario.setNivel( c.getString( 4 ) );
            } while (c.moveToNext());

        }
    }

    public boolean actualizarDatos(String id,String nombre,String estrato, String salario,String nivel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,estrato);
        contentValues.put(COL_4,salario);
        contentValues.put(COL_5,nivel);
        db.update("PARCIAL",contentValues,"IDENTIFICACION = ?", new String[]{id});
        return true;
    }

    public Integer eliminarDatos(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PARCIAL", "IDENTIFICACION = ?", new String[]{id});
    }



}
