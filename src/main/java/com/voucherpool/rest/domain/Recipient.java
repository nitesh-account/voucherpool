package com.voucherpool.rest.domain;

import com.voucherpool.rest.domain.base.BaseMaster;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "recipient")
@Entity
public class Recipient extends BaseMaster implements Serializable {
    private static final long serialVersionUID = -6380749575516426900L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    public Recipient() {
    }

    public Recipient(String createdBy, String name, String email) {
        super(createdBy);
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(id, recipient.id) &&
                Objects.equals(name, recipient.name) &&
                Objects.equals(email, recipient.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
