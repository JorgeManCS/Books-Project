package Modelo;

/**
 * @author JorgeManuel
 *
 */
public class Libro {
	private String isbn;
	private String bookTitle;
	private String bookAuthor;
	private int bookYear;
	private String publisher;
	//private String imageurl;
	/**
	 * @param isbn
	 * @param bookTitle
	 * @param bookAuthor
	 * @param bookYear
	 * @param publisher
	 */
	public Libro(String isbn, String bookTitle, String bookAuthor, int bookYear, String publisher/*, String imageurl*/) {
		
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookYear = bookYear;
		this.publisher = publisher;
		//this.imageurl = imageurl;
	}
	
	//Libro por defecto que se muestra cuando 
	//se añade un libro nuevo a la tabla
	public Libro(){
		this.isbn = "";
		this.bookTitle = "";
		this.bookAuthor = "";
		this.bookYear = 0;
		this.publisher = "";
	}
	
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public int getBookYear() {
		return bookYear;
	}
	public String getPublisher() {
		return publisher;
	}
	/*
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}*/
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	public void setBookAuthor(String bookAutor) {
		this.bookAuthor = bookAutor;
	}
	
	public void setBookYear(int bookYear) {
		this.bookYear = bookYear;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/*@Override
	public String toString() {
		return isbn + bookTitle + bookAuthor + bookYear + Publisher ;
	}*/
	
	
	
}
