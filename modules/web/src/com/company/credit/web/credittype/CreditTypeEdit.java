
package com.company.credit.web.credittype;

import java.util.Map;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.credit.entity.CreditType;
import org.slf4j.Logger;

import javax.inject.Inject;

public class CreditTypeEdit extends AbstractEditor<CreditType> {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CreditTypeEdit.class);

    @Inject
    UniqueNumbersService uniqueNumbersService;

    @Override
    protected void initNewItem(CreditType item) {
        super.initNewItem(item);
        String uniqueNumber = String.format( "%03d" ,(uniqueNumbersService.getNextNumber("CreditTypeCode")));
        item.setCode(uniqueNumber);
        log.info("Номер для CreditType успешно сгенерирован и установлен {} :", uniqueNumber);
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
    }
}