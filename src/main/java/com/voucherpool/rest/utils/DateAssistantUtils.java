package com.voucherpool.rest.utils;

import java.time.Instant;

/**
 * DateAssistantUtils class is used for date related common method
 *
 * @author Nitesh Kumar
 */
public class DateAssistantUtils {

    public static Long setDate() {
        return Instant.now().getEpochSecond();
    }

    public static boolean checkExpiryDate(Long expirationDate) {
        Long currentDate = Instant.now().getEpochSecond();
        long difference = expirationDate - currentDate;
        if (difference > 0)
            return false;
        return true;
    }
}
