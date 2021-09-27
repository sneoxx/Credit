/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */

package service;


import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;


@Source(type = SourceType.APP)
public interface CreditConfig extends Config {

    @Property("credit.LoanAmountThreshold")
    @Default("10000")
    String getLoanAmountThreshold();

    String setLoanAmountThreshold();
}

