package com.jpmorgan.tradesettlement.reports;

import java.io.FileNotFoundException;
import java.util.List;

import com.jpmorgan.tradesettlement.domain.ReportData;

public interface ReportGenerator {
	List<ReportData> getReport(String reportDate) throws FileNotFoundException;
}
