package edu.fiuba.algo3.entrega_1.unidades;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class MechTurnoTest {
    private static final Posicion ORIGEN = new Posicion(4, 3);

    private Tablero tablero;
    private Mech mech;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
        mech = new Mech();
        tablero.colocarUnidad(mech, ORIGEN);
    }

    @Test
    public void mechNoPuedeMoverseDosVecesEnMismoTurno () {
        Posicion destinoSiguiente1 = new Posicion(3, 3);
        Posicion destinoSiguiente2 = new Posicion(3, 4);

        mech.moverseA(destinoSiguiente1, tablero);

        assertThrows(
            LimiteDeAccionesAlcanzadaException.class,
            () -> mech.moverseA(destinoSiguiente2, tablero)
        );
        assertEquals(destinoSiguiente1, mech.obtenerPosicion());
    }

    @Test
    public void mechNoPuedeAtacarDosVecesEnMismoTurno () {
        Posicion posicionAtaque = new Posicion(4, 6);

        mech.atacar(posicionAtaque, tablero);

        assertThrows(
            LimiteDeAccionesAlcanzadaException.class,
            () -> mech.atacar(posicionAtaque, tablero)
        );
    }

    @Test
    public void mechPuedeDeshacerMovimientoAntesDeAtacar() {
        Posicion destinoSiguiente = new Posicion(3, 3);
        mech.moverseA(destinoSiguiente, tablero);

        assertDoesNotThrow(() -> mech.deshacerMovimiento(tablero));
        assertEquals(destinoSiguiente, mech.obtenerPosicion());
    }

    @Test
    public void mechNoPuedeDeshacerMovientoDosVecesEnElMismoTurno() {
        Posicion destinoSiguiente = new Posicion(3, 3);
        mech.moverseA(destinoSiguiente, tablero);
        mech.deshacerMovimiento(tablero);

        assertThrows(
            LimiteDeAccionesAlcanzadaException.class,
            () -> mech.deshacerMovimiento(tablero)
        );
        assertEquals(ORIGEN, mech.obtenerPosicion());
    }

    @Test
    public void mechNoPuedeDeshacerMovientoDespuesDeAtacar() {
        Posicion destinoSiguiente = new Posicion(3, 3);
        Posicion posicionAtaque = new Posicion(4, 6);
        mech.moverseA(destinoSiguiente, tablero);
        mech.atacar(posicionAtaque, tablero);

        assertThrows(
            LimiteDeAccionesAlcanzadaException.class,
            () -> mech.deshacerMovimiento(tablero)
        );
        assertEquals(destinoSiguiente, mech.obtenerPosicion());
    }
}
