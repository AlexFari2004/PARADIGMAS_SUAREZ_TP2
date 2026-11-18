package edu.fiuba.algo3.entrega_1.unidades;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.errors.AccionNoPermitidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MechTurnoTest {
    private static final Posicion ORIGEN = new Posicion(4, 3);

    private Tablero tablero;
    private Mech mech;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
        mech = new MechPrueba(3);
        tablero.colocar(mech, ORIGEN);
    }

    @Test
    public void mechNoPuedeMoverseDosVecesEnMismoTurno() {
        Posicion destinoSiguiente1 = new Posicion(3, 3);
        Posicion destinoSiguiente2 = new Posicion(3, 4);

        mech.mover(destinoSiguiente1, tablero);

        assertThrows(
                AccionNoPermitidaException.class,
                () -> mech.mover(destinoSiguiente2, tablero)
        );
        assertEquals(destinoSiguiente1, mech.getPosicion());
    }

    @Test
    public void mechNoPuedeAtacarDosVecesEnMismoTurno() {
        Posicion posicionAtaque = new Posicion(4, 6);

        mech.atacar(posicionAtaque, tablero);

        assertThrows(
                AccionNoPermitidaException.class,
                () -> mech.atacar(posicionAtaque, tablero)
        );
    }

    @Test
    public void mechPuedeDeshacerMovimientoAntesDeAtacar() {
        Posicion destinoSiguiente = new Posicion(3, 3);
        mech.mover(destinoSiguiente, tablero);

        assertDoesNotThrow(() -> mech.deshacerMovimiento(tablero));
        assertEquals(ORIGEN, mech.getPosicion());
    }

    @Test
    public void mechNoPuedeDeshacerMovientoDosVecesEnElMismoTurno() {
        Posicion destinoSiguiente = new Posicion(3, 3);
        mech.mover(destinoSiguiente, tablero);
        mech.deshacerMovimiento(tablero);

        assertThrows(
                AccionNoPermitidaException.class,
                () -> mech.deshacerMovimiento(tablero)
        );
        assertEquals(ORIGEN, mech.getPosicion());
    }

    @Test
    public void mechNoPuedeDeshacerMovientoDespuesDeAtacar() {
        Posicion destinoSiguiente = new Posicion(3, 3);
        Posicion posicionAtaque = new Posicion(4, 6);
        mech.mover(destinoSiguiente, tablero);
        mech.atacar(posicionAtaque, tablero);

        assertThrows(
                AccionNoPermitidaException.class,
                () -> mech.deshacerMovimiento(tablero)
        );
        assertEquals(destinoSiguiente, mech.getPosicion());
    }
}
