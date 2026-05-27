/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.ordenamiento;

/**
 *
 * @author kevin
 */
public class Nodo {
    public String texto;
    public int dato;
    public Nodo siguiente;

    public Nodo(String texto, int dato) {
        this.texto = texto;
        this.dato = dato;
        this.siguiente = null;
    }
    
     public Nodo(int dato) {
        this.texto = "";
        this.dato = dato;
        this.siguiente = null;
    }
    
    
}
