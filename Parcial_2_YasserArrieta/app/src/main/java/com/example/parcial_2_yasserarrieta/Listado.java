package com.example.parcial_2_yasserarrieta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class Listado extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Adapter usuariosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_listado );

        recyclerView = findViewById( R.id.ListView);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        BaseHelper db = new BaseHelper( getApplicationContext() );

        usuariosAdapter = new Adapter( db.mostrarDatos() );
        recyclerView.setAdapter( usuariosAdapter );

    }
}
