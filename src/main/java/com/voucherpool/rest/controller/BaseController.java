package com.voucherpool.rest.controller;

import com.voucherpool.rest.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseController is used to define common services
 *
 * @author Nitesh Kumar
 */

public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    RecipientService recipientService;

    @Autowired
    SpecialOfferService specialOfferService;

    @Autowired
    VoucherCodeService voucherCodeService;
}
