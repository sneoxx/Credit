/*
 * Copyright (c) 2021 com.company.credit.entity
 */
package com.company.credit.entity;


/**
 * @author zaraevrs
 */
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|name")
@Table(name = "CREDIT_CREDIT_TYPE")
@Entity(name = "credit$CreditType")
@EnableRestore
@TrackEditScreenHistory
public class CreditType extends StandardEntity {
    private static final long serialVersionUID = -4626292878969781540L;

    @Column(name = "NAME", nullable = false, length = 150)
    protected String name;

    @Column(name = "CODE", nullable = false, length = 3)
    protected String code;

    @Column(name = "COMMENT_", length = 400)
    protected String comment;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


}