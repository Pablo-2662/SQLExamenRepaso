package com.example.sqlexamenrepaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    EditText editText_nombre, editText_apellidos, editText_edad;
    Button botonInsertar, botonActualizar, botonBorrar, botonConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SQLiteHelperAlumnos helperAlumnos = new SQLiteHelperAlumnos(this,"bbdd_alumnos",null,1);
        database = helperAlumnos.getWritableDatabase();

        editText_nombre = (EditText) findViewById(R.id.edit_nombre);
        editText_apellidos = (EditText) findViewById(R.id.edit_apellidos);
        editText_edad = (EditText) findViewById(R.id.edit_edad);
        botonInsertar = (Button) findViewById(R.id.botonInsertar);
        botonActualizar = (Button) findViewById(R.id.botonActualizar);
        botonBorrar = (Button) findViewById(R.id.botonBorrar);
        botonConsultar = (Button) findViewById(R.id.botonConsultar);
        TextView resultados = (TextView) findViewById(R.id.textoResultado);



        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editText_nombre.getText().toString();
                String apellidos = editText_apellidos.getText().toString();
                int edad = Integer.parseInt(editText_edad.getText().toString());

                /*ContentValues contentValues = new ContentValues();
                contentValues.put("nombre", nombre);
                contentValues.put("apellidos",apellidos);
                contentValues.put("edad",edad);
                database.insert("Alumno",null,contentValues);*/


                String inserccion = "INSERT INTO Alumno (nombre, apellidos, edad) VALUES ('"+nombre+"','"+apellidos+"',"+edad+")";
                database.execSQL(inserccion);
                Snackbar snackbar = Snackbar.make(v,"Alumno añadido",Snackbar.LENGTH_SHORT);
                snackbar.show();
                resultados.setText("NOMBRE: "+nombre+"\nAPELLIDOS: "+apellidos+"\nEDAD: "+edad);
            }
        });


        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editText_nombre.getText().toString();
                String apellidos = editText_apellidos.getText().toString();
                int edad = Integer.parseInt(editText_edad.getText().toString());

                /*ContentValues contentValues = new ContentValues();
                contentValues.put("nombre", nombre);
                contentValues.put("apellidos",apellidos);
                contentValues.put("edad",edad);
                database.update("Alumno",contentValues,"nombre=?",new String[]{nombre});*/

                String update = "UPDATE Alumno SET nombre='"+nombre+"' WHERE ID=1";
                database.execSQL(update);

                Snackbar snackbar = Snackbar.make(v,"Alumno actualizado",Snackbar.LENGTH_SHORT);
                snackbar.show();


            }
        });


        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editText_nombre.getText().toString();
                int registrosBorrados = database.delete("Alumno", "nombre=?",new String[]{nombre});
                if(registrosBorrados>0){
                    Snackbar snackbar = Snackbar.make(v,"Alumno eliminado",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else{
                    Snackbar snackbar = Snackbar.make(v,"No se ha podido eliminar",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editText_nombre.getText().toString();
                Cursor cursor = database.rawQuery("SELECT * FROM Alumno WHERE NOMBRE=?",new String[]{nombre});

                if (cursor != null && cursor.moveToFirst()) {
                    int id = cursor.getInt(0);
                    String apellido = cursor.getString(2);
                    int edad = cursor.getInt(3);

                    resultados.setText("ID: " + id + " APELLIDOS: " + apellido + " EDAD: " + edad);
                } else {
                    resultados.setText("No se encontraron resultados para el nombre proporcionado");
                }

                if (cursor != null) {
                    cursor.close();
                }
            }
        });






    }
}