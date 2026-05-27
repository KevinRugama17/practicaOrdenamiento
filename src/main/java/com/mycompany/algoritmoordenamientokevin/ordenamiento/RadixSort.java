/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class RadixSort extends ListaEnlazada{
    
    public void ordenar() {
        if (cabeza == null) return;

        // Paso 1: encontrar el valor máximo
        int maximo = cabeza.dato;
        Nodo aux = cabeza.siguiente;
        while (aux != null) {
            if (aux.dato > maximo) maximo = aux.dato;
            aux = aux.siguiente;
        }

        // Paso 2: aplicar counting-sort estable por cada posición de dígito
        // exp=1 → unidades, exp=10 → decenas, exp=100 → centenas, etc.
        for (int exp = 1; maximo / exp > 0; exp *= 10) {
            distribuirYRecolectar(exp);
        }
    }

    /**
     * Una pasada de Radix Sort: distribuye los nodos en 10 cubetas según
     * el dígito en la posición 'exp', luego los recolecta en orden.
     */
    private void distribuirYRecolectar(int exp) {
        // Cabeza y cola de cada una de las 10 cubetas (dígitos 0–9)
        Nodo[] cabezasCubeta = new Nodo[10];
        Nodo[] colasCubeta   = new Nodo[10];

        // ── Distribuir ──────────────────────────────────────────────────
        Nodo actual = cabeza;
        while (actual != null) {
            Nodo siguiente = actual.siguiente;
            int digito = (actual.dato / exp) % 10;

            actual.siguiente = null; // desconectar el nodo antes de encolarlo

            if (cabezasCubeta[digito] == null) {
                // Cubeta vacía: el nodo es cabeza y cola a la vez
                cabezasCubeta[digito] = actual;
                colasCubeta[digito]   = actual;
            } else {
                // Cubeta con elementos: agregar al final para mantener estabilidad
                colasCubeta[digito].siguiente = actual;
                colasCubeta[digito]           = actual;
            }
            actual = siguiente;
        }

        // ── Recolectar ───────────────────────────────────────────────────
        cabeza = null;
        Nodo cola = null;
        for (int d = 0; d <= 9; d++) {
            if (cabezasCubeta[d] != null) {
                if (cabeza == null) {
                    cabeza = cabezasCubeta[d];
                } else {
                    cola.siguiente = cabezasCubeta[d];
                }
                cola = colasCubeta[d]; // actualizar cola al final de esta cubeta
            }
        }
    }

}
