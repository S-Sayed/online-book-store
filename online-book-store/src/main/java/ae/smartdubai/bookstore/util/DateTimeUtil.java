package ae.smartdubai.bookstore.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ae.smartdubai.bookstore.constant.DateTimeFormatEnum;

public class DateTimeUtil {
	public static String formatDateTime(LocalDateTime dateTime, DateTimeFormatEnum dateTimeFormatEnum) {
		if (dateTime == null || dateTimeFormatEnum == null)
			return "";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatEnum.getFormat());
		return dateTime.format(formatter);
	}
}