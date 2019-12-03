package com.voucherpool.rest.domain.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateVoucherResDTO {
    private Logger logger = LoggerFactory.getLogger(ValidateVoucherResDTO.class);

    private Long percentageDiscount;

    public Long getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Long percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public String toString() {
        return "ValidateVoucherResDTO{" +
                "percentageDiscount=" + percentageDiscount +
                '}';
    }
}
