package ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio4b {

	// Crear variables estáticas útiles
	static String dni = "";
	static int peso;
	static long posicion = 4;
	// Crear variable para lectura por consola
	static Scanner scanner = new Scanner(System.in);
	// Crear variable para lectura de fichero

	public static void main(String[] args) {

		int pesoAnterior;
		int diferencia;
		String heroe;

		// Crear el fichero aleatorio Marvel.dat
		try (RandomAccessFile raf = new RandomAccessFile(
				"." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "Marvel.dat", "rw")) {

			// Solicitar entrada de DNI hasta que sea correcto
			peticionDNI();

			// Solicitar entrada de peso hasta que sea correcta
			peticionPeso();

			// Buscar el DNI en el fichero aleatorio
			buscarxDNI(dni, raf);

			// Recuperar nombre heroe
			heroe = (recuperarNombre(posicion, raf)).strip();

			// Recuperar peso
			pesoAnterior = recuperarPeso(posicion, raf);

			// Calcular nuevo peso y devolver situacion
			diferencia = calculoPeso(peso, pesoAnterior);

			// Salida por consola final
			if (diferencia > 0) {
				System.out.println(heroe + " ha engordado " + diferencia + " kilos.");
			} else if (diferencia < 0) {
				System.out.println(heroe + " ha adelgazado " + diferencia + " kilos.");
			} else
				System.out.println(heroe + " se mantiene en su peso anterior.");

			// Modificar fichero aleatorio con el nuevo peso
			insertarPeso(peso, posicion, raf);

		} catch (IOException e) {
			System.err.println("Error al leer el fichero: " + e.getMessage());
		}
	}

	private static String recuperarNombre(long posicion, RandomAccessFile raf) throws IOException {
		String nombre = "";
		char letra;
		raf.seek(posicion);
		for (int i = 0; i < 10; i++) {
			try {
				letra = raf.readChar();
				nombre = nombre + letra;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		posicion = raf.getFilePointer();
		return nombre;
	}

	private static void insertarPeso(int peso2, long posicion, RandomAccessFile raf) {
		try {
			raf.seek(posicion + 80);
			raf.writeInt(peso2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int calculoPeso(int peso, int pesoAnterior) {
		return peso - pesoAnterior;
	}

	private static int recuperarPeso(long posicion, RandomAccessFile raf) throws IOException {
		int pesoAnterior = 0;
		raf.seek(posicion + 80);

		try {
			pesoAnterior = raf.readInt();

			return pesoAnterior;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	private static void buscarxDNI(String dni2, RandomAccessFile raf) {
		String dniFichero = "";
		char letra;

		// Buscar en el fichero la String recibida saltando el id (4bytes)
		try {
			raf.seek(4);

			while (raf.getFilePointer() < raf.length()) {

				for (int i = 0; i < 9; i++) {
					letra = raf.readChar();
					dniFichero = dniFichero + letra;
				}
				if (dniFichero.equals(dni2)) {
					raf.seek(posicion + 18);
					posicion = raf.getFilePointer();
					break;
				} else
					raf.seek(posicion + 110);
				dniFichero = "";
				posicion = raf.getFilePointer();
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("El DNI no existe");
		}
	}

	private static void peticionPeso() {
		do {
			System.out.println("Introduzca su peso");
			peso = scanner.nextInt();
		} while (peso <= 0);
		scanner.close();
	}

	private static void peticionDNI() {
		do {
			System.out.println("Introduzca el DNI (con letra) del personaje para el control de peso:");
			dni = scanner.nextLine();
		} while (!validarDNI(dni));
	}

	private static boolean validarDNI(String dni) {
		// Crear variables necesarias
		char letra = dni.charAt(dni.length() - 1);
		String numerosStr = dni.substring(0, 8);

		// 1. Comprobar longitud del DNI
		if (dni == null || dni.length() != 9) {
			System.out.println("El DNI introducido no contiene 9 caracteres.");
			return false;
		}

		// 2. Validar que los 8 primeros caracteres sean numéricos
		if (!numerosStr.matches("\\d{8}")) {
			System.out.println("Los primeros 8 digitos deben ser números.");
			return false;
		}
		// Validar que el último char de la String sea letra mayúscula
		if (!Character.isUpperCase(letra)) {
			System.out.println("El DNI debe estar compuesto por 8 números y una letra mayúscula.");
		}
		return Character.isUpperCase(letra);
	}
}
