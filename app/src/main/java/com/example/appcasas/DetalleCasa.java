package com.example.appcasas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleCasa extends AppCompatActivity {
    private Casa c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_casa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView foto;
        TextView escritura, ciudad, direccion;
        Bundle bundle;
        Intent intent;
        String escri, ciu, dir, ide;
        int fot;
        StorageReference storageReference;

        foto = findViewById(R.id.imgFotoDetalle);
        escritura = findViewById(R.id.lblEscrituraDetalle);
        ciudad = findViewById(R.id.lblCiudadDetalle);
        direccion = findViewById(R.id.lblDireccionDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        ide = bundle.getString( "id");
        escri = bundle.getString("escritura");
        ciu = bundle.getString("ciudad");
        dir = bundle.getString("direccion");

        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(ide).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });
        //foto.setImageResource(fot);
        escritura.setText(escri);
        ciudad.setText(ciu);
        direccion.setText(dir);

        c = new Casa(escri, ciu, dir, 0, ide);
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleCasa.this, MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.eliminar_casa);
        builder.setMessage(R.string.advertencia_eliminar_casa);
        positivo = getString(R.string.si);
        negativo = getString(R.string.no);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                c.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
