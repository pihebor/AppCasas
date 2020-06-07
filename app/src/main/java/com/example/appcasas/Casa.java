package com.example.appcasas;

public class Casa {
    private String num_escritura;
    private String departamento;
    private String ciudad;
    private String barrio;
    private String direccion;
    private int foto;

    public Casa(String num_escritura, String departamento, String ciudad, String barrio, String direccion, int foto){
        this.num_escritura = num_escritura;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.barrio = barrio;
        this.direccion = direccion;
        this.foto = foto;
    }

    public String getNum_escritura() {
        return num_escritura;
    }

    public void setNum_escritura(String num_escritura) {
        this.num_escritura = num_escritura;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void guardar(){
        Datos.guardar(this);
    }
}
