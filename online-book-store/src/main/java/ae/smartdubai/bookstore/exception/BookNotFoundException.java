package ae.smartdubai.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String isbn) {
		super("No book found for ISBN " + isbn);
	}
}
