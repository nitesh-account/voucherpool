package com.voucherpool.rest.service;

import com.voucherpool.rest.domain.Recipient;
import com.voucherpool.rest.domain.SpecialOffer;
import com.voucherpool.rest.domain.VoucherCode;
import com.voucherpool.rest.domain.dtos.*;
import com.voucherpool.rest.enums.HttpHeaders;
import com.voucherpool.rest.exception.CustomException;
import com.voucherpool.rest.exception.NoContentException;
import com.voucherpool.rest.exception.ResourceNotFoundException;
import com.voucherpool.rest.utils.CommonUtils;
import com.voucherpool.rest.utils.DateAssistantUtils;
import com.voucherpool.rest.utils.HttpUtils;
import com.voucherpool.rest.utils.RequestValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * VoucherCodeService class is used for logical implementation for voucher code related operations
 *
 * @author Nitesh Kumar
 */

@Service
public class VoucherCodeService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(VoucherCodeService.class);

    /**
     * Create offers For a given Special Offer and an expiration date, generate for each Recipient a Voucher Code
     * if email is not exist than will create {@link Recipient}, will create {@link SpecialOffer} and {@link VoucherCode}
     *
     * @param createOffersReqDTO a valid {@link CreateOffersReqDTO} object
     * @return a valid {@link VoucherOfferDTO} object
     */
    @Transactional
    public ResponseEntity<VoucherOfferDTO> createOffers(CreateOffersReqDTO createOffersReqDTO) {
        RequestValidationUtils.validate(createOffersReqDTO);

        VoucherOfferDTO voucherOfferDTO = new VoucherOfferDTO();
        VoucherDetailsDTO voucherDetailsDTO = new VoucherDetailsDTO();
        List<VoucherDTO> voucher = new ArrayList<>();
        SpecialOffer specialOffer = createSpecialOffer(createOffersReqDTO);

        for (String recipientEmail : createOffersReqDTO.getEmailList()) {
            Optional<Recipient> optionalRecipient = recipientRepository.findByEmail(recipientEmail);
            Recipient recipient = optionalRecipient.orElseGet(() -> createRecipient(recipientEmail));
            VoucherDTO voucherDTO = setVoucherDTO(createOffersReqDTO, specialOffer, recipient);
            voucher.add(voucherDTO);
        }
        voucherDetailsDTO.setVoucher(voucher);

        voucherOfferDTO.setOfferDetails(specialOffer);
        voucherOfferDTO.setVoucherDetails(voucherDetailsDTO);
        return ResponseEntity.ok(voucherOfferDTO);
    }

    /**
     * Setting {@link VoucherDTO} and save {@link VoucherCode}
     *
     * @param createOffersReqDTO a valid {@link CreateOffersReqDTO} object
     * @param specialOffer a valid {@link SpecialOffer} object
     * @param recipient a valid {@link Recipient}
     * @return a valid {@link VoucherDTO}
     */
    @Transactional
    private VoucherDTO setVoucherDTO(CreateOffersReqDTO createOffersReqDTO, SpecialOffer specialOffer, Recipient recipient) {
        VoucherCode code = new VoucherCode();
        VoucherDTO voucherDTO = new VoucherDTO();
        String voucherCode = CommonUtils.generateUniqueRandomCode();
        voucherDTO.setVoucherCode(voucherCode);
        code.setCode(voucherCode);
        voucherDTO.setUserId(recipient.getId());
        code.setRecipient(recipient);
        voucherDTO.setOfferId(specialOffer.getId());
        code.setSpecialOffer(specialOffer);
        voucherDTO.setExpiresAt(createOffersReqDTO.getExpiresAt());
        code.setExpirationDate(createOffersReqDTO.getExpiresAt());
        code.setIs_used(false);
        String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if (!StringUtils.isEmpty(createdBy))
            code.setCreatedBy(createdBy);

        voucherCodeRepository.save(code);

        return voucherDTO;
    }

    /**
     * Save {@link Recipient} in recipient table
     *
     * @param recipientEmail
     * @return a valid {@link Recipient}
     */
    @Transactional
    private Recipient createRecipient(String recipientEmail) {
        Recipient recipient = new Recipient();
        recipient.setEmail(recipientEmail);
        String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if (!StringUtils.isEmpty(createdBy))
            recipient.setCreatedBy(createdBy);

        return recipientRepository.save(recipient);
    }

    /**
     * Save {@link SpecialOffer} object in Offer table
     *
     * @param createOffersReqDTO a valid {@link CreateOffersReqDTO} object
     * @return a valid {@link SpecialOffer}
     */
    private SpecialOffer createSpecialOffer(CreateOffersReqDTO createOffersReqDTO) {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setName(createOffersReqDTO.getOfferName());
        specialOffer.setPercentageDiscount(createOffersReqDTO.getDiscount());
        String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if (!StringUtils.isEmpty(createdBy))
            specialOffer.setCreatedBy(createdBy);

        return specialOfferRepository.save(specialOffer);
    }

    /**
     * Given a Voucher Code and Email and validates the Voucher Code. In Case it is valid, return the Percentage Discount
     * and set the date of usage.
     *
     * @param validateVoucherReqDTO a valid {@link ValidateVoucherReqDTO}
     * @return a valid {@link ValidateVoucherResDTO}
     * @throws {@link ResourceNotFoundException} if voucher code and email is not exist
     * @throws {@link CustomException} for generic exception
     * @throws {@link NoContentException} if voucher code is not matched with email
     */
    public ResponseEntity<ValidateVoucherResDTO> validateVoucher(ValidateVoucherReqDTO validateVoucherReqDTO) {
        RequestValidationUtils.validate(validateVoucherReqDTO);
        Optional<Recipient> optionalRecipient = recipientRepository.findByEmail(validateVoucherReqDTO.getEmail());
        Recipient recipient = optionalRecipient.orElseThrow(()-> new ResourceNotFoundException("Email [email id=" + validateVoucherReqDTO.getEmail() + "] can't be found"));
        Optional<VoucherCode> optionalVoucherCode = voucherCodeRepository.findByCode(validateVoucherReqDTO.getVoucherCode());
        VoucherCode voucherCode = optionalVoucherCode.orElseThrow(()-> new ResourceNotFoundException("Voucher code [voucher code=" + validateVoucherReqDTO.getVoucherCode() + "] can't be found"));
        if(voucherCode.getRecipient().getEmail().equalsIgnoreCase(recipient.getEmail())){
            boolean isCouponExpired = DateAssistantUtils.checkExpiryDate(voucherCode.getExpirationDate());
            if(isCouponExpired)
                throw new CustomException("Voucher code [voucher code=" + validateVoucherReqDTO.getVoucherCode() + "] is expired");
            else if(voucherCode.getIs_used())
                throw new CustomException("Voucher code [voucher code=" + validateVoucherReqDTO.getVoucherCode() + "] is already used");
            else
                voucherCode = updateVoucherCode(voucherCode);
            ValidateVoucherResDTO validateVoucherResDTO = new ValidateVoucherResDTO();
            validateVoucherResDTO.setPercentageDiscount(voucherCode.getSpecialOffer().getPercentageDiscount());
            return ResponseEntity.ok(validateVoucherResDTO);
        }
        else
            throw new NoContentException("Voucher code [voucher code=" + validateVoucherReqDTO.getVoucherCode() + "] is not matched with Email [email id=" + validateVoucherReqDTO.getEmail() + "]");
    }

    /**
     * Update {@link VoucherCode} object in voucher table
     *
     * @param voucherCode a valid {@link VoucherCode} object
     * @return a valid {@link VoucherCode} object
     */
    @Transactional
    private VoucherCode updateVoucherCode(VoucherCode voucherCode) {
        voucherCode.setIs_used(true);
        voucherCode.setDate_used(DateAssistantUtils.setDate());
        String updatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if (!StringUtils.isEmpty(updatedBy))
            voucherCode.setUpdatedBy(updatedBy);

        return voucherCodeRepository.save(voucherCode);
    }

    /**
     * Given Email, return all his valid Voucher Codes with the Name of the Special Offer.
     *
     * @param email
     * @return a valid {@link ValidVoucherListDTO}
     * @throws ResourceNotFoundException if email not found
     */
    public ResponseEntity<ValidVoucherListDTO>  getVoucherList(String email) {
        RequestValidationUtils.validate(email);
        ValidVoucherListDTO validVoucherListDTO = new ValidVoucherListDTO();
        List<ValidVoucherListDTO.VoucherData> voucherDataList = new ArrayList<>();
        Optional<Recipient> optionalRecipient = recipientRepository.findByEmail(email);
        Recipient recipient = optionalRecipient.orElseThrow(()-> new ResourceNotFoundException("Email [email id=" + email + "] can't be found"));
        List<VoucherCode> voucherCodeList = voucherCodeRepository.findByRecipientId(recipient.getId());
        voucherCodeList.parallelStream().forEach(voucherCode -> {
            if(!voucherCode.getIs_used()&&!DateAssistantUtils.checkExpiryDate(voucherCode.getExpirationDate())){
                ValidVoucherListDTO.VoucherData voucherData = new ValidVoucherListDTO.VoucherData();
                voucherData.setVoucherCode(voucherCode.getCode());
                voucherData.setOfferName(voucherCode.getSpecialOffer().getName());
                voucherDataList.add(voucherData);
            }
        });
        validVoucherListDTO.setVoucherDataList(voucherDataList);
        return ResponseEntity.ok(validVoucherListDTO);
    }
}
