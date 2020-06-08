package com.example.appcasas;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorCasa.OnCasaClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab;
        RecyclerView lstCasas;
        ArrayList<Casa> casas;
        LinearLayoutManager llm;
        AdaptadorCasa adaptador;

        lstCasas = findViewById(R.id.lstCasas);
        casas = Datos.obtener();
        llm = new LinearLayoutManager(this);
        adaptador = new AdaptadorCasa(casas, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstCasas.setLayoutManager(llm);
        lstCasas.setAdapter(adaptador);

        fab = findViewById(R.id.btnAgregar);
    }

    public void agregar(View v){
        Intent intent;
        intent = new Intent(MainActivity.this, AgregarCasa.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCasaClick(Casa c) {
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("escritura", c.getNum_escritura());
        bundle.putString("ciudad", c.getCiudad());
        bundle.putString("direccion", c.getDireccion());
        bundle.putInt("foto", c.getFoto());

        intent = new Intent(MainActivity.this, DetalleCasa.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
        finish();


    }
}
