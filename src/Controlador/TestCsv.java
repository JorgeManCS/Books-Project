package Controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Modelo.Libro;

public class TestCsv {
	public static void main(String[] args) {
		File inFile = new File("resources\\books.csv");
		String[] linea;
		
		List<Libro> lista = new ArrayList<>();
		lista = ImportacionCsv.registros(inFile);
		
		System.out.println(lista.get(1).getIsbn());
		
	
	}

}
