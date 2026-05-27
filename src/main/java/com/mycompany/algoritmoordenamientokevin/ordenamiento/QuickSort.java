/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class QuickSort extends ListaEnlazada{
    
    public void ordenar() {
        if (cabeza == null) return;
        Nodo ultimo = cabeza;
        while (ultimo.siguiente != null) {
            ultimo = ultimo.siguiente;
        }
        quickSort(cabeza, ultimo);
    }

    /**
     * Ordena el segmento de la lista entre los nodos 'inicio' y 'fin' (inclusive).
     */
    private void quickSort(Nodo inicio, Nodo fin) {
        if (inicio == null || inicio == fin) return;

        Nodo pivote = particionar(inicio, fin);

        // Ordenar el lado izquierdo (antes del pivote)
        if (pivote != inicio) {
            Nodo anteriorPivote = inicio;
            while (anteriorPivote.siguiente != pivote) {
                anteriorPivote = anteriorPivote.siguiente;
            }
            quickSort(inicio, anteriorPivote);
        }

        // Ordenar el lado derecho (después del pivote)
        if (pivote != fin) {
            quickSort(pivote.siguiente, fin);
        }
    }

    /**
     * Partición de Lomuto: usa 'fin' como pivote.
     * Mueve todos los elementos <= pivote a la izquierda del punto de retorno
     * e intercambia el pivote a su posición definitiva.
     *
     * @return el nodo donde quedó el valor del pivote tras la partición
     */
    private Nodo particionar(Nodo inicio, Nodo fin) {
        int valorPivote = fin.dato;
        Nodo i = null; // apuntará al último nodo con valor <= pivote

        for (Nodo j = inicio; j != fin; j = j.siguiente) {
            if (j.dato <= valorPivote) {
                i = (i == null) ? inicio : i.siguiente;
                int temp = i.dato;
                i.dato   = j.dato;
                j.dato   = temp;
            }
        }

        // Colocar el pivote justo después de todos los menores
        i = (i == null) ? inicio : i.siguiente;
        int temp = i.dato;
        i.dato   = fin.dato;
        fin.dato = temp;

        return i;
    }

}
