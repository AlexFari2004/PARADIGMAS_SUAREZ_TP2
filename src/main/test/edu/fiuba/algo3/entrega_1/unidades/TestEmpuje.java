package edu.fiuba.algo3.entrega_1.unidades;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import edu.fiuba.algo3.modelo.unidad.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidad.VekLuciernaga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmpuje {

    @Test
    public void alEmpujarUnaUnidadContraUnaMontañaNoSeDesplaza() {

        Tablero tablero = new Tablero();
        MechCombate mech = new MechCombate(new Puno());
        VekLuciernaga vek = new VekLuciernaga();

        Posicion posicionMech = new Posicion(4, 5);
        Posicion posicionVek = new Posicion(4, 6);

        tablero.colocar(mech, posicionMech);
        tablero.colocar(vek, posicionVek);

        mech.atacar(posicionVek, tablero);

        assertEquals(posicionVek, vek.getPosicion());
        assertEquals(0, vek.obtenerVida());
    }

    @Test
    public void alEmpujarUnaUnidadContraOtraUnidadNoSeDesplaza() {

        Tablero tablero = new Tablero();
        MechCombate mech = new MechCombate(new Puno());
        VekLuciernaga vekObjetivo = new VekLuciernaga();
        VekLuciernaga otroVek = new VekLuciernaga();

        Posicion posicionMech = new Posicion(4, 4);
        Posicion posicionVek = new Posicion(4, 5);
        Posicion posicionOtroVek = new Posicion(4, 6);

        tablero.colocar(mech, posicionMech);
        tablero.colocar(vekObjetivo, posicionVek);
        tablero.colocar(otroVek, posicionOtroVek);

        mech.atacar(posicionVek, tablero);

        assertEquals(posicionVek, vekObjetivo.getPosicion());
        assertEquals(0, vekObjetivo.obtenerVida());
        assertEquals(posicionOtroVek, otroVek.getPosicion());
        assertEquals(2, otroVek.obtenerVida());
    }
}
