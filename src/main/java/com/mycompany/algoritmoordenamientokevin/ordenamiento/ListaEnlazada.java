/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class ListaEnlazada {
    
    protected Nodo cabeza;
    protected boolean ordenarPorTexto = false;
    protected boolean ascendente = true;
    protected long iteraciones = 0;

    public ListaEnlazada() {
        this.cabeza = null;
    }
  public void insertar(String texto, int dato) {
        Nodo nuevo = new Nodo(texto, dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }
       public void setOrdenarPorTexto(boolean ordenarPorTexto) {
        this.ordenarPorTexto = ordenarPorTexto;
    }

    public boolean isOrdenarPorTexto() {
        return ordenarPorTexto;
    }

    public void setAscendente(boolean ascendente) {
        this.ascendente = ascendente;
    }

    public boolean isAscendente() {
        return ascendente;
    }

    public void resetIteraciones() {
        this.iteraciones = 0;
    }

    public long getIteraciones() {
        return this.iteraciones;
    }

    public void incrementarIteraciones() {
        this.iteraciones++;
    }       
        
 public int longitud() {
        int contador = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    /**
     * Retorna el nodo en la posición indicada (base 0).
     * Retorna null si el índice está fuera de rango.
     */
    public Nodo obtenerNodo(int indice) {
        Nodo actual = cabeza;
        int i = 0;
        while (actual != null && i < indice) {
            actual = actual.siguiente;
            i++;
        }
        return actual;
    }

    public void ordenar() {
        // Base implementation does nothing
    }
}
