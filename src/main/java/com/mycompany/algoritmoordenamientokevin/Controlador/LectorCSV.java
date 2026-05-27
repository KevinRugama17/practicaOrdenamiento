/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algoritmoordenamientokevin.Controlador;

import com.mycompany.algoritmoordenamientokevin.ordenamiento.ListaEnlazada;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author kevin
 */
public class LectorCSV {

    // Ahora el método devuelve directamente tu ListaEnlazada
    public static ListaEnlazada leerCSV(String rutaArchivo) throws IOException {
        ListaEnlazada registros = new ListaEnlazada();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean esPrimeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                
                String delimiter = ",";
                if (linea.contains(";")) {
                    delimiter = ";";
                }
                
                String[] columnas = parseLine(linea, delimiter);
                if (columnas.length < 2) {
                    continue;
                }

                String region = columnas[0].trim();
                String unitsStr = columnas[1].trim();

                // Quitar comillas si están presentes
                if (region.startsWith("\"") && region.endsWith("\"")) {
                    region = region.substring(1, region.length() - 1);
                }
                if (unitsStr.startsWith("\"") && unitsStr.endsWith("\"")) {
                    unitsStr = unitsStr.substring(1, unitsStr.length() - 1);
                }

                // Intentar parsear el número de unidades vendidas
                try {
                    int units = Integer.parseInt(unitsStr.trim());
                    // Se inserta directamente en tu estructura de datos propia
                    registros.insertar(region, units); 
                } catch (NumberFormatException e) {
                    // Omitir si es la cabecera u otra línea inválida
                    if (!esPrimeraLinea) {
                        System.err.println("Línea omitida por formato numérico inválido: " + linea);
                    }
                }
                esPrimeraLinea = false;
            }
        }
        return registros;
    }

    private static String[] parseLine(String line, String delimiter) {
        // Usamos un arreglo nativo con un tamaño inicial razonable
        String[] result = new String[10]; 
        int count = 0;
        
        StringBuilder curVal = new StringBuilder();
        boolean inQuotes = false;
        char[] chars = line.toCharArray();
        char delimChar = delimiter.charAt(0);

        for (char c : chars) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == delimChar && !inQuotes) {
                // Si el arreglo se llena, lo redimensionamos manualmente
                if (count == result.length) {
                    String[] temp = new String[result.length * 2];
                    System.arraycopy(result, 0, temp, 0, result.length);
                    result = temp;
                }
                result[count++] = curVal.toString();
                curVal.setLength(0);
            } else {
                curVal.append(c);
            }
        }
        
        // Agregar el último valor procesado
        if (count == result.length) {
            String[] temp = new String[result.length + 1];
            System.arraycopy(result, 0, temp, 0, result.length);
            result = temp;
        }
        result[count++] = curVal.toString();

        // Ajustar el arreglo al tamaño exacto de columnas que realmente se encontraron
        String[] finalResult = new String[count];
        System.arraycopy(result, 0, finalResult, 0, count);
        
        return finalResult;
    }
}

