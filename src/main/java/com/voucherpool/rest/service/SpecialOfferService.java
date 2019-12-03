package com.voucherpool.rest.service;

import com.voucherpool.rest.domain.SpecialOffer;
import com.voucherpool.rest.enums.HttpHeaders;
import com.voucherpool.rest.exception.ResourceNotFoundException;
import com.voucherpool.rest.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SpecialOfferService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(SpecialOfferService.class);

    /**
     * Save offer data in offers table
     *
     * @param specialOffer a valid {@link SpecialOffer} object
     * @return newly created {@link SpecialOffer} object
     */
    public SpecialOffer save(SpecialOffer specialOffer) {
        String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if(!StringUtils.isEmpty(createdBy))
            specialOffer.setCreatedBy(createdBy);

        return specialOfferRepository.save(specialOffer);
    }

    /**
     * Get all offers data
     *
     * @param pageable
     * @return {@link Page <SpecialOffer}
     */
    public Page<SpecialOffer> findAll(Pageable pageable) {
        return specialOfferRepository.findAll(pageable);
    }

    /**
     * Find offer based on specialOffer Id
     *
     * @param specialOfferId unique specialOffer id
     * @return {@link SpecialOffer} object
     *
     * @throws if specialOffer id is not found than throw {@link ResourceNotFoundException} exception.
     */
    public SpecialOffer findByOfferId(String specialOfferId) {
        return specialOfferRepository.findById(specialOfferId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer [specialOfferId=" + specialOfferId + "] can't be found"));
    }

    /**
     * Delete offer based on specialOffer Id
     *
     * @param specialOfferId a unique specialOffer Id
     * @return
     *
     * @throws if specialOffer id is not found than throw {@link ResourceNotFoundException} exception.
     */
    public ResponseEntity<?> deleteOffer(String specialOfferId) {
        return specialOfferRepository.findById(specialOfferId).map(specialOffer -> {
                    specialOfferRepository.delete(specialOffer);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("SpecialOffer [specialOfferId=" + specialOfferId + "] can't be found"));
    }

    /**
     * Update offer based on specialOffer Id
     *
     * @param specialOfferId a unique specialOffer id
     * @param newSpecialOffer a valid {@link SpecialOffer} object
     * @return {@link SpecialOffer} object
     *
     * @throws if SpecialOffer id is not found than throw {@link ResourceNotFoundException} exception.
     */
    public ResponseEntity<SpecialOffer> updateOffer(String specialOfferId, SpecialOffer newSpecialOffer) {
        return specialOfferRepository.findById(specialOfferId).map(specialOffer -> {
            specialOffer.setName(newSpecialOffer.getName());
            specialOffer.setPercentageDiscount(newSpecialOffer.getPercentageDiscount());
            String updatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            if(!StringUtils.isEmpty(updatedBy))
                specialOffer.setUpdatedBy(updatedBy);

            specialOfferRepository.save(specialOffer);
            return ResponseEntity.ok(specialOffer);
        }).orElseThrow(() -> new ResourceNotFoundException("SpecialOffer [specialOfferId=" + specialOfferId + "] can't be found"));
    }
}
