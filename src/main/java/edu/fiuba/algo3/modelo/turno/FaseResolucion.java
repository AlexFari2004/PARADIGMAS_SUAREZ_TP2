package edu.fiuba.algo3.modelo.turno;


import edu.fiuba.algo3.modelo.ataque.AtaqueRegistrado;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.Vek;

public class FaseResolucion extends Fase {
    private final Tablero tablero;
    private final AtaqueRegistrado registrado;

    public FaseResolucion(Tablero tablero, AtaqueRegistrado registrado) {

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