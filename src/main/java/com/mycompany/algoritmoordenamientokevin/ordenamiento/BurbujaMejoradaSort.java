/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class BurbujaMejoradaSort extends ListaEnlazada{
    
    public void ordenar() {
        int n = longitud();
        if (n <= 1) return;

        for (int i = 0; i < n - 1; i++) {
            boolean huboIntercambio = false;
            Nodo actual = cabeza;

            for (int j = 0; j < n - 1 - i; j++) {
                if (actual.dato > actual.siguiente.dato) {
                    int temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    huboIntercambio = true;
                }
                actual = actual.siguiente;
            }

            // Si no hubo intercambios en esta pasada, la lista ya está ordenada
            if (!huboIntercambio) {
                break;
            }
        }
    }

   

}
