package edu.fiuba.algo3.unitario;


import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.turno.Emergencia;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestEmergencia {

        private Tablero tablero;
        private Posicion posicion;

        @BeforeEach
        void setUp() {
            tablero = new Tablero();
            posicion = new Posicion(1, 1);
        }

        @Test
        void emergerEnCasillaLibreColocaAlVekEnElTablero() {
            VekSaltador vekEmergente = new VekSaltador();
            Emergencia emergencia = new Emergencia(vekEmergente, posicion);

            emergencia.emerger(tablero);

            assertEquals(vekEmergente, tablero.obtenerCasilla(posicion).getUnidad());
            assertEquals(posicion, vekEmergente.obtenerPosicion());
        }

        @Test
        void unidadEnEmergenciaBloqueaAparicionYRecibeUnPuntoDeDanio() {
            MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
            tablero.colocar(mech, posicion);

            VekSaltador vek = new VekSaltador();
            Emergencia emergencia = new Emergencia(vek, posicion);

            int vidaInicial = mech.obtenerVida();
            emergencia.emerger(tablero);
            assertEquals(vidaInicial - 1, mech.obtenerVida());
            assertEquals(mech, tablero.obtenerCasilla(posicion).getUnidad());
            assertNull(vek.obtenerPosicion());
        }

        @Test
        void unidadConUnoDeVidaMuereAlBloquearLaEmergenciaYLiberaLaCasilla() {
            VekSaltador vek = new VekSaltador();
            tablero.colocar(vek, posicion);

            vek.recibirDanio(1);

            VekSaltador vekEmergente = new VekSaltador();
            Emergencia emergencia = new Emergencia(vekEmergente, posicion);

            emergencia.emerger(tablero);

            assertFalse(vek.estaVivo());
            assertFalse(tablero.obtenerCasilla(posicion).estaOcupada());
        }
}
