package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpujeOrtogonal;
import edu.fiuba.algo3.modelo.mech.*;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.turno.Emergencia;
import edu.fiuba.algo3.modelo.turno.Fase;
import edu.fiuba.algo3.modelo.turno.FaseJugador;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekLuciernaga;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegracionDerrotaTest {
    private void narrar(String mensaje) {
        Logger.log(mensaje);
    }

    private void banner(String titulo) {
        Logger.log("");
        Logger.log("################################################################");
        Logger.log("###  " + titulo);
        Logger.log("################################################################");
        Logger.log("");
    }

    @BeforeEach
    void habilitarLog() {
        Logger.setEnabled(true);
    }

    @Test
    void ElJugadorPierdeElJuegoPorDestruccionDeLaRedElectrica() {
        banner("Partida perdida por el jugador");
        narrar("Inciando el partido o el juego");
        Mech mech1 = new MechCombate(new Punio(2, new EfectoEmpuje()));
        Mech mech2 = new MechDeArtilleria(new Artilleria(1, new EfectoEmpujeOrtogonal()));
        Mech mech3 = new MechCombate(new Punio(2, new EfectoEmpuje()));


        List<Mech> mechs = List.of(mech1, mech2, mech3);
        List<Posicion> posicionesIniciales = List.of(
                new Posicion(1, 1),
                new Posicion(2, 1),
                new Posicion(3, 1)
        );

        List<Emergencia> emergencias = List.of(
                new Emergencia(new VekSaltador(), new Posicion(3, 6)),
                new Emergencia(new VekLuciernaga(), new Posicion(5, 2)),
                new Emergencia(new VekAvispon(), new Posicion(7, 2)),
                new Emergencia(new VekSaltador(), new Posicion(7, 5)),
                new Emergencia(new VekLuciernaga(), new Posicion(5, 7))
        );

        Juego juego = new Juego(mechs, posicionesIniciales, emergencias);

        narrar("Red eléctrica inicial: " + juego.obtenerRedElectrica().obtenerEnergia());
        narrar("--- Turno " + 1 + " ---");
        narrar(juego.obtenerFaseActual().toString());
        juego.avanzarFase();

        // jugada
        juego.avanzarFase();

        juego.avanzarFase();

        juego.avanzarFase();

        narrar("Fin del turno " + 1
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Ganó: " + juego.gano());
        assertFalse(juego.perdio());

        narrar("Red eléctrica inicial: " + juego.obtenerRedElectrica().obtenerEnergia());

        narrar("--- Turno " + 2 + " ---");
        juego.avanzarFase();

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        narrar("Fin del turno " + 2
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Perdio: " + juego.perdio());
        assertFalse(juego.perdio());

        narrar("--- Turno " + 3 + " ---");
        juego.avanzarFase();

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        narrar("Fin del turno " + 3
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Perdio: " + juego.perdio());
        assertFalse(juego.perdio());

        narrar("--- Turno " + 4 + " ---");
        juego.avanzarFase();

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        narrar("Fin del turno " + 4
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Perdio: " + juego.perdio());
        assertFalse(juego.perdio());

        narrar("--- Turno " + 5 + " ---");
        juego.avanzarFase();

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        narrar("Fin del turno " + 5
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Perdio: " + juego.perdio());

        assertTrue(juego.perdio());
        assertFalse(juego.gano());
        assertEquals(0, juego.obtenerRedElectrica().obtenerEnergia());
        assertFalse(juego.obtenerTablero().obtenerMechsVivos().isEmpty());
    }

    @Test
    void ElJugadorPierdeElJuegoPorDestruccionDeLos3Mechs() {
        banner("Partida perdida por el jugador");
        narrar("Inciando el partido o el juego");
        Mech mech1 = new MechCombate(new Punio(2, new EfectoEmpuje()));
        Mech mech2 = new MechDeArtilleria(new Artilleria(1, new EfectoEmpujeOrtogonal()));
        Mech mech3 = new MechCombate(new Punio(2, new EfectoEmpuje()));


        List<Mech> mechs = List.of(mech1, mech2, mech3);
        List<Posicion> posicionesIniciales = List.of(
                new Posicion(1, 1),
                new Posicion(2, 1),
                new Posicion(3, 1)
        );

        List<Emergencia> emergencias = List.of(
                new Emergencia(new VekSaltador(), new Posicion(2, 2)),
                new Emergencia(new VekLuciernaga(), new Posicion(2, 3)),
                new Emergencia(new VekAvispon(), new Posicion(1, 2)),
                new Emergencia(new VekSaltador(), new Posicion(4, 3)),
                new Emergencia(new VekLuciernaga(), new Posicion(3, 2))
        );

        Juego juego = new Juego(mechs, posicionesIniciales, emergencias);

        narrar("Red eléctrica inicial: " + juego.obtenerRedElectrica().obtenerEnergia());
        narrar("--- Turno " + 1 + " ---");

        juego.avanzarFase();

        // jugada
        juego.avanzarFase();

        juego.avanzarFase();

        juego.avanzarFase();

        narrar("Fin del turno " + 1
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Ganó: " + juego.gano());
        assertFalse(juego.perdio());

        narrar("Red eléctrica inicial: " + juego.obtenerRedElectrica().obtenerEnergia());

        narrar("--- Turno " + 2 + " ---");
        juego.avanzarFase();

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        narrar("Fin del turno " + 2
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Perdio: " + juego.perdio());
        assertFalse(juego.perdio());

        narrar("--- Turno " + 3 + " ---");
        juego.avanzarFase();

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        narrar("Fin del turno " + 3
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Perdio: " + juego.perdio());
        assertFalse(juego.perdio());

        narrar("--- Turno " + 4 + " ---");
        juego.avanzarFase();

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        narrar("Fin del turno " + 4
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Perdio: " + juego.perdio());
        assertFalse(juego.perdio());

        narrar("--- Turno " + 5 + " ---");
        juego.avanzarFase();

        juego.avanzarFase();
        juego.avanzarFase();
        juego.avanzarFase();

        narrar("Fin del turno " + 5
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Perdio: " + juego.perdio());

        assertTrue(juego.perdio());
        assertFalse(juego.gano());
        assertTrue(juego.obtenerTablero().obtenerMechsVivos().isEmpty());
        assertTrue(juego.obtenerRedElectrica().obtenerEnergia() > 0);
    }
}

