package com.ipartek.formacion.migracion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
	
	//nombre fichero a leer
	static final String FILE_PERSONAS = "C:\\0554\\Workspace\\migracion\\src\\main\\resources\\personas.txt";

	//contadores
	static int contlineasLeidas = 0;
	static int contInsert = 0;
	static int cont7Campos = 0;
	static int contEdad = 0;
	
	
	static final int NUMERO_CAMPOS_LINEA = 7;
	static final int CAMPO_NOMBRE = 0;
	static final int CAMPO_APELLIDO1 = 1;
	static final int CAMPO_APELLIDO2 = 2;
	static final int CAMPO_EDAD = 3;
	static final int CAMPO_EMAIL = 4;
	static final int CAMPO_DNI = 5;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Comenzamos migracion...");
		BufferedReader br = null;
		FileReader fr = null;

		try {

		//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILE_PERSONAS);
			br = new BufferedReader(fr);

		//Lectura del fichero linea a linea
			String linea;
			while ((linea = br.readLine()) != null) {
				contlineasLeidas++;
				System.out.println(linea);
				
				String[] aCampos = linea.split(",");
				if(aCampos.length == NUMERO_CAMPOS_LINEA) {
					
		//comprobar edad
					try {
						int edad = Integer.parseInt(aCampos[CAMPO_EDAD]);
						if(edad >= 18) {
							
							//TODO insert en bbdd
							contInsert++;
						}else {
							contEdad++;
						}
					} catch (Exception e) {
						contEdad++;
					}
					
		// NO TENEMOS 7 CAMPOS	
				}else { 
					cont7Campos++;
				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		mostrarResumen();
		
	}
	
	private static void mostrarResumen() {
		System.out.println("Resumen: -------------------");
		System.out.println("Lineas leidas: " + contlineasLeidas);
		System.out.println("Lineas insertadas: " + contInsert);
		System.out.println("Lineas que no tienen 7 campos: " + cont7Campos);
		System.out.println("Lineas menores edad: " + contEdad);
		System.out.println("------------------");
	}


}
