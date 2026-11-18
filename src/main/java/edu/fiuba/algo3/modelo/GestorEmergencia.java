package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.unidad.vek.VekAvispon;

public class GestorEmergencia {
    public Emergencia generarEmergencia() {
        return new Emergencia(new VekAvispon(), new Posicion(2, 2));
    }
}
