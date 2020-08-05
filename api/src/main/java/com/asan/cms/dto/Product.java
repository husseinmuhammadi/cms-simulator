package com.asan.cms.dto;

public class Product extends DtoBase {

    private String productName;

    private String productCode;

    private Double amount;

    private String currency;

    private Company manufacturer;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Company getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Company manufacturer) {
        this.manufacturer = manufacturer;
    }
}
