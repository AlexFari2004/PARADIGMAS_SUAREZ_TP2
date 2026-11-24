package edu.fiuba.algo3.modelo.estado;

import edu.fiuba.algo3.modelo.Unidad;

public interface EstadoUnidad {

    EstadoUnidad recibirDanio(Unidad unidad, int danio);

    EstadoUnidad contaminar();

    EstadoUnidad destruir();

    boolean estaVivo();
}
