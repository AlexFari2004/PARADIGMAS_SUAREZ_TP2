package edu.fiuba.algo3.modelo.unidad.direccion;

import edu.fiuba.algo3.modelo.unidad.Direccion;

public class Norte extends Direccion {
    @Override
    public boolean equals(Object o) {
        return o instanceof Norte;
    }

    @Override
    public int hashCode() {
        return Norte.class.hashCode();
    }
}