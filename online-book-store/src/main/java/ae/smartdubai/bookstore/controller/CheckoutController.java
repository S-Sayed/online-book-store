package ae.smartdubai.bookstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ae.smartdubai.bookstore.annotation.Log;
import ae.smartdubai.bookstore.model.Book;
import ae.smartdubai.bookstore.model.CheckoutResponse;
import ae.smartdubai.bookstore.service.CheckoutService;

@RestController
@CrossOrigin
@RequestMapping("/v1/checkout")
public class CheckoutController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutController.class);

	@Autowired
	private CheckoutService checkoutService;

	@PostMapping(value = "/order", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@Log
	public CheckoutResponse calculateTotalPrice(@RequestBody(required = true) List<Book> books,
			@RequestParam(required = false) String promotionCode) {
		LOGGER.info("calculateTotalPrice - promotionCode <{}>, books <{}>", promotionCode, books);

		CheckoutResponse response = new CheckoutResponse();
		response.setTotalPrice(checkoutService.calculateTotalPrice(books, promotionCode));
		return response;
	}
}
