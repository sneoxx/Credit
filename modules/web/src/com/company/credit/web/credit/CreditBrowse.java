package com.company.credit.web.credit;


import com.company.credit.entity.Credit;
import service.CreditConfig;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.thesis.core.entity.TsCard;
import com.haulmont.thesis.web.ui.basic.browse.AbstractCardBrowser;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;

public class CreditBrowse<T extends TsCard> extends AbstractCardBrowser<T> {

    @Inject
    private CreditConfig creditConfig;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        cardsTable.addStyleProvider(new Table.StyleProvider<Credit>() {
            @Override
            public String getStyleName(Credit entity, @Nullable String property) {
                BigDecimal loanAmountThreshold = new BigDecimal(creditConfig.getLoanAmountThreshold());
                        if (property == null) {
                             return null;
                        } else if (property.equals("sum")) {
                            if (entity.getSum().compareTo(loanAmountThreshold) >=0) {
                              return "new-style-yellow";
                        }
                        }
                return null;
            }
        });
    }
}

