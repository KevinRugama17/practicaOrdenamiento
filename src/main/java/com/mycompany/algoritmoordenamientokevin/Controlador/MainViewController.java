/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.Controlador;

import com.mycompany.algoritmoordenamientokevin.ordenamiento.BurbujaSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.GnomeSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.ListaEnlazada;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
/**
 * FXML Controller class
 *
 * @author kevin
 */
public class MainViewController{
 

    // Componentes inyectados desde el FXML
    @FXML private TableView<Registro> tablaOriginal;
    @FXML private TableView<Registro> tablaOrdenada;
    @FXML private TableColumn<Registro, String> colRegionOrig;
    @FXML private TableColumn<Registro, Integer> colUnitsOrig;
    @FXML private TableColumn<Registro, String> colRegionOrd;
    @FXML private TableColumn<Registro, Integer> colUnitsOrd;

    @FXML private ComboBox<String> comboArchivo;
    @FXML private ComboBox<String> comboAlgoritmo;
    @FXML private ComboBox<String> comboColumna;
    @FXML private ComboBox<String> comboDireccion;

    @FXML private Label lblStatus;
    @FXML private Label lblIteraciones;
    @FXML private Label lblTiempo;

    // Listas nativas de JavaFX (Paquete: javafx.collections)
    private ObservableList<Registro> datosOriginales = FXCollections.observableArrayList();
    private ObservableList<Registro> datosOrdenados = FXCollections.observableArrayList();

    // TU ESTRUCTURA PROPIA para mantener la referencia original sin java.util
    private ListaEnlazada listaDatosPropios = new ListaEnlazada();

    @FXML
    public void initialize() {
        // Configurar las columnas de las tablas usando las propiedades de tu objeto POJO
        colRegionOrig.setCellValueFactory(new PropertyValueFactory<>("region"));
        colUnitsOrig.setCellValueFactory(new PropertyValueFactory<>("unitsSold"));
        
        colRegionOrd.setCellValueFactory(new PropertyValueFactory<>("region"));
        colUnitsOrd.setCellValueFactory(new PropertyValueFactory<>("unitsSold"));

        tablaOriginal.setItems(datosOriginales);
        tablaOrdenada.setItems(datosOrdenados);
        
        // Inicializar combos con textos
        comboArchivo.setItems(FXCollections.observableArrayList(
            "Sales_democienmil.csv", "Sales_demodocemil.csv", "Sales_demoseismil.csv"
        ));
        
        comboAlgoritmo.setItems(FXCollections.observableArrayList(
            "Hundimiento (Gnome Sort)", "Burbuja", "Burbuja mejorada", 
            "Selección lineal", "Selección con intercambio", "Quick sort", 
            "Radix Sort", "Merge Sort", "Shell Sort"
        ));
    }

    @FXML
    private void handleSeleccionArchivo() {
        String archivoSeleccionado = comboArchivo.getValue();
        if (archivoSeleccionado != null) {
            try {
                // RECOMENDACIÓN: Modificar tu CsvReader para que devuelva tu ListaEnlazada
                // o cargar directamente los datos recorriéndolos.
                
                datosOriginales.clear();
                datosOrdenados.clear();
                
                // Ejemplo asumiendo que procesas el archivo y lo metes en tu ListaEnlazada:
                // listaDatosPropios = CsvReader.leerCSV(archivoSeleccionado);
                
                lblStatus.setText("Estado: Archivo '" + archivoSeleccionado + "' cargado exitosamente.");
            } catch (Exception ex) {
                mostrarAlerta("Error de Lectura", "Error al leer el archivo CSV: " + ex.getMessage());
            }
        }
    }

    @FXML
    private void handleEjecutarOrdenamiento() {
        if (datosOriginales.isEmpty()) {
            mostrarAlerta("Sin datos", "Por favor, cargue un archivo CSV antes de ordenar.");
            return;
        }

        int algoIdx = comboAlgoritmo.getSelectionModel().getSelectedIndex();
        boolean ordenarTexto = comboColumna.getSelectionModel().getSelectedIndex() == 0;
        boolean ascendente = comboDireccion.getSelectionModel().getSelectedIndex() == 0;

        if (algoIdx == 6 && ordenarTexto) {
            mostrarAlerta("Algoritmo No Soportado", "Radix Sort no soporta ordenamiento alfanumérico.");
            return;
        }

        // Aquí instancias tu ListaEnlazada propia y ejecutas el ordenamiento analogo a tu código original
        ListaEnlazada listaSort = obtenerAlgoritmo(algoIdx);
        
        // Pasas los datos de tu lista guardada a la lista que va a ordenar
        // ... (Algoritmo de ordenamiento propio)
        
        long startTime = System.nanoTime();
        listaSort.ordenar();
        long endTime = System.nanoTime();

        // Para mostrar los resultados en la tabla ordenada, recorres tu ListaEnlazada propia
        // y añades los elementos a 'datosOrdenados' (que es ObservableList)
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private ListaEnlazada obtenerAlgoritmo(int idx) {
        switch (idx) {
            case 0: return new GnomeSort();
            case 1: return new BurbujaSort();
            // ... resto de tus algoritmos
            default: return new BurbujaSort();
        }
    }
}

