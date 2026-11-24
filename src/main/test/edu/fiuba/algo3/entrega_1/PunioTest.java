package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PunioTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void testPunoCausa2DeDanioAUnidadAdyacente() {
        Punio punio = new Punio();
        MechCombate mech = new MechCombate(punio);
        VekSaltador vek = new VekSaltador();

        Posicion posMech = new Posicion(3, 2);
        Posicion posVek = new Posicion(3, 3);

        tablero.colocar(mech, posMech);
        tablero.colocar(vek, posVek);

        mech.atacar(posVek, tablero);

        assertEquals(0, vek.obtenerVida());
    }

    @Test
    public void testPunoEmpujaUnaCasillaAUnidadAdyacente() {
        Punio punio = new Punio();
        MechCombate mech = new MechCombate(punio);
        VekSaltador vek = new VekSaltador();

        Posicion posMech = new Posicion(3, 2);
        Posicion posVek = new Posicion(3, 3);
        Posicion posEmpujado = new Posicion(3, 4);

        tablero.colocar(mech, posMech);
        tablero.colocar(vek, posVek);

        mech.atacar(posVek, tablero);

        assertFalse(tablero.obtenerCasilla(posVek).estaOcupada());
        assertEquals(vek, tablero.obtenerCasilla(posEmpujado).getUnidad());
        assertEquals(posEmpujado.getFila(), vek.getPosicion().getFila());
        assertEquals(posEmpujado.getColumna(), vek.getPosicion().getColumna());
    }
}
