package com.mycompany.algoritmoordenamientokevin.Controlador;

import com.mycompany.algoritmoordenamientokevin.ordenamiento.BurbujaMejoradaSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.BurbujaSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.GnomeSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.ListaEnlazada;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.MergeSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.Nodo;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.QuickSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.RadixSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.SeleccionIntercambioSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.SeleccionLinealSort;
import com.mycompany.algoritmoordenamientokevin.ordenamiento.ShellSort;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;

public class MainViewController {

    // ── Tablas ────────────────────────────────────────────────────────────────
    @FXML private TableView<Registro>            tablaOriginal;
    @FXML private TableView<Registro>            tablaOrdenada;
    @FXML private TableColumn<Registro, String>  colRegionOrig;
    @FXML private TableColumn<Registro, Integer> colUnitsOrig;
    @FXML private TableColumn<Registro, String>  colRegionOrd;
    @FXML private TableColumn<Registro, Integer> colUnitsOrd;

    // ── Combos ────────────────────────────────────────────────────────────────
    @FXML private ComboBox<String> comboArchivo;
    @FXML private ComboBox<String> comboAlgoritmo;
    @FXML private ComboBox<String> comboColumna;
    @FXML private ComboBox<String> comboDireccion;

    // ── Etiquetas ─────────────────────────────────────────────────────────────
    @FXML private Label lblStatus;
    @FXML private Label lblIteraciones;
    @FXML private Label lblTiempo;

    // ── Datos ─────────────────────────────────────────────────────────────────
    private final ObservableList<Registro> datosOriginales = FXCollections.observableArrayList();
    private final ObservableList<Registro> datosOrdenados  = FXCollections.observableArrayList();

    /** Copia inmutable de los datos cargados para reutilizar en cada ordenamiento. */
    private ListaEnlazada listaCargada = new ListaEnlazada();

    // ─────────────────────────────────────────────────────────────────────────
    @FXML
    public void initialize() {
        // Columnas tabla original
        colRegionOrig.setCellValueFactory(new PropertyValueFactory<>("region"));
        colUnitsOrig .setCellValueFactory(new PropertyValueFactory<>("unitsSold"));

        // Columnas tabla ordenada
        colRegionOrd.setCellValueFactory(new PropertyValueFactory<>("region"));
        colUnitsOrd .setCellValueFactory(new PropertyValueFactory<>("unitsSold"));

        tablaOriginal.setItems(datosOriginales);
        tablaOrdenada.setItems(datosOrdenados);

        // ── CSV disponibles en la raíz del proyecto ──────────────────────────
        String raiz  = System.getProperty("user.dir");
        File[] csvs  = new File(raiz).listFiles(
                f -> f.isFile() && f.getName().toLowerCase().endsWith(".csv"));

        ObservableList<String> nombres = FXCollections.observableArrayList();
        if (csvs != null) {
            for (File f : csvs) {
                nombres.add(f.getName());
            }
        }
        if (nombres.isEmpty()) {
            nombres.add("(No hay archivos .csv en la raíz del proyecto)");
        }
        comboArchivo.setItems(nombres);

        // ── Algoritmos ───────────────────────────────────────────────────────
        comboAlgoritmo.setItems(FXCollections.observableArrayList(
            "Hundimiento (Gnome Sort)",
            "Burbuja",
            "Burbuja mejorada",
            "Selección lineal",
            "Selección con intercambio",
            "Quick sort",
            "Radix Sort",
            "Merge Sort",
            "Shell Sort"
        ));

        // ── Columna de orden ─────────────────────────────────────────────────
        comboColumna.setItems(FXCollections.observableArrayList(
            "Region (texto)",
            "Units Sold (número)"
        ));
        comboColumna.getSelectionModel().selectLast();   // número por defecto

        // ── Dirección ────────────────────────────────────────────────────────
        comboDireccion.setItems(FXCollections.observableArrayList(
            "Ascendente",
            "Descendente"
        ));
        comboDireccion.getSelectionModel().selectFirst();
    }

