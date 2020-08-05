package com.asan.cms.to;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Set;

@StaticMetamodel(ProductCategory.class)
public class ProductCategory_ {
    public static volatile SingularAttribute<ProductCategory, String> name;
    public static volatile SingularAttribute<ProductCategory, ProductCategory> masterCategory;
    public static volatile SingularAttribute<ProductCategory, Set<ProductCategory>> subCategories;
    public static volatile SingularAttribute<ProductCategory, Set<Product>> products;
}
