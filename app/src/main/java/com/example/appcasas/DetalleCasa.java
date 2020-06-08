package com.example.appcasas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCasa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_casa);

        ImageView foto;
        TextView escritura, ciudad, direccion;
        Bundle bundle;
        Intent intent;

        foto = findViewById(R.id.imgFotoDetalle);
        escritura = findViewById(R.id.lblEscrituraDetalle);
        ciudad = findViewById(R.id.lblCiudadDetalle);
        direccion = findViewById(R.id.lblDireccionDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");
        foto.setImageResource(bundle.getInt("foto"));
        escritura.setText(bundle.getString("escritura"));
        ciudad.setText(bundle.getString("ciudad"));
        direccion.setText(bundle.getString("direccion"));
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleCasa.this, MainActivity.class);
        startActivity(intent);
    }
}
