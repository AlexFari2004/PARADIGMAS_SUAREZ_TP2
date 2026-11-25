package edu.fiuba.algo3.modelo;

public class RedElectrica {
    private static final int ENERGIA_INICIAL = 5;
    private int energia;

    public RedElectrica(int energiaInicial) {
        this.energia = Math.max(0, energiaInicial);
    }

    public RedElectrica() {
        this(ENERGIA_INICIAL);
    }

    public void consumirEnergia(int cantidad) {
        if (cantidad > 0) {
            energia = Math.max(0, energia - cantidad);
        }
    }

    public boolean estaAgotada() {
        return energia == 0;
    }

    public int obtenerEnergia() {
        return energia;
    }
}