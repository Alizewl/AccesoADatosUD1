package ejercicios;

import java.io.*;

public class Ejercicio3 {

	public static void main(String[] args) {
		// Verificamos si se pasó al menos un argumento
        if (args.length > 0) {
            // Accedemos al primer argumento
            String argumento = args[0];
            
            //Ruta relativa usando el campo separator.
        	File archivoPDF = new File("."+ File.separator + "src" + File.separator + "ejercicios"+ File.separator + argumento);

        	if(archivoPDF.exists()){
        		try (FileInputStream fis = new FileInputStream(archivoPDF)) {
        			// Leer los primeros 5 bytes
                    byte[] firmaMagica = new byte[5]; 
                    if (fis.read(firmaMagica) != -1) {
                        String firma = new String(firmaMagica);
                        if (firma.equals("%PDF-")) {
                            System.out.println("El archivo es un PDF válido.");
                        } else {
                            System.out.println("El archivo no tiene la firma mágica de un PDF.");
                            }
                        }
                    } catch (IOException e) {
                    	System.err.println("Error al leer el archivo: " + e.getMessage());
                    	}
        		
        	} else {
        		System.out.println("El archivo no existe con ese nombre");
        		}
        } else {
            System.out.println("No se ha pasado el nombre del fichero.");
        }
    }
}
