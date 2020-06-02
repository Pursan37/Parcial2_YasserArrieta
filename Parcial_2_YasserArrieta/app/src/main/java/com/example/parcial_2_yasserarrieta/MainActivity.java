package com.example.parcial_2_yasserarrieta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{


    EditText edi_cedula,edi_nombre, edi_salario;
    Button btnGuardar,btnListar,btnBuscar,btnBorrar,btnEditar;
    Spinner spinner_selecion_grupo, spinner_selecion_grupo2;
    BaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        edi_nombre  = findViewById( R.id.act_nombre );
        edi_cedula = findViewById( R.id.act_cedula );
        edi_salario = findViewById( R.id.act_salario );

        spinner_selecion_grupo = (Spinner) findViewById( R.id.act_spinner_selecion_grupo );
        spinner_selecion_grupo2 = (Spinner) findViewById( R.id.act_spinner_selecion_grupo2 );

        btnGuardar = findViewById( R.id.btnGuardar );
        btnBorrar = findViewById( R.id.act_btnBorrar );
        btnEditar = findViewById( R.id.act_btnEditar );
        btnListar = findViewById( R.id.btnListar );

        btnBuscar = findViewById(R.id.act_btnBuscar);
        db = new BaseHelper(getApplicationContext());


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persona usuarios = new Persona();
                db.buscar(usuarios,edi_cedula.getText().toString());
                MostrarDialogo(usuarios);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(validar()){
                    db.agregarDatos(edi_cedula.getText().toString(),edi_nombre.getText().toString(),
                            spinner_selecion_grupo.getSelectedItem().toString(), edi_salario.getText().toString(),spinner_selecion_grupo2.getSelectedItem().toString());
                    Toast.makeText(MainActivity.this, "Se agregó correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDato();
            }
        });



        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarDato();
            }
        });



        btnListar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });



    }
    public boolean validar(){
        boolean retorno=true;
        String c1=edi_nombre.getText().toString();
        String c2=edi_cedula.getText().toString();
        String c3=edi_salario.getText().toString();
        if(c1.isEmpty()){
            edi_nombre.setError("ESTE CAMPO NO PUEDE ESTAR VACIO");
            retorno=false;

        }
        if(c2.isEmpty()){
            edi_cedula.setError("ESTE CAMPO NO PUEDE ESTAR VACIO");
            retorno=false;
        }
        if(c3.isEmpty()){
            edi_salario.setError("ESTE CAMPO NO PUEDE ESTAR VACIO");
            retorno=false;

        }
        return retorno;
    }

    private void actualizarDato() {
        boolean actualizar= db.actualizarDatos(edi_cedula.getText().toString(),edi_nombre.getText().toString(),
                spinner_selecion_grupo.getSelectedItem().toString(), edi_salario.getText().toString(),spinner_selecion_grupo2.getSelectedItem().toString());
        if (actualizar == true){

            Toast.makeText(MainActivity.this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarDato() {
        Integer eliminar = db.eliminarDatos(edi_cedula.getText().toString());
        if(eliminar > 0){
            Toast.makeText(MainActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    private void MostrarDialogo(Persona usuarios) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.item_row);

        TextView txtId = dialog.findViewById(R.id.txtcedula);
        TextView txtNombre = dialog.findViewById(R.id.txtnombre);
        TextView txtEstrato = dialog.findViewById(R.id.txtestrato);
        TextView txtSalario = dialog.findViewById(R.id.txtsalario);
        TextView txtNivel = dialog.findViewById( R.id.txtnivel );

        txtId.setText(usuarios.getCedula());
        txtNombre.setText(usuarios.getNombre());
        txtEstrato.setText(usuarios.getEstrato());
        txtSalario.setText(usuarios.getSalario());
        txtNivel.setText(usuarios.getNivel());

        dialog.show();



    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
