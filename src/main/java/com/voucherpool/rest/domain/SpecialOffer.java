package com.voucherpool.rest.domain;

import com.voucherpool.rest.domain.base.BaseMaster;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name="offer")
@Entity
public class SpecialOffer extends BaseMaster implements Serializable {
    private static final long serialVersionUID = -6759774343110776659L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="fixed_percentage_discount")
    private Long percentageDiscount;

    public SpecialOffer(String createdBy, String offerId, String name, Long percentageDiscount) {
        super(createdBy);
        this.id = offerId;
        this.name = name;
        this.percentageDiscount = percentageDiscount;
    }

    public SpecialOffer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Long percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialOffer that = (SpecialOffer) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(percentageDiscount, that.percentageDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, percentageDiscount);
    }
}
