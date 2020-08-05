package com.asan.cms.to;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(name = "SEQUENCE_GENERATOR", sequenceName = "PRODUCT_SEQ")
public class Product extends EntityBase {

    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "BARCODE")
    private String code;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "CURRENCY")
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY_PRODUCT"))
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "manufacturer", foreignKey = @ForeignKey(name = "FK_COMPANY_PRODUCT"))
    private Company manufacturer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Company getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Company manufacturer) {
        this.manufacturer = manufacturer;
    }
}
