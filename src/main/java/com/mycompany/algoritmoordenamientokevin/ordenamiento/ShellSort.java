/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class ShellSort extends ListaEnlazada{
    
      public void ordenar() {
        int n = longitud();
        if (n <= 1) return;

        // Reducir la brecha a la mitad en cada pasada exterior
        for (int brecha = n / 2; brecha > 0; brecha /= 2) {

            // Ordenamiento por inserción con la brecha actual
            for (int i = brecha; i < n; i++) {
                int valorTemp = obtenerNodo(i).dato;
                int j = i;

                // Desplazar hacia la derecha los elementos que están a
                // 'brecha' posiciones de distancia y son mayores que valorTemp
                while (j >= brecha && obtenerNodo(j - brecha).dato > valorTemp) {
                    obtenerNodo(j).dato = obtenerNodo(j - brecha).dato;
                    j -= brecha;
                }

                // Insertar valorTemp en la posición correcta
                obtenerNodo(j).dato = valorTemp;
            }
        }
    }
}
