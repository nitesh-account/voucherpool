package com.voucherpool.rest.service;

import com.voucherpool.rest.repository.RecipientRepository;
import com.voucherpool.rest.repository.SpecialOfferRepository;
import com.voucherpool.rest.repository.VoucherCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    private Logger logger = LoggerFactory.getLogger(BaseService.class);
    @Autowired
    RecipientRepository recipientRepository;
    @Autowired
    SpecialOfferRepository specialOfferRepository;
    @Autowired
    VoucherCodeRepository voucherCodeRepository;
}
