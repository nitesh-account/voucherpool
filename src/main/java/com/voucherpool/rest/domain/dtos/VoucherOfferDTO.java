package com.voucherpool.rest.domain.dtos;

import com.voucherpool.rest.domain.SpecialOffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherOfferDTO {
    private Logger logger = LoggerFactory.getLogger(VoucherOfferDTO.class);

    private SpecialOffer offerDetails;
    private VoucherDetailsDTO voucherDetails;

    public SpecialOffer getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(SpecialOffer offerDetails) {
        this.offerDetails = offerDetails;
    }

    public VoucherDetailsDTO getVoucherDetails() {
        return voucherDetails;
    }

    public void setVoucherDetails(VoucherDetailsDTO voucherDetails) {
        this.voucherDetails = voucherDetails;
    }

    @Override
    public String toString() {
        return "VoucherOfferDTO{" +
                "offerDetails=" + offerDetails +
                ", voucherDetails=" + voucherDetails +
                '}';
    }
}
