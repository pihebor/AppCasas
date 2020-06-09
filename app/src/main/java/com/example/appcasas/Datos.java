package com.example.appcasas;

import java.util.ArrayList;

public class Datos {
    private static ArrayList<Casa> casas = new ArrayList<>();
    public static void guardar(Casa c){
        casas.add(c);
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
