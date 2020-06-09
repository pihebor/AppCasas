package com.example.appcasas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCasa extends AppCompatActivity {
    private ArrayList<Integer> fotos;
    private EditText escritura, departamento, ciudad, barrio, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_casa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        escritura = findViewById(R.id.txtEscritura);
        departamento = findViewById(R.id.txtDepartamento);
        ciudad = findViewById(R.id.txtCiudad);
        barrio = findViewById(R.id.txtBarrio);
        direccion = findViewById(R.id.txtDireccion);

        fotos = new ArrayList<>();
        fotos.add(R.drawable.casa1);
        fotos.add(R.drawable.casa2);
        fotos.add(R.drawable.casa3);
        fotos.add(R.drawable.casa4);
        fotos.add(R.drawable.casa5);

    }

    public void guardar(View v){
        String escri, dep, ciu, bar, dir;
        int fot;
        Casa casa;
        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        escri = escritura.getText().toString();
        dep = departamento.getText().toString();
        ciu = ciudad.getText().toString();
        bar = barrio.getText().toString();
        dir = direccion.getText().toString();
        fot = fotoAleatoria();
        casa = new Casa(escri, dep, ciu, bar, dir, fot);
        casa.guardar();
        limpiar();
        imp.hideSoftInputFromWindow(escritura.getWindowToken(), 0);
        Snackbar.make(v, getString(R.string.mensaje_guardar),Snackbar.LENGTH_LONG).show();
    }

    public void limpiar(View v){
        limpiar();
    }

    public int fotoAleatoria(){
        int foto_seleccionada;
        Random r = new Random();
        foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }

    public void limpiar(){
        escritura.setText("");
        departamento.setText("");
        ciudad.setText("");
        barrio.setText("");
        direccion.setText("");
        escritura.requestFocus();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarCasa.this, MainActivity.class);
        startActivity(i);
    }
}
