package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.opencsv.CSVReader;

import Modelo.Libro;

public class ImportacionCsv {
	public static List<Libro> registros (File inFile){
		String[] linea = new String[5];
		CSVReader csvReader;
		List<Libro> lista = new ArrayList<>();
		
		
		try {
			csvReader = new CSVReader(new FileReader(inFile));
			linea = csvReader.readNext();
			
			while(linea != null){
			lista.add(new Libro(linea[0], linea[1], linea[2], Integer.parseInt(linea[3]), linea[4]));
			linea = csvReader.readNext();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public static DefaultTableModel tablaRegistros(String[] cabecera, List<Libro> lista){
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(cabecera);
		String[] datos = new String [cabecera.length];
		
		for (int i = 0; i < lista.size(); i++) {
			datos[0] = lista.get(i).getIsbn();
			datos[1] = lista.get(i).getBookTitle();
			datos[2] = lista.get(i).getBookAuthor();
			datos[3] = lista.get(i).getBookYear()+"";
			datos[4] = lista.get(i).getPublisher();
			dtm.addRow(datos);
		}
		return dtm;
		
	}
	public static DefaultTableModel eliminarRegistro(String[] cabecera, List<Libro> lista, int fila){
		lista.remove(fila);
		return tablaRegistros(cabecera, lista);
	}
	
}
