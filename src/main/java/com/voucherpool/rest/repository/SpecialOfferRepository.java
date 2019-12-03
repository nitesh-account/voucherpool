package com.voucherpool.rest.repository;

import com.voucherpool.rest.domain.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, String> {
}
