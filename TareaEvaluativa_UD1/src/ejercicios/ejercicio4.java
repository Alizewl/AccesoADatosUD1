package ejercicios;

import java.io.*;

public class ejercicio4 {

	public static void main(String[] args) {
		// Crear el fichero aleatorio Marvel.dat
        try (RandomAccessFile raf = new RandomAccessFile("." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "Marvel.dat", "rw")) {
        	
        	// Crear el array con los datos de los personajes
            int [] ids= {1, 2, 3, 4, 5, 6, 7};
            String[] dnis= {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
        	String[] noms= {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
        	String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
        	String[] tipos = {"heroe","villano","heroe","heroe","villano","heroe","villano"};
        	int[] pesos = {76,84,66,136,78,102,70};
        	int[] alturas = {178,183,156,152,177,182,188};
        	
        	// Recorrer el array con un bucle
            for (int i = 0; i < ids.length; i++) {
                // Obtener los datos de un personaje del array
                int id = ids[i]; //( Ocupará 1int=4 bytes)
                String dni = dnis[i]; //(2*9=18 bytes)
                String nombre = noms[i]; //(2*10=20 bytes)
                String identidad = identidades[i]; //(2*20=40 bytes)
                String tipo = tipos[i]; //(2*10=20 bytes)
                int peso = pesos[i];  //(4 bytes)
                int altura = alturas[i]; //(4 bytes)
                
                // Escribir datos en el fichero aleatorio, clave primaria = ID
                raf.seek((id - 1) * 110); // Total por registro = 110 bytes
                raf.writeInt(id); 
                raf.writeChars(String.format("%-9s", dni)); 
                raf.writeChars(String.format("%-10s", nombre)); 
                raf.writeChars(String.format("%-20s", identidad)); 
                raf.writeChars(String.format("%-10s", tipo)); 
                raf.writeInt(peso); 
                raf.writeInt(altura);
                }
            
            // Mostrar un mensaje de éxito
            System.out.println("La carga de datos se ha realizado satisfactoriamente.");
        } catch (IOException e) {
            // Mostrar un mensaje de error
            System.out.println("Ha ocurrido un error al crear o escribir en el fichero Marvel.dat.");
        }
	}

}
