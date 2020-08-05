package com.asan.cms.to;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public class Product_ {
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> code;
    public static volatile SingularAttribute<Product, Double> amount;
    public static volatile SingularAttribute<Product, String> currency;
    public static volatile SingularAttribute<Product, ProductCategory> category;
    public static volatile SingularAttribute<Product, Company> manufacturer;
}
