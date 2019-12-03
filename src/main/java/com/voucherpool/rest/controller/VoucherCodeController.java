package com.voucherpool.rest.controller;

import com.voucherpool.rest.domain.dtos.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * VoucherCodeController is used to perform create, validate operations of voucher
 *
 * @author Nitesh Kumar
 */

@RestController
@CrossOrigin
@RequestMapping(value = {"/api"})
@Api(value = "/voucher", tags = "Voucher", description = "VoucherCode API's perform create, validate operations of voucher")
public class VoucherCodeController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(VoucherCodeController.class);

    @PostMapping(value = "/voucher/createOffers")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create offers. user name is mandatory.")
    public ResponseEntity<VoucherOfferDTO> createOffers(@RequestBody CreateOffersReqDTO createOffersReqDTO) {
        return voucherCodeService.createOffers(createOffersReqDTO);
    }

    @PutMapping(value = "/voucher/validate")
    @ApiOperation(value = "Validates the voucher code. user name is mandatory")
    public ResponseEntity<ValidateVoucherResDTO> validateVoucher(@RequestBody ValidateVoucherReqDTO validateVoucherReqDTO) {
        return voucherCodeService.validateVoucher(validateVoucherReqDTO);
    }

    @GetMapping(value = "/voucher/voucherList")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "Get valid voucher list using email. user name is optional")
    public ResponseEntity<ValidVoucherListDTO> getVoucherList(@RequestParam String email) {
        return voucherCodeService.getVoucherList(email);
    }
}
