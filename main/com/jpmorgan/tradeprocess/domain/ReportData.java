package com.jpmorgan.tradeprocess.domain;

public class ReportData implements Comparable {

	private String entity;
	private double incomingSettlementAmt;
	private double outgoingSettlementAmt;
	private int ranking;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public double getIncomingSettlementAmt() {
		return incomingSettlementAmt;
	}

	public void setIncomingSettlementAmt(double incomingSettlementAmt) {
		this.incomingSettlementAmt = incomingSettlementAmt;
	}

	public double getOutgoingSettlementAmt() {
		return outgoingSettlementAmt;
	}

	public void setOutgoingSettlementAmt(double outgoingSettlementAmt) {
		this.outgoingSettlementAmt = outgoingSettlementAmt;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "ReportData [entity=" + entity + ", incomingSettlementAmt="
				+ incomingSettlementAmt + ", outgoingSettlementAmt="
				+ outgoingSettlementAmt + ", ranking=" + ranking + "]";
	}

	@Override
	public int compareTo(Object o) {
		double amount = ((ReportData) o).getOutgoingSettlementAmt();
		
		if (this.incomingSettlementAmt > amount) {
			return 1;
		} else if (this.incomingSettlementAmt < amount) {
			return -1;
		} else {
			return 0;
		}
	}
}
