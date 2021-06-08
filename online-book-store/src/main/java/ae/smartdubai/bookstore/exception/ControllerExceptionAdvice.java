package ae.smartdubai.bookstore.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ae.smartdubai.bookstore.constant.DateTimeFormatEnum;
import ae.smartdubai.bookstore.util.DateTimeUtil;

@ControllerAdvice
public class ControllerExceptionAdvice {
	private final static Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

	@ExceptionHandler(value = { BookNotFoundException.class, InvalidPromotionException.class })
	public ResponseEntity<ErrorMessage> handleNotFoundException(Exception ex) {
		LOGGER.error("handleNotFoundException - exception: ", ex);

		ErrorMessage errorMeg = new ErrorMessage(HttpStatus.NOT_FOUND.value(),
				DateTimeUtil.formatDateTime(LocalDateTime.now(), DateTimeFormatEnum.YYYY_MM_DD_HH_MM_SS),
				ex.getMessage());

		return new ResponseEntity<ErrorMessage>(errorMeg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	public ResponseEntity<ErrorMessage> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
		LOGGER.error("handleEmptyResultDataAccessException - exception: ", ex);

		ErrorMessage errorMeg = new ErrorMessage(HttpStatus.NOT_FOUND.value(),
				DateTimeUtil.formatDateTime(LocalDateTime.now(), DateTimeFormatEnum.YYYY_MM_DD_HH_MM_SS),
				"book not found");

		return new ResponseEntity<ErrorMessage>(errorMeg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { BadRequestException.class })
	public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException ex) {
		LOGGER.error("handleBadRequestException - exception: ", ex);

		ErrorMessage errorMeg = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
				DateTimeUtil.formatDateTime(LocalDateTime.now(), DateTimeFormatEnum.YYYY_MM_DD_HH_MM_SS),
				ex.getMessage());

		return new ResponseEntity<ErrorMessage>(errorMeg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
		LOGGER.error("handleGeneralException - exception: ", ex);

		ErrorMessage errorMeg = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				DateTimeUtil.formatDateTime(LocalDateTime.now(), DateTimeFormatEnum.YYYY_MM_DD_HH_MM_SS),
				"Something went wrong");

		return new ResponseEntity<ErrorMessage>(errorMeg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}