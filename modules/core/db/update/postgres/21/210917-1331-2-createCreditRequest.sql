alter table CREDIT_CREDIT_REQUEST add constraint FK_CREDIT_CREDIT_REQUEST_CREDIT_ID foreign key (CREDIT_ID) references CREDIT_CREDIT(CARD_ID);
alter table CREDIT_CREDIT_REQUEST add constraint FK_CREDIT_CREDIT_REQUEST_BORROWER_ID foreign key (BORROWER_ID) references DF_INDIVIDUAL(CONTRACTOR_ID);
alter table CREDIT_CREDIT_REQUEST add constraint FK_CREDIT_CREDIT_REQUEST_CARD_ID foreign key (CARD_ID) references DF_DOC(CARD_ID);
create index IDX_CREDIT_CREDIT_REQUEST_BORROWER on CREDIT_CREDIT_REQUEST (BORROWER_ID);
create index IDX_CREDIT_CREDIT_REQUEST_CREDIT on CREDIT_CREDIT_REQUEST (CREDIT_ID);