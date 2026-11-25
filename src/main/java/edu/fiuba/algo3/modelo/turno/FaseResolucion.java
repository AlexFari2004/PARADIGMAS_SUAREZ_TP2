package edu.fiuba.algo3.modelo.turno;


import edu.fiuba.algo3.modelo.ataque.RegistroAtaque;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class FaseResolucion extends Fase {
    private final Tablero tablero;
    private final RegistroAtaque registrado;

    public FaseResolucion(Tablero tablero, RegistroAtaque registrado) {

        this.tablero = tablero;
        this.registrado = registrado;
    }

    @Override
    public void ejecutar() {
        registrado.resolverTodos(tablero);
        finalizada = true;
    }

    @Override
    public Fase avanzar(FabricaFase fabrica, ControladorTurno controlador) {
        return fabrica.crearFaseEmergencia();
    }
}