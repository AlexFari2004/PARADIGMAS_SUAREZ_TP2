package edu.fiuba.algo3.modelo;

public class RedElectrica {
    private int energia;

    public RedElectrica(int redElectrica) {
        this.energia = redElectrica;
    }

    public void consumirEnergia(int cantidad) {
        energia = Math.max(0, energia - cantidad);
    }

    public boolean energiaDisponible() { return energia == 0; }
}