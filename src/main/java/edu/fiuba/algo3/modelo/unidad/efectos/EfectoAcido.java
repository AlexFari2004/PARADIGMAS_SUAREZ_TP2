package edu.fiuba.algo3.modelo.unidad.efectos;

import edu.fiuba.algo3.modelo.unidad.Unidad;

public class EfectoAcido implements EfectoAtaque {
    public void aplicar(Unidad objetivo) {
        objetivo.contaminar();
    }
}
