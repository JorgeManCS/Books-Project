package Modelo;

public class Libro {
	private String isbn;
	private String bookTitle;
	private String bookAuthor;
	private int bookYear;
	private String publisher;
	public Libro(String isbn, String bookTitle, String bookAuthor, int bookYear, String publisher) {
		
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookYear = bookYear;
		this.publisher = publisher;
	}
	public void setLibro (Libro libro){
		this.isbn = libro.isbn;
		this.bookTitle = libro.bookTitle;
		this.bookAuthor = libro.bookAuthor;
		this.bookYear = libro.bookYear;
		this.publisher = libro.publisher;
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
