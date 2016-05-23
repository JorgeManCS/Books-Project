package Modelo;

public class Libro {
	private String isbn;
	private String bookTitle;
	private String bookAutor;
	private int bookYear;
	private String Publisher;
	public Libro(String isbn, String bookTitle, String bookAutor, int bookYear, String publisher) {
		super();
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.bookAutor = bookAutor;
		this.bookYear = bookYear;
		Publisher = publisher;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAutor() {
		return bookAutor;
	}
	public void setBookAutor(String bookAutor) {
		this.bookAutor = bookAutor;
	}
	public int getBookYear() {
		return bookYear;
	}
	public void setBookYear(int bookYear) {
		this.bookYear = bookYear;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	@Override
	public String toString() {
		return isbn + bookTitle + bookAutor + bookYear + Publisher ;
	}
	
}
