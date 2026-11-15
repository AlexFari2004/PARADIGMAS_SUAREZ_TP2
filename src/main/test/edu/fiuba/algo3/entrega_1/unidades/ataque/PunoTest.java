package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.mock.TableroStub;
import edu.fiuba.algo3.modelo.unidad.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import edu.fiuba.algo3.modelo.unidad.vek.Saltador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PunoTest {

    private TableroStub tablero;

    @BeforeEach
    void setUp() {
        tablero = new TableroStub();
    }

    @Test
    public void test01PunoGeneraAtaqueDe2DeDanioHaciaLaDerecha() {
        Puno puno = new Puno();
        Posicion origen = new Posicion(3, 3);
        Posicion destino = new Posicion(4, 3); // Adyacente derecha

        Ataque ataque = puno.generarAtaques(origen, destino, tablero);

        assertEquals(2, ataque.getDanio());
        assertEquals(new Este(), ataque.getDireccionEmpuje()); // O el tipo de dirección polimórfica que uses
    }

    @Test
    public void test02PunoCausa2DeDanioAUnidadAdyacenteYLaEmpuja1Casilla() {
        Puno puno = new Puno();
        MechCombate mech = new MechCombate(puno);
        Saltador vek = new Saltador(); // Nace con 2 de vida por defecto

        Posicion posMech = new Posicion(3, 3);
        Posicion posVek = new Posicion(4, 3);
        Posicion posEmpujado = new Posicion(5, 3);

        tablero.obtenerCasilla(posMech).colocarUnidad(mech);
        mech.setPosicion(posMech);

        tablero.obtenerCasilla(posVek).colocarUnidad(vek);
        vek.setPosicion(posVek);

        mech.atacar(posVek, tablero);

        // Assert
        assertEquals(0, vek.obtenerVida(), "El Puño debe causar exactamente 2 puntos de daño.");
        assertFalse(tablero.obtenerCasilla(posVek).estaOcupado(), "La casilla original del Vek debe quedar vacía.");
        assertEquals(vek, tablero.obtenerCasilla(posEmpujado).getUnidad(), "El Vek debió ser empujado 1 casilla.");
    }
}