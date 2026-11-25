package edu.fiuba.algo3.modelo.tablero;

import java.util.function.Supplier;

public enum TipoCasilla {
    SUELO(Suelo::new),
    EDIFICIO(() -> new Edificio()),
    MONTANIA(Montania::new),
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
