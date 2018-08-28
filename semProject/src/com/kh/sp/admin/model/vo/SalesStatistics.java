package com.kh.sp.admin.model.vo;

import java.sql.Date;

public class SalesStatistics {
    private String term;
    private int paymentCount;
    private int paymentPrice;
    private int refundCount;
    private int paymentPercentage;
    private int paymentCompletePrice;
    private int netSales;
    
    public SalesStatistics(){}

	public SalesStatistics(String term, int paymentCount, int paymentPrice, int refundCount, int paymentPercentage,
			int paymentCompletePrice, int netSales) {
		super();
		this.term = term;
		this.paymentCount = paymentCount;
		this.paymentPrice = paymentPrice;
		this.refundCount = refundCount;
		this.paymentPercentage = paymentPercentage;
		this.paymentCompletePrice = paymentCompletePrice;
		this.netSales = netSales;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(int paymentCount) {
		this.paymentCount = paymentCount;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public int getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(int refundCount) {
		this.refundCount = refundCount;
	}

	public int getPaymentPercentage() {
		return paymentPercentage;
	}

	public void setPaymentPercentage(int paymentPercentage) {
		this.paymentPercentage = paymentPercentage;
	}

	public int getPaymentCompletePrice() {
		return paymentCompletePrice;
	}

	public void setPaymentCompletePrice(int paymentCompletePrice) {
		this.paymentCompletePrice = paymentCompletePrice;
	}

	public int getNetSales() {
		return netSales;
	}

	public void setNetSales(int netSales) {
		this.netSales = netSales;
	}

	@Override
	public String toString() {
		return "SalesStatistics [term=" + term + ", paymentCount=" + paymentCount + ", paymentPrice=" + paymentPrice
				+ ", refundCount=" + refundCount + ", paymentPercentage=" + paymentPercentage
				+ ", paymentCompletePrice=" + paymentCompletePrice + ", netSales=" + netSales + "]";
	}
    
	
    
}
