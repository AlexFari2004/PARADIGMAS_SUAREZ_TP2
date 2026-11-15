package edu.fiuba.algo3.modelo.unidad.direccion;

import edu.fiuba.algo3.modelo.unidad.Direccion;

public class Oeste extends Direccion {
    @Override
    public boolean equals(Object o) {
        return o instanceof Oeste;
    }

    @Override
    public int hashCode() {
        return Oeste.class.hashCode();
    }
}