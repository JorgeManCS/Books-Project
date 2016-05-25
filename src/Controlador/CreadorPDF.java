package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Modelo.Libro;

public class CreadorPDF {
	private static String [] cabecera = {"ISBN", "Title", "Author", "Year", "Publisher"};
	
	//Metodo para añadir la lista a al PDF
	public static void FabricarPdf(List<Libro> lista){
		Document documento = new Document();
		try {
			PdfWriter.getInstance(documento, new FileOutputStream(new File("libros.pdf")));
			documento.open();
			PdfPTable fila = new PdfPTable(5);
			fila.setHeaderRows(1);
			for (String cadena : cabecera) {
				fila.addCell(cadena);
			}
			for (Libro libro : lista) {
				fila.setSpacingAfter(10);
				fila.addCell(libro.getIsbn());
				fila.addCell(libro.getBookTitle());
				fila.addCell(libro.getBookAuthor());
				fila.addCell(libro.getBookYear()+"");
				fila.addCell(libro.getPublisher());
				
			}
			documento.add(fila);
			documento.close();
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
