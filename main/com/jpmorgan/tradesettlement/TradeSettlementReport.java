/**
 * @author Manjunath Gornal
 * Trade process report is used to generate the report for a specified date.
 * it takes the input in dd MMM yyyy format.
 * if the data is available for input date, programm will print the report.
 */

package com.jpmorgan.tradesettlement;


import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.jpmorgan.tradesettlement.domain.ReportData;
import com.jpmorgan.tradesettlement.reports.ReportGenerator;
import com.jpmorgan.tradesettlement.reports.impl.ReportGeneratorImpl;

public class TradeSettlementReport {

	public static void main(String[] args) {

		TradeSettlementReport report = new TradeSettlementReport();
		try {
			report.generateReport("04 Jan 2016");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<ReportData> generateReport(String reportDate) throws IOException {
		ReportGenerator reportService = new ReportGeneratorImpl();
		List<ReportData> reportData = null;
		if (reportDate != null && !reportDate.isEmpty()) {
			reportData = reportService.getReport(reportDate);
				
		} else {
			System.out.println("Invalid input date. please provide valid date in the format - dd MMM yyy. example - 01 Jan 2018.");
		}
		printReport(reportData);
		return reportData;
	}
	
	private void printReport(Collection<ReportData> reportData) {
		if (reportData != null && reportData.isEmpty()) {
			System.out.println("No data found");
		} else if (reportData != null){
			System.out.println(reportData);
		}
	}
}
