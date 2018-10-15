package com.jpmorgan.tradeprocess.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.jpmorgan.tradeprocess.domain.ReportData;

public interface TradeProcessReportService {
	List<ReportData> getReport(String reportDate) throws FileNotFoundException;
}
