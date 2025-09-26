package ejercicios;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {
	
	public static void main(String[] args) {
		//Creación de rutas
		String ficheroE = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "entrada.txt";
		String ficheroS = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "salida.txt";
		 
		//Creación de objetos
		FileReader fileR= null;
		FileWriter fileW = null;
		
		//Array de caracteres
		char[] array = new char[1024];
		
		//Creación de variable contador necesaria
		int con = 0;
		
		try {
			//Abriendo ficheros
			fileR = new FileReader(ficheroE);
			fileW = new FileWriter(ficheroS);
			
			//Creación variable para caracteres del texto
			int letra;
			
			//Lectura de fichero y guardado en array letra a letra
			while ((letra = fileR.read()) != -1 && con < array.length ) {
				array[con] = (char) letra;
				con++;
			}
			//Pase del array, de atrás adelante, a fichero de salida
			for (int i = con - 1; i >= 0; i--) {
				fileW.write(array[i]);
			}
		} catch (IOException e) {
            // Mostrar el error de excepción
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Cerrar recursos en bloque finally
            try {
                if (fileR != null) {
                    fileR.close();
                }
                if (fileW != null) {
                    fileW.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
		}
	}
}
