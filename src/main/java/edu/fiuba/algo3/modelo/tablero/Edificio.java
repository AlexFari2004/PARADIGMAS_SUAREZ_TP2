package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.vek.EfectoAtaque;

public class Edificio extends Casilla {
    private final RedElectrica red;

    public Edificio(RedElectrica red) {
        this.red = red;
    }

    @Override
    public void recibirDaño(int daño) {
        red.consumirEnergia(1);
    }

    @Override
    public void recibirAtaque(int daño, EfectoAtaque efecto) {
        red.consumirEnergia(1);
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.EDIFICIO;
    }
}
