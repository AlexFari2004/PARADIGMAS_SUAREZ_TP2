package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.turno.Emergencia;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class EmergenciaTest {
    @Test
    void laCasillaOcupadaPorUnidadBloqueaAparicionYRecibeUnPuntoDeDanio() {
        Tablero tablero = new Tablero();
        Posicion posicion = new Posicion(2,1);
        VekAvispon vek = new VekAvispon();
        tablero.colocar(vek, posicion);

        VekSaltador vek1 = new VekSaltador();
        Emergencia emergencia = new Emergencia(vek1, posicion);

        int vidaInicial = vek.obtenerVida();
        emergencia.emerger(tablero);
        assertEquals(vidaInicial - 1, vek.obtenerVida());
        assertEquals(vek, tablero.obtenerCasilla(posicion).getUnidad());
        assertEquals(posicion, vek.obtenerPosicion());
    }
}
