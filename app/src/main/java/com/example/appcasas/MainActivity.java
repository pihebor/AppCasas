package com.example.appcasas;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
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
        final ArrayList<Casa> casas;
        LinearLayoutManager llm;
        final AdaptadorCasa adaptador;
        DatabaseReference databaseReference;
        String db = "Casas";

        lstCasas = findViewById(R.id.lstCasas);
        casas = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        adaptador = new AdaptadorCasa(casas, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstCasas.setLayoutManager(llm);
        lstCasas.setAdapter(adaptador);

        fab = findViewById(R.id.btnAgregar);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                casas.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Casa c = snapshot.getValue(Casa.class);
                        casas.add(c);
                    }
                }

                adaptador.notifyDataSetChanged();
                Datos.setCasas(casas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        bundle.putString("id", c.getId());

        intent = new Intent(MainActivity.this, DetalleCasa.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
        finish();


    }
}
