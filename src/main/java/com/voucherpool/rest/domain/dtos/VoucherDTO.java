package com.voucherpool.rest.domain.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherDTO {
    private Logger logger = LoggerFactory.getLogger(VoucherDTO.class);

    private String voucherCode;
    private String offerId;
    private String userId;
    private Long expiresAt;

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getOfferId() {
        return offerId;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VoucherDTO{" +
                "voucherCode='" + voucherCode + '\'' +
                ", offerId='" + offerId + '\'' +
                ", userId='" + userId + '\'' +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
