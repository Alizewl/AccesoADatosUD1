package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio4c {

	// Creacion de variables estaticas necesarias
	static long posicion = 82;
	// Inicializar el array
	static String[] personaje = new String[6];

	public static void main(String[] args) {
		// Creación de variables necesarias
		String tipo;

		// Crear el fichero aleatorio Marvel.dat
		try (RandomAccessFile raf = new RandomAccessFile(
				"." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "Marvel.dat", "rw")) {

			// Petición por consola de tipo de personaje
			Scanner scanner = new Scanner(System.in);
			System.out.print("Introduce un tipo de personaje: ");
			tipo = scanner.nextLine();

			// Busqueda en fichero del tipo indicado
			buscarTipo(tipo, raf);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void buscarTipo(String tipo, RandomAccessFile raf) {
		String nombre = "";
		char letra;
		int num;

		try {
			raf.seek(posicion);
			while (raf.getFilePointer() < raf.length()) {
				for (int i = 0; i < 10; i++) {
					letra = raf.readChar();
					nombre = nombre + letra;
				}

				if (nombre.strip().equalsIgnoreCase(tipo.strip())) {
					raf.seek(posicion - 78);
					nombre = "";
					for (int i = 0; i < 9; i++) {
						letra = raf.readChar();
						nombre = nombre + letra;
					}
					nombre = "dni: " + nombre + " , ";
					personaje[0] = nombre;

					nombre = "";
					for (int i = 0; i < 10; i++) {
						letra = raf.readChar();
						nombre = nombre + letra;
					}
					nombre = "nombre: " + nombre + " , ";
					personaje[1] = nombre;

					nombre = "";
					for (int i = 0; i < 20; i++) {
						letra = raf.readChar();
						nombre = nombre + letra;
					}
					nombre = "identidad: " + nombre + " , ";
					personaje[2] = nombre;

					nombre = "";
					for (int i = 0; i < 10; i++) {
						letra = raf.readChar();
						nombre = nombre + letra;
					}
					nombre = "tipo: " + nombre + " , ";
					personaje[3] = nombre;

					nombre = "";

					num = raf.readInt();
					nombre = "peso: " + num + " , ";
					personaje[4] = nombre;

					nombre = "";

					num = raf.readInt();
					nombre = "altura: " + num;
					personaje[5] = nombre;

					// Imprimir los datos del personaje
					System.out.print("Personaje: [");
					for (String dato : personaje) {
						System.out.print(dato);
					}
					System.out.println("]");
					raf.seek(posicion + 110);

					posicion = raf.getFilePointer();
				} 
				
			}
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}