package ae.smartdubai.bookstore.exception;

public class InvalidPromotionException extends RuntimeException {

	public InvalidPromotionException(String promotionCode) {
		super("invalid promotion code " + promotionCode);
	}
}
