/*
 * Copyright (c) 2021 com.company.credit.web.companybrowse
 */
package com.company.credit.web.companybrowse;

import com.company.credit.service.CreditRequestService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.thesis.core.entity.Company;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.thesis.web.ui.company.CompanyBrowser;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author zaraevrs
 */
public class ExtCompanyBrowser extends CompanyBrowser {

    @Inject
    CreditRequestService creditRequestService;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        companiesTable.addGeneratedColumn(getMessage("NumberOfLoanApplications"), new Table.ColumnGenerator() {

            @Override
            public Component generateCell(Entity entity) {
                return new Table.PlainTextCell(creditRequestService.getCreditRequestCount((Company) entity).toString());
            }
        });
    }
}
