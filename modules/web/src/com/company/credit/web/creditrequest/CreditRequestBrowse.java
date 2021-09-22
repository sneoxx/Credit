package com.company.credit.web.creditrequest;

import java.util.Map;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.credit.entity.CreditRequest;

public class CreditRequestBrowse extends AbstractDocBrowser<CreditRequest> {

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "credit$CreditRequest";
    }
}