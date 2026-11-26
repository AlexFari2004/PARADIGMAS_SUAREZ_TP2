package edu.fiuba.algo3.unitario;


import edu.fiuba.algo3.modelo.errores.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCasilla {
    @Test
    void unaCasillaCreadaEstaLibre() {

        Casilla casilla = new Suelo();
        assertFalse(casilla.estaOcupada());
        assertNull(casilla.getUnidad());

    }
    @Test
    void unaCasillaCreadaPuedeSerOcupada() {
        Casilla casilla = new Suelo();
        VekAvispon vek1 = new VekAvispon();

        assertTrue(casilla.puedeSerOcupada());
        casilla.ocupar(vek1);
        assertFalse(casilla.puedeSerOcupada());
    }

    @Test
    void ocuparCasillaOcupadaDebeLanzarExcepcion() {
        Casilla casilla = new Suelo();
        VekSaltador vek1 = new VekSaltador();
        VekAvispon vek2 = new VekAvispon();
        casilla.ocupar(vek1);

        assertThrows(MovimientoEnPosicionOcupadaException.class, () -> casilla.ocupar(vek2));

    }

    @Test
    void liberarCasillaOcupadaVuelveDisponibleNuevamente() {
        Casilla casilla = new Agua();
        VekAvispon vek = new VekAvispon();
        casilla.ocupar(vek);
        assertEquals(vek, casilla.getUnidad());

        casilla.liberar();

        assertFalse(casilla.estaOcupada());
        assertTrue(casilla.puedeSerOcupada());
        assertNull(casilla.getUnidad());

    }

    @Test
    void recibirDanioEnCasillaLibreNoHaceNada() {
        Posicion posicion = new Posicion(0, 0);

        Casilla casilla = new Suelo();
        assertDoesNotThrow(() -> casilla.recibirDanio(1, new Tablero(), posicion));
    }

    @Test
    void recibirDanioEnCasillaOcupadaLaUnidadDebeRecibirDanio() {
        Posicion posicion = new Posicion(0, 0);
        Casilla casilla = new Agua();
        VekAvispon vek = new VekAvispon();
        casilla.ocupar(vek);

        casilla.recibirDanio(1, new Tablero(), posicion);
        assertEquals(1,casilla.getUnidad().obtenerVida());
        assertTrue(casilla.estaOcupada());
    }

    @Test
    void unaCasillaSueloLibreNoDetieneAtaque() {
        Casilla casilla = new Suelo();

        assertFalse(casilla.estaOcupada());
        assertNull(casilla.getUnidad());
        assertFalse(casilla.detieneAtaque());

    }

    @Test
    void unaCasillaSueloOcupadoNoDetieneAtaque() {
        Casilla casilla = new Suelo();
        VekAvispon vek = new VekAvispon();
        casilla.ocupar(vek);


        assertTrue(casilla.detieneAtaque());

    }

    @Test
    void unaCasillaMotaniaDetieneAtaque() {
        Casilla casilla = new Montania();

        assertFalse(casilla.estaOcupada());
        assertFalse(casilla.puedeSerOcupada());
        assertTrue(casilla.detieneAtaque());

    }

    @Test
    void unaCasillaEdificioDetieneAtaque() {
        Casilla casilla = new Edificio();

        assertFalse(casilla.estaOcupada());
        assertFalse(casilla.puedeSerOcupada());
        assertTrue(casilla.detieneAtaque());

    }
}
