package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.errores.UnidadYaColocadaException;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.vek.Vek;

import java.util.ArrayList;
import java.util.List;

import static edu.fiuba.algo3.modelo.tablero.TipoCasilla.*;


public class Tablero {

    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;
    private final List<Mech> mechs = new ArrayList<>();
    private final List<Vek> veks = new ArrayList<>();

    private final Casilla[][] casillas;
    private final RedElectrica redElectrica;
    private final GestorEmpuje gestorEmpuje;

    private static final TipoCasilla[][] TABLERO = {
            {SUELO, AGUA, SUELO, MONTANIA, SUELO, SUELO, SUELO, SUELO},
            {SUELO, SUELO, SUELO, SUELO, SUELO, EDIFICIO, SUELO, SUELO},
            {SUELO, SUELO, ACIDO, SUELO, AGUA, AGUA, SUELO, SUELO},
            {AGUA, SUELO, SUELO, SUELO, SUELO, SUELO, ACIDO, SUELO},
            {SUELO, SUELO, MONTANIA, SUELO, SUELO, SUELO, SUELO, MONTANIA},
            {SUELO, AGUA, SUELO, EDIFICIO, SUELO, SUELO, SUELO, SUELO},
            {SUELO, SUELO, AGUA, SUELO, SUELO, AGUA, SUELO, SUELO},
            {ACIDO, SUELO, SUELO, ACIDO, SUELO, SUELO, SUELO, EDIFICIO}

    };
    public Tablero() {
        this(new RedElectrica());
    }

    public Tablero(RedElectrica redElectrica) {
        this.redElectrica = redElectrica;
        this.casillas = new Casilla[FILAS][COLUMNAS];
        inicializarTablero();
        this.gestorEmpuje = new GestorEmpuje(this);
    }

    private void inicializarTablero() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                casillas[fila][columna] = TABLERO[fila][columna].crearCasilla();
            }
        }
    }

    public Casilla obtenerCasilla(Posicion posicion) {
        if (!posicion.estaEnElTablero(FILAS, COLUMNAS)) {
            throw new IllegalArgumentException(
                    "La posición no está dentro de los limites del tablero");
        }

        int fila = posicion.getFila();
        int columna = posicion.getColumna();

        return casillas[fila][columna];
    }

    public List<Casilla> obtenerCasillasOrtogonales(Posicion posicion) {
        List<Casilla> casillasOrtogonales = new ArrayList<>();

        for (Posicion posicionAdyacenteOrtogonal : obtenerPosicionesOrtogonales(posicion)) {
            Casilla casillaAdyacenteOrtogonal = obtenerCasilla(posicionAdyacenteOrtogonal);
            casillasOrtogonales.add(casillaAdyacenteOrtogonal);
        }

        return casillasOrtogonales;
    }

    public List<Posicion> obtenerPosicionesOrtogonales(Posicion posicion) {
        return posicion.obtenerPosicionesOrtogonales(FILAS, COLUMNAS);
    }

    public void colocar(Unidad unidad, Posicion posicion) {

        if (unidad.obtenerPosicion() != null) {
            throw new UnidadYaColocadaException();
        }

        Casilla casilla = obtenerCasilla(posicion);

        casilla.ocupar(unidad);
        unidad.actualizarPosicion(posicion);
        unidad.registrarEn(this);
    }

    public void registrarMech(Mech mech) {
        mechs.add(mech);
    }

    public void registrarVek(Vek vek) {
        veks.add(vek);
    }

    public List<Mech> obtenerMechsVivos() {
        return mechs.stream().filter(Mech::estaVivo).toList();
    }

    public List<Vek> obtenerVeks() {
        return veks.stream().filter(Vek::estaVivo).toList();
    }

    public List<Posicion> obtenerPosicionEdificios() {
        List<Posicion> edificios = new ArrayList<>();

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (casillas[fila][columna].getTipo() == TipoCasilla.EDIFICIO) {
                    edificios.add(new Posicion(fila, columna));
                }
            }
        }

        return edificios;
    }

    public boolean estaDentro(Posicion p) { return p.estaEnElTablero(FILAS, COLUMNAS); }

    public void empujar(Unidad unidad, Direccion direccion) {
        gestorEmpuje.empujar(unidad, direccion);
    }

    public void destruirEdificioEn(Posicion posicion) {
        reemplazarCasilla(posicion, TipoCasilla.SUELO.crearCasilla());
        consumirRedElectrica(1);
    }
    public void consumirRedElectrica(int cantidad) {
        redElectrica.consumirEnergia(cantidad);
    }
    public int obtenerEnergiaRedElectrica() {
        return redElectrica.obtenerEnergia();
    }

    public boolean redElectricaAgotada() {
        return redElectrica.estaAgotada();
    }
    public void reemplazarCasilla(Posicion posicion, Casilla nuevaCasilla) {
        if (!estaDentro(posicion)) {
            throw new IllegalArgumentException(
                    "La posición no está dentro de los limites del tablero");
        }
        int fila = posicion.getFila();
        int columna = posicion.getColumna();
        casillas[fila][columna] = nuevaCasilla;
    }
}
