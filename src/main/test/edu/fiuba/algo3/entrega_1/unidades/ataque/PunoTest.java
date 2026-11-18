package edu.fiuba.algo3.entrega_1.unidades.ataque;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.VekSaltador;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import edu.fiuba.algo3.modelo.unidad.mech.MechCombate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PunoTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void testPunoCausa2DeDanioAUnidadAdyacente() {
        Puno puno = new Puno();
        MechCombate mech = new MechCombate(puno);
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
        Puno puno = new Puno();
        MechCombate mech = new MechCombate(puno);
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
