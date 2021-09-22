create table CREDIT_CREDIT (
    CARD_ID uuid,
    --
    NUMBER_ varchar(50),
    CREDIT_TYPE_ID uuid not null,
    BANK_ID uuid,
    CREDIT_DATE decimal(19, 2),
    --
    primary key (CARD_ID)
);
