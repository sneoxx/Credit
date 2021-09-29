/*
 * Copyright (c) 2021 com.company.credit.service
 */
package com.company.credit.service;

import com.company.credit.entity.CreditRequest;
import com.haulmont.thesis.core.entity.Contractor;

/**
 * @author zaraevrs
 */
public interface CreditRequestService {
    String NAME = "credit_CreditRequestService";

    Integer getCreditRequestCount (Contractor individual);

    boolean checkTheLimitOnTheNumberOfLoansIssued(CreditRequest creditRequest);
}