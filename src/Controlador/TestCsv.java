package Controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Modelo.Libro;

/**
 * @author JorgeManuel
 *Clase para comporvar que efectivamente cargaba el fichero CSV
 */
public class TestCsv {
	public static void main(String[] args) {
		File inFile = new File("resources\\books.csv");
		String[] linea;
		
		List<Libro> lista = new ArrayList<>();
		lista = ImportacionCsv.registros(inFile);
		
		System.out.println(lista.get(1).getIsbn());
		
	
	}

}
