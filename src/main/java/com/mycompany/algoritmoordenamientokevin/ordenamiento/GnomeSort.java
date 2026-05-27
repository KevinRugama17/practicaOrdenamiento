/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class GnomeSort extends ListaEnlazada{
     public void ordenar() {
        int n = longitud();
        if (n <= 1) return;

        int pos = 0;

        while (pos < n - 1) {
            Nodo actual   = obtenerNodo(pos);
            Nodo siguiente = actual.siguiente; // equivale a obtenerNodo(pos + 1)

            if (actual.dato <= siguiente.dato) {
                // Par en orden: avanzar
                pos++;
            } else {
                // Par fuera de orden: intercambiar y retroceder
                int temp = actual.dato;
                actual.dato = siguiente.dato;
                siguiente.dato = temp;

                if (pos > 0) {
                    pos--;
                }
            }
        }
    }

}
