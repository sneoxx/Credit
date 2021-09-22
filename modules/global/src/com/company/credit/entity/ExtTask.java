/*
 * Copyright (c) 2021 com.haulmont.thesis.core.entity
 */
package com.company.credit.entity;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import com.haulmont.cuba.core.entity.annotation.Extends;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.thesis.core.entity.Task;

/**
 * @author zaraevrs
 */
@Extends(Task.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("1020")
@Entity(name = "credit$Task")
public class ExtTask extends Task {
    private static final long serialVersionUID = -6784486948071069375L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDIT_APPLICATION_ID")
    protected CreditRequest creditApplication;

    public void setCreditApplication(CreditRequest creditApplication) {
        this.creditApplication = creditApplication;
    }

    public CreditRequest getCreditApplication() {
        return creditApplication;
    }


}