    // ── Carga del CSV seleccionado ────────────────────────────────────────────
    @FXML
    private void handleSeleccionArchivo() {
        String nombreArchivo = comboArchivo.getValue();
        if (nombreArchivo == null || nombreArchivo.startsWith("(")) return;

        String ruta   = System.getProperty("user.dir") + File.separator + nombreArchivo;
        File   archivo = new File(ruta);

        if (!archivo.exists()) {
            mostrarAlerta("Archivo no encontrado", "No se encontró:\n" + ruta);
            return;
        }

        try {
            // 1. Leer CSV → lista propia
            listaCargada = LectorCSV.leerCSV(ruta);

            // 2. Limpiar tablas
            datosOriginales.clear();
            datosOrdenados.clear();

            // 3. Recorrer la lista nodo a nodo y llenar la tabla original
            Nodo nodo = listaCargada.obtenerNodo(0); // devuelve cabeza (índice 0)
            while (nodo != null) {
                datosOriginales.add(new Registro(nodo.texto, nodo.dato));
                nodo = nodo.siguiente;
            }

            lblStatus.setText("Estado: '" + nombreArchivo
                    + "' cargado — " + datosOriginales.size() + " registros.");

        } catch (IOException ex) {
            mostrarAlerta("Error de lectura", ex.getMessage());
        }
    }

    // ── Ordenamiento ──────────────────────────────────────────────────────────
    @FXML
    private void handleEjecutarOrdenamiento() {
        if (datosOriginales.isEmpty()) {
            mostrarAlerta("Sin datos", "Primero cargue un archivo CSV.");
            return;
        }
        if (comboAlgoritmo.getValue() == null) {
            mostrarAlerta("Sin algoritmo", "Seleccione un algoritmo.");
            return;
        }

        int     algoIdx      = comboAlgoritmo.getSelectionModel().getSelectedIndex();
        boolean ordenarTexto = comboColumna.getSelectionModel().getSelectedIndex() == 0;
        boolean ascendente   = comboDireccion.getSelectionModel().getSelectedIndex() == 0;

        if (algoIdx == 6 && ordenarTexto) {
            mostrarAlerta("No soportado", "Radix Sort no soporta ordenamiento por texto.");
            return;
        }

        // 1. Crear instancia del algoritmo elegido y copiar datos originales
        ListaEnlazada listaSort = obtenerAlgoritmo(algoIdx);
        listaSort.setOrdenarPorTexto(ordenarTexto);
        listaSort.setAscendente(ascendente);

        Nodo nodo = listaCargada.obtenerNodo(0);
        while (nodo != null) {
            listaSort.insertar(nodo.texto, nodo.dato);
            nodo = nodo.siguiente;
        }

        // 2. Ordenar y medir tiempo
        long inicio = System.nanoTime();
        listaSort.ordenar();
        long fin = System.nanoTime();

        // 3. Volcar resultado en la tabla ordenada
        datosOrdenados.clear();

        Nodo resultado = listaSort.obtenerNodo(0);

        if (!ascendente) {
            // Invertir: cargar en arreglo nativo y leer al revés
            int n = listaSort.longitud();
            Registro[] tmp = new Registro[n];
            int i = 0;
            while (resultado != null) {
                tmp[i++] = new Registro(resultado.texto, resultado.dato);
                resultado = resultado.siguiente;
            }
            for (int j = n - 1; j >= 0; j--) {
                datosOrdenados.add(tmp[j]);
            }
        } else {
            while (resultado != null) {
                datosOrdenados.add(new Registro(resultado.texto, resultado.dato));
                resultado = resultado.siguiente;
            }
        }

        double ms = (fin - inicio) / 1_000_000.0;
        lblTiempo.setText(String.format("Tiempo: %.3f ms", ms));
        lblIteraciones.setText("Registros ordenados: " + datosOrdenados.size());
        lblStatus.setText("Estado: Ordenado con '"
                + comboAlgoritmo.getValue() + "' ("
                + (ascendente ? "↑" : "↓") + ").");
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private ListaEnlazada obtenerAlgoritmo(int idx) {
        switch (idx) {
            case 0:  return new GnomeSort();
            case 1:  return new BurbujaSort();
            case 2:  return new BurbujaMejoradaSort();
            case 3:  return new SeleccionLinealSort();
            case 4:  return new SeleccionIntercambioSort();
            case 5:  return new QuickSort();
            case 6:  return new RadixSort();
            case 7:  return new MergeSort();
            case 8:  return new ShellSort();
            default: return new BurbujaSort();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}