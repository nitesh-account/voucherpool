package com.voucherpool.rest.repository;

import com.voucherpool.rest.domain.VoucherCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherCodeRepository extends JpaRepository<VoucherCode, String> {
    Optional<VoucherCode> findByCode(String voucherCode);
    List<VoucherCode> findByRecipientId(String recipientId);
}
