package com.asan.cms.to;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

@Entity
@Table(name = "PRODUCT_CATEGORY", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_PRODUCT_CATEGORY_NAME", columnNames = {"NAME"})
})
@SequenceGenerator(name = "SEQUENCE_GENERATOR", sequenceName = "PRODUCT_CATEGORY_SEQ")
public class ProductCategory extends EntityBase {

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    @Column(name = "name", length = 200)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MASTER_CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY_PRODUCT_CATEGORY"))
    private ProductCategory masterCategory;

    @OneToMany(mappedBy = "masterCategory", fetch = FetchType.EAGER)
    private Set<ProductCategory> subCategories = new HashSet<>();

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public ProductCategory getMasterCategory() {
        return masterCategory;
    }

    public void setMasterCategory(ProductCategory masterCategory) {
        this.masterCategory = masterCategory;
    }

    public Set<ProductCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<ProductCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
