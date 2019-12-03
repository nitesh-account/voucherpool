package com.voucherpool.rest.domain;

import com.voucherpool.rest.domain.base.BaseMaster;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name="voucher")
@Entity
public class VoucherCode extends BaseMaster implements Serializable {
    private static final long serialVersionUID = -6759774343110776659L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name="voucher_code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Recipient recipient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "offer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SpecialOffer specialOffer;

    @Column(name="expiration_date")
    private Long expirationDate;

    @Column(name="is_used")
    private Boolean is_used;

    @Column(name="date_used")
    private long date_used;

    public VoucherCode() {
    }

    public VoucherCode(String createdBy, String voucherId, String code, Recipient recipient, SpecialOffer specialOffer, Long expirationDate, Boolean is_used, long date_used) {
        super(createdBy);
        this.id = voucherId;
        this.code = code;
        this.recipient = recipient;
        this.specialOffer = specialOffer;
        this.expirationDate = expirationDate;
        this.is_used = is_used;
        this.date_used = date_used;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public SpecialOffer getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(SpecialOffer specialOffer) {
        this.specialOffer = specialOffer;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getIs_used() {
        return is_used;
    }

    public void setIs_used(Boolean is_used) {
        this.is_used = is_used;
    }

    public long getDate_used() {
        return date_used;
    }

    public void setDate_used(long date_used) {
        this.date_used = date_used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherCode that = (VoucherCode) o;
        return date_used == that.date_used &&
                Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(recipient, that.recipient) &&
                Objects.equals(specialOffer, that.specialOffer) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(is_used, that.is_used);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, recipient, specialOffer, expirationDate, is_used, date_used);
    }
}
