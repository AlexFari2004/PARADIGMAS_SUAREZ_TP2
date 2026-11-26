package edu.fiuba.algo3.unitario;

import edu.fiuba.algo3.modelo.ataque.AtaqueAnunciado;
import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.ataque.RegistroAtaque;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import edu.fiuba.algo3.modelo.vek.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestVekIA {

    @Test
    void siNoQuedanEdificiosNiMechsNoDebeLanzarExcepcion() {
        Tablero tablero = new Tablero();
        RegistroAtaque registroAtaque = new RegistroAtaque();
        CriterioMenorFila criterio = new CriterioMenorFila();
        VekIA vekIA = new VekIA(criterio);

        Posicion posicionEdificio1 = new Posicion(1, 5);
        Posicion posicionEdificio2 = new Posicion(5, 3);
        Posicion posicionEdificio3 = new Posicion(7, 7);
        tablero.destruirEdificioEn(posicionEdificio1);
        tablero.destruirEdificioEn(posicionEdificio2);
        tablero.destruirEdificioEn(posicionEdificio3);

        VekSaltador vek = new VekSaltador();
        Posicion posicionVek = new Posicion(3, 3);
        tablero.colocar(vek, posicionVek);

        assertDoesNotThrow(() -> vekIA.tomarDecision(vek, tablero, registroAtaque));
        assertEquals(posicionVek, vek.obtenerPosicion());
    }

    @Test
    void siEstaAdyacenteAlEdificioSeQuedaEnSuPosicionYLoAtaca() {
        Tablero tablero = new Tablero();
        RegistroAtaque registroAtaque = new RegistroAtaque();
        CriterioMenorColumna criterio = new CriterioMenorColumna();
        VekIA vekIA = new VekIA(criterio);

        Posicion posicionEdificio = new Posicion(1, 5);
        Posicion posicionVek = new Posicion(1, 4);

        VekSaltador vek = new VekSaltador();
        tablero.colocar(vek, posicionVek);

        vekIA.tomarDecision(vek, tablero, registroAtaque);
        registroAtaque.resolverTodos(tablero);

        assertEquals(posicionVek, vek.obtenerPosicion());
        assertEquals(TipoCasilla.SUELO, tablero.obtenerCasilla(posicionEdificio).getTipo());
    }
}
