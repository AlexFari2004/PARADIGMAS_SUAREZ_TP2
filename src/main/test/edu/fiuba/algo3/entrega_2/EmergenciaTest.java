package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Emergencia;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.errors.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.unidad.Vek;
import edu.fiuba.algo3.modelo.unidad.VekSaltador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmergenciaTest {
    @Test
    void unidadEnEmergenciaBloqueaApariciónYRecibeDaño() {
        Tablero tablero = new Tablero();
        Posicion posicion = new Posicion(2, 2);

        Vek vek = new VekSaltador();
        vek.recibirDaño(4);
        tablero.obtenerCasilla(posicion).ocupar(vek);

        Emergencia emergencia =
                new Emergencia(new VekSaltador(), posicion);
        Assertions.assertTrue(vek.estaMuerta());
        assertThrows(
                CasillaNoTransitableException.class,
                () -> emergencia.emerger(tablero)
        );


    }

    @Test
    void emergerEnCasillaOcupadoDebeLanzarExcepcion() {

        Tablero tablero = new Tablero();
        Posicion posicion = new Posicion(2, 2);

        Vek vekExistente = new VekSaltador();

        tablero.obtenerCasilla(posicion).ocupar(vekExistente);

        Emergencia emergencia =
                new Emergencia(new VekSaltador(), posicion);

        assertThrows(
                CasillaNoTransitableException.class,
                () -> emergencia.emerger(tablero)
        );

        //Assertions.assertTrue(vekExistente.estaMuerta());
    }
}
