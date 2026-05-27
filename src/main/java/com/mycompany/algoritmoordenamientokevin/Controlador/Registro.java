/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.Controlador;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class Registro {
   private final SimpleStringProperty  region;
    private final SimpleIntegerProperty unitsSold;
 
    public Registro(String region, int unitsSold) {
        this.region    = new SimpleStringProperty(region);
        this.unitsSold = new SimpleIntegerProperty(unitsSold);
    }
 
    // ── Getters normales ──────────────────────────────────────────────────────
    public String getRegion()   { return region.get(); }
    public int    getUnitsSold(){ return unitsSold.get(); }
 
   
    public SimpleStringProperty  regionProperty()    { 
        return region;
    }
    public SimpleIntegerProperty unitsSoldProperty() { 
        return unitsSold;
    }
}