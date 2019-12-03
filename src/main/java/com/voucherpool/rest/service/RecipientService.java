package com.voucherpool.rest.service;

import com.voucherpool.rest.domain.Recipient;
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
public class RecipientService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(RecipientService.class);

    /**
     * Save recipient data in recipient table
     *
     * @param recipient a valid {@link Recipient} object
     * @return newly created {@link Recipient} object
     */
    public Recipient save(Recipient recipient) {
        String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if(!StringUtils.isEmpty(createdBy))
            recipient.setCreatedBy(createdBy);

        return recipientRepository.save(recipient);
    }

    /**
     * Get all recipient data
     *
     * @param pageable
     * @return {@link Page <Recipient}
     */
    public Page<Recipient> findAll(Pageable pageable) {
        return recipientRepository.findAll(pageable);
    }

    /**
     * Find recipient based on recipient id
     *
     * @param recipientId unique recipient id
     * @return {@link Recipient} object
     *
     * @throws if recipient id is not found than throw {@link ResourceNotFoundException} exception.
     */
    public Recipient findByRecipientId(String recipientId) {
        return recipientRepository.findById(recipientId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipient [recipientId=" + recipientId + "] can't be found"));
    }

    /**
     * Delete recipient based on recipient Id
     *
     * @param recipientId a unique recipient Id
     * @return
     *
     * @throws if recipient Id is not found than throw {@link ResourceNotFoundException} exception.
     */
    public ResponseEntity<?> deleteRecipient(String recipientId) {
        return recipientRepository.findById(recipientId).map(recipient -> {
                    recipientRepository.delete(recipient);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Recipient [recipientId=" + recipientId + "] can't be found"));
    }

    public ResponseEntity<Recipient> updateRecipient(String recipientId, Recipient newRecipient) {
        return recipientRepository.findById(recipientId).map(recipient -> {
            recipient.setName(newRecipient.getName());
            recipient.setEmail(newRecipient.getEmail());
            String updatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            if(!StringUtils.isEmpty(updatedBy))
                recipient.setUpdatedBy(updatedBy);

            recipientRepository.save(recipient);
            return ResponseEntity.ok(recipient);
        }).orElseThrow(() -> new ResourceNotFoundException("Recipient [recipientId=" + recipientId + "] can't be found"));
    }
}
