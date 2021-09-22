/*
 * Copyright (c) 2021 com.company.credit.core.listener
 */
package com.company.credit.core.listener;

import com.haulmont.cuba.core.Persistence;
import org.apache.commons.lang.StringUtils;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import com.haulmont.cuba.core.global.AppBeans;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Set;
import javax.annotation.ManagedBean;
import javax.inject.Inject;

import com.company.credit.entity.Credit;
/**
 * @author zaraevrs
 */
@ManagedBean("credit_CreditListener")
public class CreditListener implements BeforeUpdateEntityListener<Credit>, BeforeInsertEntityListener<Credit> {

    @Inject
    protected Persistence persistence;

    @Override
    public void onBeforeUpdate(Credit entity) {

        Set<String> fields = persistence.getTools().getDirtyFields(entity);

        if (CollectionUtils.containsAny(fields, Arrays.asList("number"))){
            StringBuilder description=new StringBuilder();
            description.append(StringUtils.trimToEmpty(entity.<String>getValue("number")));
            entity.setDescription(description.toString());
        }
    }

    @Override
    public void onBeforeInsert(Credit entity) {
        onBeforeUpdate(entity);
    }
}