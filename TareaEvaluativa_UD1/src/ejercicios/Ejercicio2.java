package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2 {

	public static void main(String[] args) {
		//Creación de variable que contendrá la palabra más larga
		String palabraML = "";
		
		// Crear un objeto BufferedReader para leer el fichero original
		try (BufferedReader br = new BufferedReader(new FileReader("." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "entrada.txt"))) {
			
			// Leer cada línea del fichero original
			String linea = br.readLine();
			
			while (linea != null) {
				// Separar la línea en palabras usando el método split
	            String[] palabras = linea.split(" ");
	                
	            // Recorrer cada palabra de la línea
	            for (String palabra : palabras) {
	            	if (palabra.length() > palabraML.length()) {
	            		palabraML = palabra;
	            		}
	            }
	            
	            // Leer la siguiente línea del fichero original
                linea = br.readLine();
                }
			
			System.out.println("La palabra más larga que contiene el fichero es: "+palabraML);
			
		} catch (IOException e) {
			System.out.println("Error al leer el fichero original");
			}
		}
}
	
