/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class SeleccionLinealSort extends ListaEnlazada{
        public void ordenar() {
        int n = longitud();
        if (n <= 1) return;

        for (int i = 0; i < n - 1; i++) {
            // Paso 1: encontrar el índice del elemento mínimo en [i, n-1]
            int indiceMin = i;
            for (int j = i + 1; j < n; j++) {
                if (obtenerNodo(j).dato < obtenerNodo(indiceMin).dato) {
                    indiceMin = j;
                }
            }

            // Paso 2: intercambiar el mínimo con el elemento en la posición i
            if (indiceMin != i) {
                Nodo nodoI   = obtenerNodo(i);
                Nodo nodoMin = obtenerNodo(indiceMin);
                int temp = nodoI.dato;
                nodoI.dato   = nodoMin.dato;
                nodoMin.dato = temp;
            }
        }
    }
}
