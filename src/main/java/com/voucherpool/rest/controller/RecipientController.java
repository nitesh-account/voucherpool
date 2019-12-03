package com.voucherpool.rest.controller;

import com.voucherpool.rest.domain.Recipient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * RecipientController is used to perform CRUD operations of recipient
 *
 * @author Nitesh Kumar
 */

@RestController
@CrossOrigin
@RequestMapping(value = {"/api"})
@Api(value = "/recipient", tags = "recipient", description = "Recipient API's perform all recipient's related CRUD operations")
public class RecipientController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(RecipientController.class);

    @PostMapping(value = "/recipients")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create recipient. user name is mandatory.")
    public Recipient save(@RequestBody Recipient recipient) {
        return recipientService.save(recipient);
    }

    @GetMapping(value = "/recipients")
    @ApiOperation(value = "Get all recipients")
    public Page<Recipient> all(Pageable pageable) {
        return recipientService.findAll(pageable);
    }

    @GetMapping(value = "/recipients/{recipientId}")
    @ApiOperation(value = "Find recipient based on recipients Id")
    public Recipient findByRecipientId(@PathVariable String recipientId) {
        return recipientService.findByRecipientId(recipientId);
    }

    @DeleteMapping(value = "/recipients/{recipientId}")
    @ApiOperation(value = "Delete recipient based on recipient Id")
    public ResponseEntity<?> deleteRecipient(@PathVariable String recipientId) {
        return recipientService.deleteRecipient(recipientId);
    }

    @PutMapping(value = "/recipients/{recipientId}")
    @ApiOperation(value = "Update recipient based on recipient Id")
    public ResponseEntity<Recipient> updateRecipient(@PathVariable String recipientId, @RequestBody Recipient newRecipient) {
        return recipientService.updateRecipient(recipientId, newRecipient);
    }
}
