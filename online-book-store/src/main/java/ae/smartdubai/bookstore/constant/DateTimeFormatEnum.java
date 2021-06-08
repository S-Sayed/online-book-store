package ae.smartdubai.bookstore.constant;

public enum DateTimeFormatEnum {

	YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss");

	String format = "";

	DateTimeFormatEnum(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}
}