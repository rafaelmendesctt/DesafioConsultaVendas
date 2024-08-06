package com.devsuperior.dsmeta.dto;

public class SaleSummaryDTO {

    private String sellerName;
    private Double amount;

    public SaleSummaryDTO() {
    }

    public SaleSummaryDTO(String sellerName, Double amount) {
        this.sellerName = sellerName;
        this.amount = amount;
    }
    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return amount;
    }
}
