package ae.smartdubai.bookstore.model;

import java.math.BigDecimal;

public class CheckoutResponse {

	private BigDecimal totalPrice;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
