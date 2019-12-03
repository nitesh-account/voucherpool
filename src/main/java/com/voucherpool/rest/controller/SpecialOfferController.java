package com.voucherpool.rest.controller;

import com.voucherpool.rest.domain.SpecialOffer;
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
 * SpecialOfferController is used to perform CRUD operations of offer
 *
 * @author Nitesh Kumar
 */

@RestController
@CrossOrigin
@RequestMapping(value = {"/api"})
@Api(value = "/specialoffer", tags = "SpecialOffer", description = "SpecialOffer API's perform all offer's related CRUD operations")
public class SpecialOfferController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(SpecialOfferController.class);

    @PostMapping(value = "/offers")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create special offer. user name is mandatory.")
    public SpecialOffer save(@RequestBody SpecialOffer specialOffer) {
        return specialOfferService.save(specialOffer);
    }

    @GetMapping(value = "/offers")
    @ApiOperation(value = "Get all offers")
    public Page<SpecialOffer> all(Pageable pageable) {
        return specialOfferService.findAll(pageable);
    }

    @GetMapping(value = "/offers/{offerId}")
    @ApiOperation(value = "Find offer based on offer Id")
    public SpecialOffer findByOfferId(@PathVariable String offerId) {
        return specialOfferService.findByOfferId(offerId);
    }

    @DeleteMapping(value = "/offers/{offerId}")
    @ApiOperation(value = "Delete offer based on offer Id")
    public ResponseEntity<?> deleteOffer(@PathVariable String offerId) {
        return specialOfferService.deleteOffer(offerId);
    }

    @PutMapping(value = "/offers/{offerId}")
    @ApiOperation(value = "Update offer based on offer Id")
    public ResponseEntity<SpecialOffer> updateOffer(@PathVariable String offerId, @RequestBody SpecialOffer newSpecialOffer) {
        return specialOfferService.updateOffer(offerId, newSpecialOffer);
    }
}
