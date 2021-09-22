alter table CREDIT_CREDIT_REQUEST drop column BORROWER_ID cascade ;
alter table CREDIT_CREDIT_REQUEST add column BORROWER_ID uuid ;
