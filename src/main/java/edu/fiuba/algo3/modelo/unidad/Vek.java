package edu.fiuba.algo3.modelo.unidad;

import java.util.List;
import java.util.function.Predicate;

import edu.fiuba.algo3.modelo.Ataque;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

public class Vek extends Unidad{
    private Ataque ataqueAnunciado;
    private final int dañoDeAtaque;
    private final Movible movimiento;

    public Vek(int dañoDeAtaque, Movible movimiento) {
        this.dañoDeAtaque = dañoDeAtaque;
        this.movimiento = movimiento;
    }
    protected abstract List<Posicion> posicionesAtacables(Posicion desde, Tablero tablero);

    public void resolverAtaque(Tablero tablero) {
        ataqueAnunciado.aplicar(tablero);
        ataqueAnunciado = null;
    }

    public void mover(Tablero tablero, Posicion destino) {
        movimiento.mover(this, destino, tablero);
    }

    public void atacarA(Posicion objetivo) {
        ataqueAnunciado = new Ataque(objetivo, dañoDeAtaque);
    }


    @Override
    public void registrarEn(Tablero tablero) { tablero.registrarVek(this); }
}


