package edu.fiuba.algo3.modelo.unidad;

public class DañoContaminada implements Dañable{

    private static final int MULTIPLICADOR_CONTAMINACION = 2;

    @Override
    public int calcularDaño(int daño){
        return daño * MULTIPLICADOR_CONTAMINACION;
    }
}
