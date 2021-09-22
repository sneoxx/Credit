/*
 * Copyright (c) 2021 com.haulmont.thesis.core.entity
 */
package com.company.credit.entity;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import com.haulmont.cuba.core.entity.annotation.Extends;
import javax.persistence.Column;
import com.haulmont.thesis.core.entity.Bank;

/**
 * @author zaraevrs
 */
@Extends(Bank.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SC")
@Entity(name = "credit$ExtBank")
public class ExtBank extends Bank {
    private static final long serialVersionUID = 3103109327134901893L;

    @Column(name = "THE_MAXIMUM_NUMBER_OF_LOANS_FROM_THE_BORROWER", nullable = false)
    protected Integer theMaximumNumberOfLoansFromTheBorrower;

    public void setTheMaximumNumberOfLoansFromTheBorrower(Integer theMaximumNumberOfLoansFromTheBorrower) {
        this.theMaximumNumberOfLoansFromTheBorrower = theMaximumNumberOfLoansFromTheBorrower;
    }

    public Integer getTheMaximumNumberOfLoansFromTheBorrower() {
        return theMaximumNumberOfLoansFromTheBorrower;
    }


}