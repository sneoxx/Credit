/*
 * Copyright (c) 2021 com.company.credit.web.companybrowse
 */
package com.company.credit.web.companybrowse;

import com.company.credit.service.CreditRequestService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.thesis.core.entity.Company;
import com.haulmont.thesis.web.ui.company.CompanyBrowser;

import javax.annotation.Nullable;
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


        companiesTable.addStyleProvider(new Table.StyleProvider<Company>() {
            @Override
            public String getStyleName(Company entity, @Nullable String property) {
                Integer x = creditRequestService.getCreditRequestCount(entity);
                if (x >= 0 && x < 2) {
                    return "new-style-red";
                } else if (x >= 2 && x < 4) {
                    return "new-style-green";
                } else if (x >= 4 && x < 6) {
                    return "new-style-blue";
                }
                return null;
            }
        });
    }
}
