package com.voucherpool.rest.domain.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CreateOffersReqDTO {
    private Logger logger = LoggerFactory.getLogger(CreateOffersReqDTO.class);

    private String offerName;
    private Long discount;
    private Long expiresAt;
    private List<String> emailList;

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }
}
