package com.jpmorgan.tradeprocess.util;

import static com.jpmorgan.tradeprocess.Constant.CURR_CODE_AED;
import static com.jpmorgan.tradeprocess.Constant.CURR_CODE_SAR;
import static com.jpmorgan.tradeprocess.Constant.REPORT_DATE_FORMAT;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jpmorgan.tradeprocess.domain.InstructionData;

public class ReporterUtil {

	public static double getAmountInUSD(double sharePrice, int units,
			String fxRate) {
		return sharePrice * units * Double.parseDouble(fxRate);
	}

	public static void updateNextSettlementDate(InstructionData instructionData) {
		String currencyCode = instructionData.getCurrencyCode();

		String settlementDate = instructionData.getSettlementDate();
		DateFormat sdf = new SimpleDateFormat(REPORT_DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(settlementDate);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		switch (dayOfWeek) {
		case Calendar.FRIDAY:
			if (currencyCode != null
					&& (CURR_CODE_AED.equalsIgnoreCase(currencyCode) || CURR_CODE_SAR
							.equalsIgnoreCase(currencyCode))) {
				cal.add(Calendar.DAY_OF_WEEK, 3);
			}
			break;
		case Calendar.SATURDAY:
			cal.add(Calendar.DAY_OF_WEEK, 2);
			break;
		case Calendar.SUNDAY:
			cal.add(Calendar.DAY_OF_WEEK, 1);
			break;

		}

		instructionData.setSettlementDate(sdf.format(cal.getTime()));
	}

}
