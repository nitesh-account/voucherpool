package com.voucherpool.rest.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
    private Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static String generateUniqueRandomCode(){
        return RandomStringUtils.randomAlphabetic(10);
    }
}
