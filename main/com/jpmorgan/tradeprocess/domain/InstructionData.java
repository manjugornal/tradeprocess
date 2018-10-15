package com.jpmorgan.tradeprocess.domain;

public class InstructionData {
	private String entity;
	private String instructionType;
	private String fxRate;
	private String currencyCode;
	private String instructionDate;
	private String settlementDate;
	private int units;
	private double sharePrice;
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getInstructionType() {
		return instructionType;
	}
	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}
	public String getFxRate() {
		return fxRate;
	}
	public void setFxRate(String fxRate) {
		this.fxRate = fxRate;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(String instructionDate) {
		this.instructionDate = instructionDate;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}
	
	@Override
	public String toString() {
		return "InstructionData [entity=" + entity + ", instructionType="
				+ instructionType + ", fxRate=" + fxRate + ", currencyCode="
				+ currencyCode + ", instructionDate=" + instructionDate
				+ ", settlementDate=" + settlementDate + ", units=" + units
				+ ", sharePrice=" + sharePrice + "]";
	}
}
