package edu.fiuba.algo3.unitario;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestPosicion {
    @Test
    void posicionConFilaYColumnaValidaDebeEstarEnElTablero() {
        Posicion posicion = new Posicion(0, 0);
        Posicion posicion1 = new Posicion(7, 7);

        assertTrue(posicion.estaEnElTablero(8, 8));
        assertTrue(posicion1.estaEnElTablero(8, 8));

    }

    @Test
    void posicionConFilaYColumnaInvalidaDebeEstarEnElTablero() {
        Posicion posicion = new Posicion(-1, 0);
        Posicion posicion1 = new Posicion(7, 8);

        assertFalse(posicion.estaEnElTablero(8, 8));
        assertFalse(posicion1.estaEnElTablero(8, 8));

    }

    @Test
    void obtenerPosicionesOrtogonalesDebeDevolverLasPosicionesCorrectamente() {
        Posicion posicion = new Posicion(3, 3);
        List<Posicion> ortogonales = posicion.obtenerPosicionesOrtogonales(8, 8);

        assertEquals(4, ortogonales.size());
        assertTrue(ortogonales.containsAll(List.of(
                new Posicion(2, 3),
                new Posicion(4, 3),
                new Posicion(3, 2),
                new Posicion(3, 4)
        )));
    }
    @Test
    void obtenerPosicionesOrtogonalesEnElBordeDebeDevolverLasPosicionesCorrectamente() {
        Posicion posicion = new Posicion(0, 3);
        List<Posicion> ortogonales = posicion.obtenerPosicionesOrtogonales(8, 8);

        assertEquals(3, ortogonales.size());
        assertTrue(ortogonales.containsAll(List.of(
                new Posicion(1, 3),
                new Posicion(0, 2),
                new Posicion(0, 4)
        )));
    }

    @Test
    void posicionesConMismaFilaYMismaColumnaSonIguales() {
        Posicion posicion1 = new Posicion(2, 3);
        Posicion posicion2 = new Posicion(2, 3);

        assertEquals(posicion1, posicion2);

    }
    @Test
    void posicionesConMismasFilasYColumnasTienenMismoHashCode() {
        Posicion posicion1 = new Posicion(2, 2);
        Posicion posicion2 = new Posicion(2, 2);


        assertEquals(posicion1.hashCode(), posicion2.hashCode());

    }
    @Test
    void posicionesConFilaYColumnaInvertidaNoTienenMismoHashCode() {
        Posicion posicion1 = new Posicion(3, 2);
        Posicion posicion2 = new Posicion(2, 3);

        assertNotEquals(posicion1.hashCode(), posicion2.hashCode());

    }
    @Test
    void laDistanciaHaciaLaMismaPosicionEsNulo() {
        Posicion posicion = new Posicion(3, 3);
        Posicion mismaPosicion = new Posicion(3, 3);

        assertEquals(0, posicion.distanciaA(mismaPosicion));
    }

    @Test
    void laDistanciaHaciaDistintaPosicionDebeSerModuloDeNorma1() {
        Posicion posicion = new Posicion(3, 3);
        Posicion posicion1 = new Posicion(3, 6);
        Posicion posicion2 = new Posicion(7, 3);
        Posicion posicion3 = new Posicion(5, 6);

        assertEquals(3, posicion.distanciaA(posicion1));
        assertEquals(4, posicion.distanciaA(posicion2));
        assertEquals(5, posicion.distanciaA(posicion3));
    }


}
