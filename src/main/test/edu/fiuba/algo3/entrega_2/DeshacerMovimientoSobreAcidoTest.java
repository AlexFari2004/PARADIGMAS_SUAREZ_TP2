package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.entrega_1.MechPrueba;
import edu.fiuba.algo3.modelo.errores.SinMovimientoParaDeshacerException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.mech.Mech;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;


public class DeshacerMovimientoSobreAcidoTest {

    private static final Posicion SUELO = new Posicion(2, 3);
    private static final Posicion ACIDO = new Posicion(2, 2);

    private Tablero tablero;
    private Mech mech;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
        mech = new MechPrueba(3);
        tablero.colocar(mech, SUELO);
    }

    @Test
    public void test01AlDeshacerElMovimientoSobreAcidoElMechVuelveASuPosicionAnterior() {

        mech.mover(ACIDO, tablero);


        mech.deshacerMovimiento(tablero);


        assertEquals(SUELO, mech.obtenerPosicion());
    }

    @Test
    public void test02AlDeshacerElMovimientoSobreAcidoLaCasillaDeAcidoQuedaLibre() {

        mech.mover(ACIDO, tablero);


        mech.deshacerMovimiento(tablero);


        assertFalse(tablero.obtenerCasilla(ACIDO).estaOcupada());
        assertTrue(tablero.obtenerCasilla(SUELO).estaOcupada());
    }

    @Test
    public void test03UnMechQueDeshizoSuMovimientoSobreAcidoRecibeElDañoNormal() {

        mech.mover(ACIDO, tablero);
        mech.deshacerMovimiento(tablero);


        mech.recibirDanio(1);


        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test04UnMechYaContaminadoQueDeshizoSuMovimientoSigueRecibiendoElDobleDeDaño() {

        mech.contaminar();
        mech.mover(ACIDO, tablero);
        mech.deshacerMovimiento(tablero);


        mech.recibirDanio(1);


        assertEquals(1, mech.obtenerVida());
    }

    @Test
    public void test05NoSePuedeDeshacerSiElMechNoSeMovio() {

        Executable deshacer = () -> mech.deshacerMovimiento(tablero);


        assertThrows(SinMovimientoParaDeshacerException.class, deshacer);
    }
}
