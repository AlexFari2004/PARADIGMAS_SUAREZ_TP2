package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.RedElectrica;

import java.util.function.Supplier;

public enum TipoCasilla {
    SUELO(Suelo::new),
    EDIFICIO(() -> new Edificio()),
    MONTAÑA(Montaña::new),
    AGUA(Agua::new),
    ACIDO(Acido::new);

    private final Supplier<Casilla> fabrica;

    TipoCasilla(Supplier<Casilla> fabrica) {
        this.fabrica = fabrica;
    }

    public Casilla crearCasilla() {
        return fabrica.get();
    }
}
