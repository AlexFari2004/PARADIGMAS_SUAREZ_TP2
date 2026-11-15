package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.unidad.Direccion;

public class Ataque {
    private final int dano;
    private final Direccion direccionEmpuje;

    public Ataque(int dano, Direccion direccionEmpuje) {
        this.dano = dano;
        this.direccionEmpuje = direccionEmpuje;
    }

    public int getDanio() {
        return dano;
    }

    public Direccion getDireccionEmpuje() {
        return direccionEmpuje;
    }
}