

/*
 * Copyright (c) 2021 com.haulmont.thesis.core.entity
 */
package com.company.credit.entity;

import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.Messages;

import com.haulmont.thesis.core.entity.Contractor;
import com.haulmont.thesis.core.entity.Doc;

import com.haulmont.thesis.core.global.EntityCopyUtils;
import org.apache.commons.lang.StringUtils;
import com.haulmont.thesis.core.entity.HasDetailedDescription;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.PrimaryKeyJoinColumn;
import com.haulmont.thesis.core.entity.Individual;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.annotation.Listeners;

/**
 * @author zaraevrs
 */
@Listeners("thesis_DocEntityListener")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
@DiscriminatorValue("1100")
@Table(name = "CREDIT_CREDIT_REQUEST")
@Entity(name = "credit$CreditRequest")
@EnableRestore
@TrackEditScreenHistory
public class CreditRequest extends Doc implements HasDetailedDescription {
    private static final long serialVersionUID = 7901078715072101373L;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CREDIT_ID")
    protected Credit credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BORROWER_ID")
    protected Contractor borrower;

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setBorrower(Contractor borrower) {
        this.borrower = borrower;
    }

    public Contractor getBorrower() {
        return borrower;
    }

    @Override
    public void copyFrom(Doc srcDoc, Set<com.haulmont.cuba.core.entity.Entity> toCommit, boolean copySignatures,
                         boolean onlyLastAttachmentsVersion, boolean useOriginalAttachmentCreatorAndCreateTs,
                         boolean copyAllVersionMainAttachment) {
        super.copyFrom(srcDoc, toCommit, copySignatures, onlyLastAttachmentsVersion,
                useOriginalAttachmentCreatorAndCreateTs, copyAllVersionMainAttachment);
        Metadata metadata = AppBeans.get(Metadata.NAME);
        MetaClass metaClass = metadata.getClassNN(getClass());
        EntityCopyUtils.copyProperties(srcDoc, this, metaClass.getOwnProperties(), toCommit);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String getDetailedDescription(Locale locale, boolean includeState, boolean includeShortInfo) {
        Messages messages = AppBeans.get(Messages.NAME);
        String dateFormat = Datatypes.getFormatStrings(locale).getDateFormat();
        String description;
        String locState = getLocState(locale);

        description =
            getDocKind().getName() + " "
                + (StringUtils.isBlank(getNumber()) ? "" : messages.getMessage(Doc.class, "notification.number", locale) + " " + getNumber() + " ")
                + (getDate() == null ? "" : messages.getMessage(Doc.class, "notification.from", locale) + " "
                + new SimpleDateFormat(dateFormat).format(getDate()))
                + (includeState && StringUtils.isNotBlank(locState) ? " [" + locState + "]" : "");


        if (includeShortInfo && (StringUtils.isNotBlank(getTheme()) || StringUtils.isNotBlank(getComment()))) {
            int length = description.length();
            int infoLength = MAX_SUBJECT_LENGTH - length;

            if (infoLength < MIN_SHORT_INFO_LENGTH) return description;

            String shortInfo = StringUtils.defaultIfBlank(getTheme(), getComment());
            return description + " - " + StringUtils.abbreviate(shortInfo, infoLength);
        } else {
            return description;
        }
    }

    
}