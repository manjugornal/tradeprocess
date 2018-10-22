package com.jpmorgan.tradesettlement.helper;

import static com.jpmorgan.tradesettlement.Constant.CURR_CODE_AED;
import static com.jpmorgan.tradesettlement.Constant.CURR_CODE_SAR;
import static com.jpmorgan.tradesettlement.Constant.REPORT_DATE_FORMAT;

import java.time.LocalDate;

import com.jpmorgan.tradesettlement.domain.InstructionData;

public class SettlementDateHelper {

	/** next working day will be calculated based on the currency code. 
	 * 
	 * @param instructionData
	 */
	public static void calculateNextSettlementDate(InstructionData instructionData) {
		String currencyCode = instructionData.getCurrencyCode();
		
		

		String settlementDate = instructionData.getSettlementDate();
		LocalDate date = LocalDate.parse(settlementDate, REPORT_DATE_FORMAT);

		switch (date.getDayOfWeek()) {
		case FRIDAY:
			if (currencyCode != null
					&& (CURR_CODE_AED.equalsIgnoreCase(currencyCode) || CURR_CODE_SAR
							.equalsIgnoreCase(currencyCode))) {
				date = date.plusDays(3);
			}
			break;
		case SATURDAY:
			date = date.plusDays(2);
			break;
		case SUNDAY:
			date = date.plusDays(1);
			break;
		default:
			break;

		}

		instructionData.setSettlementDate(date.format(REPORT_DATE_FORMAT).toString());
	}

}
