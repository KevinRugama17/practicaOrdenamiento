/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class MergeSort extends ListaEnlazada{
    
    public void ordenar() {
        cabeza = mergeSort(cabeza);
    }

    /**
     * Ordena la sublista que comienza en 'nodo'.
     * Retorna la cabeza de la sublista ordenada.
     */
    private Nodo mergeSort(Nodo nodo) {
        // Caso base: lista vacía o con un solo elemento ya está ordenada
        if (nodo == null || nodo.siguiente == null) {
            return nodo;
        }

        // Paso 1: encontrar el medio y dividir la lista en dos mitades
        Nodo medio        = obtenerMedio(nodo);
        Nodo segundaMitad = medio.siguiente;
        medio.siguiente   = null; // cortar el enlace para separar las mitades

        // Paso 2: ordenar cada mitad recursivamente
        Nodo izquierda = mergeSort(nodo);
        Nodo derecha   = mergeSort(segundaMitad);

        // Paso 3: fusionar las dos mitades ya ordenadas
        return fusionar(izquierda, derecha);
    }

    /**
     * Localiza el nodo del medio usando el algoritmo "tortuga y liebre":
     * la tortuga avanza 1 paso y la liebre avanza 2 pasos por iteración.
     * Cuando la liebre llega al final, la tortuga está en el medio.
     */
    private Nodo obtenerMedio(Nodo cabezaLocal) {
        Nodo tortuga = cabezaLocal;
        Nodo liebre  = cabezaLocal.siguiente;

        while (liebre != null && liebre.siguiente != null) {
            tortuga = tortuga.siguiente;
            liebre  = liebre.siguiente.siguiente;
        }
        return tortuga;
    }

    /**
     * Fusiona dos listas enlazadas ordenadas en una sola lista ordenada.
     * Usa un nodo centinela para simplificar la construcción del resultado.
     * Retorna la cabeza de la lista fusionada.
     */
    private Nodo fusionar(Nodo izquierda, Nodo derecha) {
        Nodo centinela = new Nodo(0); // nodo auxiliar, su dato no importa
        Nodo actual    = centinela;

        while (izquierda != null && derecha != null) {
            if (izquierda.dato <= derecha.dato) {
                actual.siguiente = izquierda;
                izquierda        = izquierda.siguiente;
            } else {
                actual.siguiente = derecha;
                derecha          = derecha.siguiente;
            }
            actual = actual.siguiente;
        }

        // Anexar la mitad que aún tiene nodos restantes
        actual.siguiente = (izquierda != null) ? izquierda : derecha;

        return centinela.siguiente;
    }
}
