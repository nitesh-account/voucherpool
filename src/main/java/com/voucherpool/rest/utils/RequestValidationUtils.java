package com.voucherpool.rest.utils;

import com.voucherpool.rest.domain.dtos.CreateOffersReqDTO;
import com.voucherpool.rest.domain.dtos.ValidateVoucherReqDTO;
import com.voucherpool.rest.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestValidationUtils {
    private Logger logger = LoggerFactory.getLogger(RequestValidationUtils.class);

    public static void validate(CreateOffersReqDTO createOffersReqDTO) {
        if (isOfferNameEmptyOrNull(createOffersReqDTO.getOfferName())) {
            throw new BadRequestException("Offer name is empty or null");
        }
        if (isDiscountOrExpiresAtNull(createOffersReqDTO.getDiscount()))
            throw new BadRequestException(("Discount is empty or null"));
        if (isDiscountOrExpiresAtNull(createOffersReqDTO.getExpiresAt()))
            throw new BadRequestException(("Discount is empty or null"));
        if (isEmailAListOrEmpty(createOffersReqDTO.getEmailList()))
            throw new BadRequestException("Email is not a list or list is empty");

    }

    public static void validate(ValidateVoucherReqDTO validateVoucherReqDTO) {
        if (isVoucherCodeEmptyOrNull(validateVoucherReqDTO.getVoucherCode())) {
            throw new BadRequestException("Voucher code is empty or null");
        }
        if (isEmailEmptyOrNull(validateVoucherReqDTO.getEmail()))
            throw new BadRequestException(("Email is empty or null"));

    }

    private static boolean isEmailEmptyOrNull(String email) {
        if (email != null && !email.isEmpty())
            return false;
        return true;
    }

    private static boolean isVoucherCodeEmptyOrNull(String voucherCode) {
        if (voucherCode != null && !voucherCode.isEmpty())
            return false;
        return true;
    }

    private static boolean isEmailAListOrEmpty(List<String> emailList) {
        boolean flag = false;
        if (emailList instanceof List<?>) {
            if (emailList.isEmpty())
                flag = true;
        } else
            flag = true;
        return flag;
    }

    private static boolean isDiscountOrExpiresAtNull(Long discount) {
        if (discount != null)
            return false;
        return true;
    }

    private static Boolean isOfferNameEmptyOrNull(String offerName) {
        if (offerName != null && !offerName.isEmpty())
            return false;
        return true;
    }

    public static void validate(String email) {
        if (email == null && email.isEmpty())
           throw new BadRequestException("email is empty or null");
    }
}
