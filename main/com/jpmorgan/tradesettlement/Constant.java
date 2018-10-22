package com.jpmorgan.tradesettlement;

import java.time.format.DateTimeFormatter;

public class Constant {

	public final static String FILE_DELIMITER = "~";
	public final static String INSTRUCTION_TYPE_BUY = "B";
	public final static String INSTRUCTION_TYPE_SELL = "S";
	public final static String CURR_CODE_SGP = "SGP";
	public final static String CURR_CODE_AED = "AED";
	public final static String CURR_CODE_SAR = "SAR";
	public final static DateTimeFormatter REPORT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");
	

}
