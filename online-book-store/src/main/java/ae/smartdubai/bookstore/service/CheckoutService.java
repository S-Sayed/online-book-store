package ae.smartdubai.bookstore.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ae.smartdubai.bookstore.annotation.Log;
import ae.smartdubai.bookstore.exception.InvalidPromotionException;
import ae.smartdubai.bookstore.model.Book;
import ae.smartdubai.bookstore.model.Classification;
import ae.smartdubai.bookstore.model.Promotion;

@Service
public class CheckoutService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutService.class);

	@Autowired
	private PromotionService promotionService;

	@Log
	public BigDecimal calculateTotalPrice(List<Book> books, String promotionCode) {
		LOGGER.info("calculateTotalPrice - No. of books is {} and promotionCode is {}", books.size(), promotionCode);

		if (StringUtils.isBlank(promotionCode)) {
			return sumBookPrices(books);
		} else {
			Promotion promotion = promotionService.getPromotionByCode(promotionCode)
					.orElseThrow(() -> new InvalidPromotionException(promotionCode));

			LOGGER.info("calculateTotalPrice - promotion is {}", promotion);

			if (!(isPromotionCodeActive(promotion))) {
				return sumBookPrices(books);
			}

			Set<Classification> classifications = promotion.getClassifications();
			LOGGER.info("calculateTotalPrice - size of classifications is {}", classifications.size());

			if (classifications == null || classifications.isEmpty()) {
				return sumBookPrices(books);
			}

			BigDecimal totalPrice = new BigDecimal(0);

			outer: for (Book book : books) {
				for (Classification classification : classifications) {
					if (book.getClassification().getCode().equals(classification.getCode())) {

						BigDecimal discountedAmount = calculateDiscountedAmount(book.getPrice(),
								promotion.getPercentage());

						totalPrice = totalPrice.add(book.getPrice().subtract(discountedAmount));
						continue outer;
					}
				}

				totalPrice = totalPrice.add(book.getPrice());
			}

			LOGGER.info("calculateTotalPrice - totalPrice is {}", totalPrice.doubleValue());

			return totalPrice;
		}
	}

	private BigDecimal sumBookPrices(List<Book> books) {
		BigDecimal totalPrice = BigDecimal
				.valueOf(books.stream().mapToDouble(book -> book.getPrice().doubleValue()).sum());

		LOGGER.info("sumBookPrices - total price is {}", totalPrice);

		return totalPrice;
	}

	private boolean isPromotionCodeActive(Promotion promotion) {
		if (promotion == null)
			return false;

		LocalDateTime now = LocalDateTime.now();
		boolean isPromotionCodeActive = promotion.getStartDate().isBefore(now) && now.isBefore(promotion.getEndDate());

		LOGGER.info("isPromotionCodeActive is {}", isPromotionCodeActive);
		return isPromotionCodeActive;
	}

	private BigDecimal calculateDiscountedAmount(BigDecimal price, int percentage) {
		return price.multiply(BigDecimal.valueOf((double) percentage / 100));
	}
}