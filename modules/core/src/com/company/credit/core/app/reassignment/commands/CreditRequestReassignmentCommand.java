/*
 * Copyright (c) 2021 com.company.credit.core.app.reassignment.commands
 */
package com.company.credit.core.app.reassignment.commands;


import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.company.credit.entity.CreditRequest;

/**
 * @author zaraevrs
 */
@ManagedBean(CreditRequestReassignmentCommand.NAME)
public class CreditRequestReassignmentCommand extends AbstractDocReassignmentCommand<CreditRequest> {
    protected static final String NAME = "creditrequest_reassignment_command";

    @PostConstruct
    protected void postInit() {
        type = "CreditRequest";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "credit$CreditRequest");
    }

    @Override
    public String getName() {
        return NAME;
    }
}