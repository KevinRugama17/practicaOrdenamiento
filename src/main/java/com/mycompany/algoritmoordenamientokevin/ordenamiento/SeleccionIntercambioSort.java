/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class SeleccionIntercambioSort extends ListaEnlazada{
    
     public void ordenar() {
        int n = longitud();
        if (n <= 1) return;

        for (int i = 0; i < n - 1; i++) {
            Nodo nodoI = obtenerNodo(i);

            for (int j = i + 1; j < n; j++) {
                Nodo nodoJ = obtenerNodo(j);

                // Intercambio inmediato: nodoI queda siempre con el menor visto
                if (nodoJ.dato < nodoI.dato) {
                    int temp  = nodoI.dato;
                    nodoI.dato = nodoJ.dato;
                    nodoJ.dato = temp;
                    // nodoI sigue apuntando al mismo nodo físico,
                    // ahora con el nuevo valor menor; la comparación continúa.
                }
            }
        }
    }
}
