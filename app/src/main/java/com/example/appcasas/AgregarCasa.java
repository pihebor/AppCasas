package com.example.appcasas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCasa extends AppCompatActivity {
    private ArrayList<Integer> fotos;
    private EditText escritura, departamento, ciudad, barrio, direccion;
    private StorageReference storageReference;

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

        storageReference = FirebaseStorage.getInstance().getReference();

    }

    public void guardar(View v){
        if (validar()) {
            String escri, dep, ciu, bar, dir, id;
            int fot;
            Casa casa;
            InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            escri = escritura.getText().toString();
            dep = departamento.getText().toString();
            ciu = ciudad.getText().toString();
            bar = barrio.getText().toString();
            dir = direccion.getText().toString();
            fot = fotoAleatoria();
            id = Datos.getId();
            casa = new Casa(escri, dep, ciu, bar, dir, fot, id);
            casa.guardar();
            subirFoto(id, fot);
            limpiar();
            imp.hideSoftInputFromWindow(escritura.getWindowToken(), 0);
            Snackbar.make(v, getString(R.string.mensaje_guardar), Snackbar.LENGTH_LONG).show();
        }
    }

    public void subirFoto(String id, int foto){
        StorageReference child = storageReference.child(id);
        Uri uri =Uri.parse("android.resource://"+R.class.getPackage().getName()+"/"+foto);
        UploadTask uploadTask = child.putFile(uri);
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

    public boolean validar(){
        String error_escritura;
        String error_departamento;
        String error_ciudad;
        String error_barrio;
        String error_direccion;
        boolean res;

        error_escritura = getResources().getString(R.string.error_escritura);
        error_departamento = getResources().getString(R.string.error_departamento);
        error_ciudad = getResources().getString(R.string.error_ciudad);
        error_barrio = getResources().getString(R.string.error_barrio);
        error_direccion = getResources().getString(R.string.error_direccion);
        res = true;

        if (escritura.getText().toString().isEmpty()|| escritura.getText().toString().equals("")){
            escritura.setError(error_escritura);
            escritura.requestFocus();
            res = false;
        }else if (departamento.getText().toString().isEmpty()|| departamento.getText().toString().equals("")){
            departamento.setError(error_departamento);
            departamento.requestFocus();
            res = false;
        }else if (ciudad.getText().toString().isEmpty()|| ciudad.getText().toString().equals("")){
            ciudad.setError(error_ciudad);
            ciudad.requestFocus();
            res = false;
        }else if (barrio.getText().toString().isEmpty()|| barrio.getText().toString().equals("")){
            barrio.setError(error_barrio);
            barrio.requestFocus();
            res = false;
        }else if (direccion.getText().toString().isEmpty()|| direccion.getText().toString().equals("")){
            direccion.setError(error_direccion);
            direccion.requestFocus();
            res = false;
        }

        return res;
    }
}
