package com.asan.cms.specification;

import org.springframework.util.Assert;

abstract class Specifications {
    static String startWithPattern(final String searchTerm) {
        Assert.isTrue(searchTerm != null && !searchTerm.isEmpty(), () -> "Search term could not be empty");
        return (searchTerm + "%").toLowerCase();
    }

    static String likePattern(final String searchTerm) {
        Assert.isTrue(searchTerm != null && !searchTerm.isEmpty(), () -> "Search term could not be empty");
        return ("%" + searchTerm + "%").toLowerCase();
    }
}
