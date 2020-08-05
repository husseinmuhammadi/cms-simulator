package com.asan.cms.service;

import com.asan.cms.mapper.ProductMapper;
import com.asan.cms.to.Product;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapperTest.class);

    @Test
    public void testMapDtoFromEntity() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Christian");
        Date now = current();
        product.getAudit().setCreatedOn(now);
        com.asan.cms.dto.Product productDto = ProductMapper.MAPPER.fromProduct(product);
        assertThat(productDto.getId()).isEqualTo(product.getId());
        assertThat(productDto.getProductName()).isEqualTo(product.getName());
//        assertThat(productDto.getAuditDto().getCreatedOn())
//                .isEqualTo(product.getAudit().getCreatedOn());
    }

    @Test
    public void testMapDtoToEntity() {
        com.asan.cms.dto.Product productDto = new com.asan.cms.dto.Product();
        productDto.setId(1L);
        productDto.setProductName("Christian");
        productDto.getAudit().setCreatedOn(current());
        Product product = ProductMapper.MAPPER.toProduct(productDto);
        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.getName()).isEqualTo(productDto.getProductName());
//        assertThat(product.getAuditDto().getCreatedOn())
//                .isEqualTo(productDto.getAudit().getCreatedOn());
    }

    private Date current() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}