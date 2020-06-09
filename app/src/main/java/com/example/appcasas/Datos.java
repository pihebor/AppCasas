package com.example.appcasas;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {
    private static String db = "Casas";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Casa> casas = new ArrayList<>();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Casa c){

        databaseReference.child(db).child(c.getId()).setValue(c);
    }

    public static ArrayList<Casa> obtener(){
        return casas;
    }

    public static void eliminar(Casa c){
        for (int i=0; i<casas.size(); i++){
            if(casas.get(i).getNum_escritura().equals(c.getNum_escritura())){
                casas.remove(i);
                break;
            }
        }
    }
}
