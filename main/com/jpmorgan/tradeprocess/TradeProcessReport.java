/**
 * @author Manjunath Gornal
 * Trade process report is used to generate the report for a specified date.
 * it takes the input in dd MMM yyyy format.
 * if the data is available for input date, programm will print the report.
 */

package com.jpmorgan.tradeprocess;


import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.jpmorgan.tradeprocess.domain.ReportData;
import com.jpmorgan.tradeprocess.service.TradeProcessReportService;
import com.jpmorgan.tradeprocess.service.impl.TradeProcessReportServiceImpl;

public class TradeProcessReport {

	public static void main(String[] args) {

		TradeProcessReport report = new TradeProcessReport();
		try {
			report.generateReport("04 Jan 2016");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<ReportData> generateReport(String reportDate) throws IOException {
		TradeProcessReportService reportService = new TradeProcessReportServiceImpl();
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
