package com.voucherpool.rest.domain.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ValidVoucherListDTO {
    private Logger logger = LoggerFactory.getLogger(ValidateVoucherResDTO.class);

    private List<VoucherData> voucherDataList;

    public List<VoucherData> getVoucherDataList() {
        return voucherDataList;
    }

    public void setVoucherDataList(List<VoucherData> voucherDataList) {
        this.voucherDataList = voucherDataList;
    }

    public static class VoucherData {
        private String voucherCode;
        private String offerName;

        public String getVoucherCode() {
            return voucherCode;
        }

        public void setVoucherCode(String voucherCode) {
            this.voucherCode = voucherCode;
        }

        public String getOfferName() {
            return offerName;
        }

        public void setOfferName(String offerName) {
            this.offerName = offerName;
        }
    }
}
