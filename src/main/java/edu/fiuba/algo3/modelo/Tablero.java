package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.List;

import static edu.fiuba.algo3.modelo.casilla.TipoCasilla.ACIDO;
import static edu.fiuba.algo3.modelo.casilla.TipoCasilla.AGUA;
import static edu.fiuba.algo3.modelo.casilla.TipoCasilla.EDIFICIO;
import static edu.fiuba.algo3.modelo.casilla.TipoCasilla.MONTAÑA;
import static edu.fiuba.algo3.modelo.casilla.TipoCasilla.SUELO;

import edu.fiuba.algo3.modelo.casilla.*;
import edu.fiuba.algo3.modelo.errors.UnidadYaColocadaException;
import edu.fiuba.algo3.modelo.unidad.Unidad;


public class Tablero {

    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;

    private final Casilla[][] casillas;

    private static final TipoCasilla [][] TABLERO = {
            {SUELO, AGUA, SUELO, MONTAÑA, SUELO, SUELO, SUELO, SUELO},
            {SUELO, SUELO, SUELO, SUELO, SUELO, EDIFICIO, SUELO, SUELO},
            {SUELO, SUELO, ACIDO, SUELO, AGUA, AGUA, SUELO, SUELO},
            {AGUA, SUELO, SUELO, SUELO, SUELO, SUELO, ACIDO, SUELO},
            {SUELO, SUELO, MONTAÑA, SUELO, SUELO, SUELO, SUELO, MONTAÑA},
            {SUELO, AGUA, SUELO, EDIFICIO, SUELO, SUELO, SUELO, SUELO},
            {SUELO, SUELO, AGUA, SUELO, SUELO, AGUA, SUELO, SUELO},
            {ACIDO, SUELO, SUELO, ACIDO, SUELO, SUELO, SUELO, EDIFICIO}

    };

    public Tablero(){
        this.casillas = new Casilla[FILAS][COLUMNAS];
        inicializarTablero();
    }

    private Casilla crearCasilla(TipoCasilla tipo) {
        switch (tipo) {
            case SUELO:
                return new Suelo();
            case EDIFICIO:
                return new Edificio();
            case MONTAÑA:
                return new Montaña();
            case AGUA:
                return new Agua();
            case ACIDO:
                return new Acido();
            default:
                throw new IllegalArgumentException("Tipo de casilla desconocido: " + tipo);
        }
    }

    private void inicializarTablero() {
        for(int fila = 0; fila < FILAS; fila++){
            for(int columna = 0; columna < COLUMNAS; columna++){
                casillas[fila][columna] = crearCasilla(TABLERO[fila][columna]);
            }
        }
    }

    public Casilla obtenerCasilla(Posicion posicion) {
        if(!posicion.estaEnElTablero(FILAS, COLUMNAS)){
            throw new IllegalArgumentException(
                    "La posición no está dentro de los limites del tablero");
        }

        int fila = posicion.getFila();
        int columna = posicion.getColumna();

        return casillas[fila][columna];
    }

    public List<Casilla> obtenerCasillasOrtogonales(Posicion posicion) {
        List<Posicion> posiciones = posicion.obtenerPosicionesOrtogonales(FILAS, COLUMNAS);

        List<Casilla> casillasOrtogonales = new ArrayList<>();

        for (Posicion posicionAdyacenteOrtogonal : posiciones) {
            Casilla casillaAdyacenteOrtogonal = obtenerCasilla(posicionAdyacenteOrtogonal);
            casillasOrtogonales.add(casillaAdyacenteOrtogonal);
        }

        return casillasOrtogonales;
    }

    public void colocar(Unidad unidad, Posicion posicion) {

        if (unidad.getPosicion() != null) {
            throw new UnidadYaColocadaException();
        }

        Casilla casilla = obtenerCasilla(posicion);

        casilla.ocupar(unidad);
        unidad.actualizarPosicion(posicion);
    }

}
