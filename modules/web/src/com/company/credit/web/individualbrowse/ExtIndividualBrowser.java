/*
 * Copyright (c) 2021 com.company.credit.web.individualbrowse
 */
package com.company.credit.web.individualbrowse;

import com.company.credit.service.CreditRequestService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.thesis.web.ui.individual.IndividualBrowser;

import javax.annotation.Nullable;
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

        individualsTable.addStyleProvider(new Table.StyleProvider<Individual>() {

            @Override
            public String getStyleName(Individual entity, @Nullable String property) {
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
