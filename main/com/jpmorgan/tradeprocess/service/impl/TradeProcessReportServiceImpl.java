package com.jpmorgan.tradeprocess.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.jpmorgan.tradeprocess.InstructionReader;
import com.jpmorgan.tradeprocess.builder.ReportBuilder;
import com.jpmorgan.tradeprocess.domain.InstructionData;
import com.jpmorgan.tradeprocess.domain.ReportData;
import com.jpmorgan.tradeprocess.service.TradeProcessReportService;
import com.jpmorgan.tradeprocess.util.ReporterUtil;

public class TradeProcessReportServiceImpl implements TradeProcessReportService {

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

	private void updateSettlementDate(InstructionData instructionData) {
		ReporterUtil.updateNextSettlementDate(instructionData);
	}

}
