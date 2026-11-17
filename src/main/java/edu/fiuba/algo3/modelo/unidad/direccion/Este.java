package edu.fiuba.algo3.modelo.unidad.direccion;

import edu.fiuba.algo3.modelo.unidad.Direccion;

public class Este extends Direccion {
    @Override
    public boolean equals(Object o) {
        return o instanceof Este;
    }

    @Override
    public int hashCode() {
        return Este.class.hashCode();
    }
}