alter table CREDIT_CREDIT add constraint FK_CREDIT_CREDIT_CREDIT_TYPE_ID foreign key (CREDIT_TYPE_ID) references CREDIT_CREDIT_TYPE(ID);
alter table CREDIT_CREDIT add constraint FK_CREDIT_CREDIT_BANK_ID foreign key (BANK_ID) references DF_BANK(ID);
alter table CREDIT_CREDIT add constraint FK_CREDIT_CREDIT_CARD_ID foreign key (CARD_ID) references WF_CARD(ID);
create index IDX_CREDIT_CREDIT_CREDIT_TYPE on CREDIT_CREDIT (CREDIT_TYPE_ID);
create index IDX_CREDIT_CREDIT_BANK on CREDIT_CREDIT (BANK_ID);
