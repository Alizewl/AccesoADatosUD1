package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2 {

	public static void main(String[] args) {
		//Creaci�n de variable que contendr� la palabra m�s larga
		String palabraML = "";
		
		// Crear un objeto BufferedReader para leer el fichero original
		try (BufferedReader br = new BufferedReader(new FileReader("." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "entrada.txt"))) {
			
			// Leer cada l�nea del fichero original
			String linea = br.readLine();
			
			while (linea != null) {
				// Separar la l�nea en palabras usando el m�todo split
	            String[] palabras = linea.split(" ");
	                
	            // Recorrer cada palabra de la l�nea
	            for (String palabra : palabras) {
	            	if (palabra.length() > palabraML.length()) {
	            		palabraML = palabra;
	            		}
	            }
	            
	            // Leer la siguiente l�nea del fichero original
                linea = br.readLine();
                }
			
			System.out.println("La palabra m�s larga que contiene el fichero es: "+palabraML);
			
		} catch (IOException e) {
			System.out.println("Error al leer el fichero original");
			}
		}
}
	
