create table CREDIT_CREDIT_TYPE (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(150) not null,
    CODE varchar(3) not null,
    COMMENT_ varchar(400),
    --
    primary key (ID)
);
