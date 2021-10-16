package com.company.credit.web.creditrequest;

import com.company.credit.entity.Credit;
import com.company.credit.entity.CreditRequest;
import com.company.credit.service.CreditRequestService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.data.impl.DsListenerAdapter;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.workflow.core.app.WfUtils;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;


public class CreditRequestEdit extends AbstractDocEditor<CreditRequest> {

    @Inject
    CreditRequestService creditRequestService;

//    4. Создать сервис осуществляющий проверку, что у пользователя нет других кредитов в   текущем банке из кредита.
//    1 Вызывать при смене заемщика или кредита в заявке и показывать сообщение о количестве кредитов для данного заемщика.
    public void init(Map<String, Object> params) {
        super.init(params);
        cardDs.addListener(new DsListenerAdapter<CreditRequest>(){
                @Override
                public void valueChanged (CreditRequest source, String property, @Nullable Object prevValue, @Nullable
                        Object value) {
                    if (Arrays.asList( "credit", "borrower" ).contains(property)) {
                        if (value != null && source.getBorrower() != null && source.getCredit() != null) {
                            LoadContext ctx = new LoadContext(Credit.class).setView("_my_edit");
                            ctx.setQueryString("select u from credit$Credit u where u.id = :creditId")
                                    .setParameter("creditId", getItem().getCredit().getId());
                            Credit credit = dataService.load(ctx);
                            Integer theMaximumNumberOfLoansFromTheBorrower = credit.getExtBank().getTheMaximumNumberOfLoansFromTheBorrower();
                            showNotification("Число выданных кредитов у клиента в выбранном банке: ", String.valueOf(creditRequestService.getCreditRequestCount(getItem().getBorrower())), NotificationType.HUMANIZED);
                            //showMessageDialog("Число выданных кредитов: ", getItem().getCredit().getExtBank().getTheMaximumNumberOfLoansFromTheBorrower().toString(),MessageType.CONFIRMATION);
                        }
                    }
                }
            });
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "correspondenceHistoryTab,docLogTab,cardLinksTab,processTab,securityTab,docTransferLogTab,cardProjectsTab,versionsTab,openHistoryTab";
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        printButton.addAction(new PrintReportAction("printExecutionList", this, "printDocExecutionListReportName"));
    }

    @Override
    protected Component createState() {
        if (WfUtils.isCardInState(getItem(), "New") || StringUtils.isEmpty(getItem().getState())) {
            Label label = componentsFactory.createComponent(Label.NAME);
            label.setValue(StringUtils.isEmpty(getItem().getState()) ? "" : getItem().getLocState());
            return label;
        } else {
            return super.createState();
        }
    }

    @Override
    protected void fillHiddenTabs() {
        hiddenTabs.put("office", getMessage("office"));
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        hiddenTabs.put("docTreeTab", getMessage("docTreeTab"));
        hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        super.fillHiddenTabs();
    }

//    4. Создать сервис осуществляющий проверку, что у пользователя нет других кредитов в   текущем банке из кредита.
//    1 Вызывать при смене заемщика или кредита в заявке и показывать сообщение о количестве кредитов для данного заемщика.
//    2 Блокировать сохранение новой карточки, если количество кредитов больше порогового значения.
    @Override
    public boolean validateAll() {
        boolean b = super.validateAll();
        LoadContext ctx = new LoadContext(Credit.class).setView("_my_edit");
        ctx.setQueryString("select u from credit$Credit u where u.id = :creditId")
                .setParameter("creditId", getItem().getCredit().getId());
        Credit credit = dataService.load(ctx);
        Integer theMaximumNumberOfLoansFromTheBorrower = credit.getExtBank().getTheMaximumNumberOfLoansFromTheBorrower();
        Integer creditCount = creditRequestService.getCreditRequestCount(getItem().getBorrower());
        if (b) {
            if (creditCount > theMaximumNumberOfLoansFromTheBorrower) {
                showOptionDialog(getMessage("Внимание"), getMessage("Число кредитов клиента в данном банке " + creditCount + " превышает пороговое значение " + theMaximumNumberOfLoansFromTheBorrower + "и выдать его невозможно, измените заявку"),
                        MessageType.CONFIRMATION, new Action[]
                                { new DialogAction(DialogAction.Type.YES) {
                                    @Override
                                    public void actionPerform(Component component) { }
                                }
                                }
                );
                return false;
            }
        }
        return b;
    }
}