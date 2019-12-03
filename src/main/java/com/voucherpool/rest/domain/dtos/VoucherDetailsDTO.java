package com.voucherpool.rest.domain.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VoucherDetailsDTO {
    private Logger logger = LoggerFactory.getLogger(VoucherDetailsDTO.class);

    private List<VoucherDTO> voucher;

    public List<VoucherDTO> getVoucher() {
        return voucher;
    }

    public void setVoucher(List<VoucherDTO> voucher) {
        this.voucher = voucher;
    }

    @Override
    public String toString() {
        return "VoucherDetailsDTO{" +
                "voucher=" + voucher +
                '}';
    }
}
