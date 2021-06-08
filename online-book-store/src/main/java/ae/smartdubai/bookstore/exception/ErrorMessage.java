package ae.smartdubai.bookstore.exception;

public class ErrorMessage {
	private int status;
	private String occuredAt;
	private String message;

	public ErrorMessage(int status, String occuredAt, String message) {
		this.status = status;
		this.occuredAt = occuredAt;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getOccuredAt() {
		return occuredAt;
	}

	public String getMessage() {
		return message;
	}
}
