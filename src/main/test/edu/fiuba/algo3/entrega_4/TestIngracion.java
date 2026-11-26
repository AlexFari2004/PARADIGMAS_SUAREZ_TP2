package edu.fiuba.algo3.entrega_4;


import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpujeOrtogonal;
import edu.fiuba.algo3.modelo.mech.*;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.turno.Emergencia;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekLuciernaga;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIngracion {
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
    void ElJugadorGanaElJuegoResistiendoLos5Turnos() {
        banner("Juego ganado por el jugador");
        narrar("Inciando el juego");
        Mech mech1 = new MechCombate(new Punio(2,new EfectoEmpuje()));
        Mech mech2 = new MechDeArtilleria(new Artilleria(1,new EfectoEmpujeOrtogonal()));
        Mech mech3 = new MechCombate(new Punio(2,new EfectoEmpuje()));


        List<Mech> mechs = List.of(mech1, mech2, mech3);
        List<Posicion> posicionesIniciales = List.of(
                new Posicion(1, 1),
                new Posicion(3, 1),
                new Posicion(6, 1)
        );

        List<Emergencia> emergencias = List.of(
                new Emergencia(new VekSaltador(), new Posicion(0, 4)),
                new Emergencia(new VekLuciernaga(), new Posicion(0, 6)),
                new Emergencia(new VekAvispon(), new Posicion(2, 6)),
                new Emergencia(new VekSaltador(), new Posicion(4, 4)),
                new Emergencia(new VekLuciernaga(), new Posicion(6, 4))
        );

        Juego juego = new Juego(mechs, posicionesIniciales, emergencias);

        narrar("Red eléctrica inicial: " + juego.obtenerRedElectrica().obtenerEnergia());

        narrar("--- Turno " + 1 + " ---");

        juego.avanzarFase();

        //FaseJugador faseJugador = juego.obtenerFaseActual();

        //faseJugador.mover(mech1, new Posicion(4,4));
        // jugada
        juego.avanzarFase();

        juego.avanzarFase();

        juego.avanzarFase();

        narrar("Fin del turno " + 1
                + " | Energía restante: " + juego.obtenerRedElectrica().obtenerEnergia()
                + " | Ganó: " + juego.gano());

        assertTrue(juego.obtenerRedElectrica().obtenerEnergia() > 0,
                "La Red Eléctrica debe conservar energía al ganar.");
    }

}
