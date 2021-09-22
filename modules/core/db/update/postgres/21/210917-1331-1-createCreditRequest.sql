create table CREDIT_CREDIT_REQUEST (
    CARD_ID uuid,
    --
    CREDIT_ID uuid not null,
    BORROWER_ID uuid,
    --
    primary key (CARD_ID)
);
