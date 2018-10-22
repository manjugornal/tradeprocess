package com.jpmorgan.tradesettlement.reports.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.jpmorgan.tradesettlement.InstructionReader;
import com.jpmorgan.tradesettlement.builder.ReportBuilder;
import com.jpmorgan.tradesettlement.domain.InstructionData;
import com.jpmorgan.tradesettlement.domain.ReportData;
import com.jpmorgan.tradesettlement.helper.SettlementDateHelper;
import com.jpmorgan.tradesettlement.reports.ReportGenerator;

public class ReportGeneratorImpl implements ReportGenerator {

	/**
	 * each instructions are processed and if the instruction settlement date is same as input date, then record will be processed for report generation. 
	 */
	@Override
	public List<ReportData> getReport(String reportDate) throws FileNotFoundException {
		InstructionReader reader = new InstructionReader();
		BufferedReader bufferReader = reader.getInstructionReader();

		String record;
		ReportBuilder builder = ReportBuilder.getReportBuilder();

		try {
			while ((record = bufferReader.readLine()) != null) {
				InstructionData data = reader.processInstructions(record);
				updateSettlementDate(data);
				if (reportDate.equalsIgnoreCase(data.getSettlementDate())) {
					builder.buildReport(data);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return builder.getReport();
	}

	/**
	 * if the settlement date falls on non-working days, it will be updated to next working day.
	 * @param instructionData
	 */
	private void updateSettlementDate(InstructionData instructionData) {
		SettlementDateHelper.calculateNextSettlementDate(instructionData);
	}

}
