package ae.smartdubai.bookstore.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException() {
		super("Invalid request");
	}
}
