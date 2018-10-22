package com.jpmorgan.tradesettlement.builder;

import static com.jpmorgan.tradesettlement.Constant.INSTRUCTION_TYPE_BUY;
import static com.jpmorgan.tradesettlement.Constant.INSTRUCTION_TYPE_SELL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmorgan.tradesettlement.domain.InstructionData;
import com.jpmorgan.tradesettlement.domain.ReportData;

public class ReportBuilder {

	private Map<String, ReportData> reporterMap = new HashMap<>();
	
	private ReportBuilder() {
		
	}
	
	public static ReportBuilder getReportBuilder() {
		return new ReportBuilder();
	}
	
	public void buildReport(InstructionData data) {
		
		String mapKey = getMapKey(data.getEntity().toUpperCase(), data.getInstructionDate().toUpperCase());
		
		ReportData report = null;
		if (reporterMap.containsKey(mapKey)) {
			
			report = reporterMap.get(mapKey);
			populateReportAttributes(report, data);			
		} else {
			report = new ReportData();
			populateReportAttributes(report, data);			
		}
		
		reporterMap.put(mapKey, report);
	}

	private void populateReportAttributes(ReportData report,
			InstructionData data) {
		report.setEntity(data.getEntity());
		double amount = getAmountInUSD(data.getSharePrice(), data.getUnits(), data.getFxRate());
		
		if (amount > 0 && data.getInstructionType().equalsIgnoreCase(INSTRUCTION_TYPE_BUY)) {
			report.setOutgoingSettlementAmt(report.getOutgoingSettlementAmt() + amount);
		}
		
		if (amount > 0 && data.getInstructionType().equalsIgnoreCase(INSTRUCTION_TYPE_SELL)) {
			report.setIncomingSettlementAmt(report.getIncomingSettlementAmt() + amount);
		}		
	}

	private String getMapKey(String entity, String instructionDate) {
		return entity + instructionDate;
	}

	public List<ReportData> getReport() {
		return updateRanking(reporterMap.values());
	}
	
	private List<ReportData> updateRanking(Collection<ReportData> report) {
		List<ReportData> sortedData = new ArrayList<>(report);
		Collections.reverse(sortedData);
		long len = sortedData.size();
		for (int i=0; i < len; i++) {
			ReportData d = sortedData.get(i);
			d.setRanking(i+1);
		}
		return sortedData;
	}
	
	private double getAmountInUSD(double sharePrice, int units,
			String fxRate) {
		return sharePrice * units * Double.parseDouble(fxRate);
	}

}
