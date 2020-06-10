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
        Snackbar.make(v, getString(R.string.mensaje_guardar),Snackbar.LENGTH_LONG).show();
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
}
