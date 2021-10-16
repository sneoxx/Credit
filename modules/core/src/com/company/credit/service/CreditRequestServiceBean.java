/*
 * Copyright (c) 2021 com.company.credit.service
 */
package com.company.credit.service;

import com.company.credit.entity.CreditRequest;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.View;
import com.haulmont.thesis.core.entity.Contractor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author zaraevrs
 */
@Service(CreditRequestService.NAME)
public class CreditRequestServiceBean implements CreditRequestService {

    @Inject
    protected Persistence persistence ;

    @Inject
    protected Metadata metadata ;

    //Создадим новый сервис для подсчета количества заявок на кредит у Физического лица
    @Override
    public Integer getCreditRequestCount (Contractor contractor) {
        Transaction tx = persistence.createTransaction();
        if (contractor != null) {
            try {
                EntityManager em = persistence.getEntityManager();
                TypedQuery<CreditRequest> query = em.createQuery(
                        "select c from credit$CreditRequest c where c.borrower.id = :borrowerId",
                        CreditRequest.class);
                query.setView(metadata.getViewRepository().getView(CreditRequest.class, View.MINIMAL));
                query.setParameter("borrowerId", contractor.getId());
                List<CreditRequest> list = query.getResultList();
                tx.commit();
                if (CollectionUtils.isNotEmpty(list)) {
                    return list.size();
                } else {
                    return 0;
                }
            } finally {
                tx.end();
            }
        }
        return 0;
    }
//    4. Создать сервис осуществляющий проверку, что у пользователя нет других кредитов в
//    текущем банке из кредита.
//    1 Вызывать при смене заемщика или кредита в заявке и показывать сообщение о количестве кредитов для данного заемщика.
//    2 Блокировать   сохранение новой карточки, если количество кредитов больше порогового значения.
//    3 Пороговое значение задается для каждого банка как новый атрибут.
    @Override
    public boolean checkTheLimitOnTheNumberOfLoansIssued (CreditRequest creditRequest) {
        if (creditRequest.getCredit().getExtBank().getTheMaximumNumberOfLoansFromTheBorrower() < getCreditRequestCount(creditRequest.getContractor())){
            return true;
        }
        return false;
    }
}