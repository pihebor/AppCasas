package com.example.appcasas;

import java.util.ArrayList;

public class Datos {
    private static ArrayList<Casa> casas = new ArrayList<>();
    public static void guardar(Casa c){
        casas.add(c);
    }

    public ArrayList<Casa> obtener(){
        return casas;
    }
}
