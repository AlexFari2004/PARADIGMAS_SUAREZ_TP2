package edu.fiuba.algo3.modelo.unidad.direccion;

import edu.fiuba.algo3.modelo.unidad.Direccion;

public class Sur extends Direccion {
    @Override
    public boolean equals(Object o) {
        return o instanceof Sur;
    }

    @Override
    public int hashCode() {
        return Sur.class.hashCode();
    }
}