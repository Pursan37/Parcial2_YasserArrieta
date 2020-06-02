package com.example.parcial_2_yasserarrieta;

import android.os.Parcel;
import android.os.Parcelable;


public class Persona  {

    public String nombre;
    public String cedula;
    public String nivel;
    public String salario;
    public String estrato;

    public Persona(String cedula, String nombre, String estrato, String salario, String nivel) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.nivel = nivel;
        this.salario = salario;
        this.estrato = estrato;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }


}