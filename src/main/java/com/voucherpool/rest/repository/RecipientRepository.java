package com.voucherpool.rest.repository;

import com.voucherpool.rest.domain.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, String> {
    Optional<Recipient> findByEmail(String email);
}
