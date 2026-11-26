package edu.fiuba.algo3.entrega_4;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpujeOrtogonal;
import edu.fiuba.algo3.modelo.mech.Artilleria;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.mech.MechCanion;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.MechDeArtilleria;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.mech.Canion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.turno.Emergencia;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekLuciernaga;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.Test;


public class TestIntegracionVictoria {
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
    public void habilitarLog() {
        Logger.setEnabled(true);
    }

    @Test
    public void ElJugadorGanaElJuegoResistiendoLos5Turnos() {
        banner("Juego ganado por el jugador");
        narrar("Inciando el juego");
        Mech mechCombate = new MechCombate(new Punio(2,new EfectoEmpuje()));
        Mech mechArtilleria = new MechDeArtilleria(new Artilleria(1,new EfectoEmpujeOrtogonal()));
        Mech mechCanion = new MechCanion(new Canion(1, new EfectoEmpuje()));

        List<Mech> mechs = List.of(mechCanion, mechArtilleria, mechCombate);
        List<Posicion> posicionesIniciales = List.of(
                new Posicion(1, 2),
                new Posicion(4, 1),
                new Posicion(2, 6)
        );

        List<Emergencia> emergencias = List.of(
                new Emergencia(new VekSaltador(), new Posicion(1, 4)),
                new Emergencia(new VekLuciernaga(), new Posicion(3, 7)),
                new Emergencia(new VekAvispon(), new Posicion(4, 4)),
                new Emergencia(new VekSaltador(), new Posicion(1, 3))
        );
        
        Juego juego = new Juego(mechs, posicionesIniciales, emergencias);
        Tablero tablero = juego.obtenerTablero();

        // TURNO 1: mover mech a casilla contaminada y deshacer

        // Fase veks
        juego.avanzarFase();

        // Fase jugador
        Posicion destinoMovimiento1MechCanionTurno1 = new Posicion(2, 2);
        
        mechCanion.mover(destinoMovimiento1MechCanionTurno1, tablero);
        mechCombate.deshacerMovimiento(tablero);

        Posicion destinoMovimiento2MechCanionTurno1 = new Posicion(0, 4);
        mechCanion.mover(destinoMovimiento2MechCanionTurno1, tablero);

        juego.avanzarFase();

        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        juego.avanzarFase(); //Emerge vekSaltador

        // TURNO 2: lanzar vek saltador al agua

        // Fase veks
        juego.avanzarFase();

        // Fase jugador
        Posicion destinoAtaqueMechCanionTurno2 = new Posicion(1, 4);
        mechCanion.atacar(destinoAtaqueMechCanionTurno2, tablero);

        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        juego.avanzarFase(); //Emerge VekLuciernaga

        // TURNO 3: VekLuciernaga escupe acido en mech y lo contamina
        // Fase veks
        juego.avanzarFase();

        // Fase jugador
        Posicion destinoMovimientoMechCombateTurno3 = new Posicion(1, 6);
        Posicion destinoAtaqueMechCombateTurno3 = new Posicion(1, 7);
        mechCombate.mover(destinoMovimientoMechCombateTurno3, tablero);
        mechCombate.atacar(destinoAtaqueMechCombateTurno3, tablero);

        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        juego.avanzarFase(); //Emerge VekAvispon

        // TURNO 4:
        // Fase veks
        juego.avanzarFase();

        // Fase jugador
        Posicion destinoAtaqueMechArtilleriaTurno4 = new Posicion(4, 3);
        mechArtilleria.atacar(destinoAtaqueMechArtilleriaTurno4, tablero);
        mechCombate.atacar(destinoAtaqueMechCombateTurno3, tablero); // Misma posicion ataque turno 3 (1,7)

        Posicion destinoMovimientoMechCanionTurno4 = new Posicion(1, 3);
        mechCanion.mover(destinoMovimientoMechCanionTurno4, tablero);


        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        juego.avanzarFase(); //intenta emerger VekSaltador pero canion lo impide

        // TURNO 5:
        // Fase veks
        juego.avanzarFase();

        //Fase jugador
        Posicion destinoMovimientoMechArtilleriaTurno5 = new Posicion(5, 1);
        mechArtilleria.mover(destinoMovimientoMechArtilleriaTurno5, tablero);

        Posicion destinoAtaqueMechArtilleriaTurno5 = new Posicion(5, 3);
        mechArtilleria.atacar(destinoAtaqueMechArtilleriaTurno5, tablero);

        Posicion destinoAtaqueMechCanionTurno5 = new Posicion(1, 4);
        mechCanion.atacar(destinoAtaqueMechCanionTurno5, tablero);

        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        juego.avanzarFase();
    }
}
