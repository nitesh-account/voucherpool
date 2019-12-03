package com.voucherpool.rest.domain.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateVoucherReqDTO {
    private Logger logger = LoggerFactory.getLogger(ValidateVoucherReqDTO.class);

    private String voucherCode;
    private String email;

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ValidateVoucherReqDTO{" +
                "voucherCode='" + voucherCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
