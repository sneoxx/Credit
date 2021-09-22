/*
 * Copyright (c) 2021 com.company.credit.web.individualbrowse
 */
package com.company.credit.web.individualbrowse;

import com.company.credit.service.CreditRequestService;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.entity.Category;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.AppConfig;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.thesis.web.ui.individual.IndividualBrowser;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author zaraevrs
 */
public class ExtIndividualBrowser extends IndividualBrowser {

    @Inject
    CreditRequestService creditRequestService;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        individualsTable.addGeneratedColumn(getMessage("NumberOfLoanApplications"), new Table.ColumnGenerator() {

            @Override
            public Component generateCell(Entity entity) {
                return new Table.PlainTextCell(creditRequestService.getCreditRequestCount((Individual) entity).toString());
            }
        });
    }
}
