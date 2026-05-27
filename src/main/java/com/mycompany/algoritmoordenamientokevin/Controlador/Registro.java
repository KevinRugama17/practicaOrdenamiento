/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.Controlador;

/**
 *
 * @author kevin
 */
public class Registro {
      private final String region;
    private final int unitsSold;

    public Registro(String region, int unitsSold) {
        this.region = region;
        this.unitsSold = unitsSold;
    }

    public String getRegion() { return region; }
    public int getUnitsSold() { return unitsSold; }
}
