/*
 * Copyright (c) 2021 com.company.credit.entity
 */
package com.company.credit.entity;


/**
 * @author zaraevrs
 */
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.PrimaryKeyJoinColumn;
import com.haulmont.thesis.core.entity.Bank;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.thesis.core.entity.TsCard;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Listeners("credit_CreditListener")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("1")
@Table(name = "CREDIT_CREDIT")
@Entity(name = "credit$Credit")
@EnableRestore
@TrackEditScreenHistory
public class Credit extends TsCard {
    private static final long serialVersionUID = -7390678890384918474L;

    @Column(name = "NUMBER_", length = 50)
    protected String number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CREDIT_TYPE_ID")
    protected CreditType creditType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    protected ExtBank extBank;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREDIT_DATE")
    protected Date creditDate;

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setExtBank(ExtBank extBank) {
        this.extBank = extBank;
    }

    public ExtBank getExtBank() {
        return extBank;
    }


}