package edu.fiuba.algo3.entrega_1.unidades.ataque;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.unidad.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import edu.fiuba.algo3.modelo.unidad.VekLuciernaga;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PunoTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void testPunoCausa2DeDanioAUnidadAdyacenteYLaEmpuja1Casilla() {
        Puno puno = new Puno();
        MechCombate mech = new MechCombate(puno);
        VekLuciernaga vek = new VekLuciernaga();

        Posicion posMech = new Posicion(3, 2);
        Posicion posVek = new Posicion(3, 3);
        Posicion posEmpujado = new Posicion(3, 4);

        tablero.colocar(mech, posMech);
        tablero.colocar(vek, posVek);

        mech.atacar(posVek, tablero);

        assertEquals(1, vek.obtenerVida(), "El Puño debe causar exactamente 2 puntos de daño.");
        assertFalse(tablero.obtenerCasilla(posVek).estaOcupada(), "La casilla original del Vek debe quedar vacía.");
        assertEquals(vek, tablero.obtenerCasilla(posEmpujado).getUnidad(), "El Vek debió ser empujado 1 casilla.");
        assertEquals(posEmpujado.getFila(), vek.getPosicion().getFila());
        assertEquals(posEmpujado.getColumna(), vek.getPosicion().getColumna());
    }
}