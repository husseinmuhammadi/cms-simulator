package com.asan.cms.to.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Printable {
    Logger LOGGER = LoggerFactory.getLogger(Printable.class);

    default String format() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return String.format("Error to format %s as json object", this.getClass().getName());
    }
}
