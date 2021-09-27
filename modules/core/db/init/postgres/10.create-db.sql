-- begin CREDIT_CREDIT_TYPE
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
)^
-- end CREDIT_CREDIT_TYPE
-- begin CREDIT_CREDIT
create table CREDIT_CREDIT (
    CARD_ID uuid,
    --
    NUMBER_ varchar(50) not null,
    CREDIT_TYPE_ID uuid not null,
    BANK_ID uuid not null,
    CREDIT_DATE date not null,
    SUM_ decimal(19, 2) not null,
    --
    primary key (CARD_ID)
)^
-- end CREDIT_CREDIT
--Add default numerator for credit$Credit
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'CreditNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
    PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('556669fc-0bd5-449c-897a-197d1f44db43', now(), 'system', 1, 'CreditNumerator', 'CR-[number]', FALSE, 'Y', 1,
    '{"captionWithLanguageList":[{"language":"ru","caption":"Credit"},{"language":"en","caption":"Credit"}]}'
    );
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert()^
drop function if exists baseInsert()^
-- begin CREDIT_CREDIT_REQUEST
create table CREDIT_CREDIT_REQUEST (
    CARD_ID uuid,
    --
    CREDIT_ID uuid not null,
    BORROWER_ID uuid,
    --
    primary key (CARD_ID)
)^
-- end CREDIT_CREDIT_REQUEST
--Add default numerator for credit$CreditRequest
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
    cnt integer = 0;
BEGIN
cnt = (select count(id) from DF_NUMERATOR where CODE = 'CreditRequestNumerator' and delete_ts is null);
if(cnt = 0) then
    INSERT INTO DF_NUMERATOR (ID, CREATE_TS, CREATED_BY, VERSION, CODE, NUMERATOR_FORMAT, SCRIPT_ENABLED,
    PERIODICITY, NUMBER_INITIAL_VALUE, LOC_NAME)
    VALUES ('49e5c7e9-3600-4eed-af62-9b3e2c90aee0', now(), 'system', 1, 'CreditRequestNumerator', 'REQ-[number]', FALSE, 'Y', 1,
    '{"captionWithLanguageList":[{"language":"ru","caption":"CreditRequest"},{"language":"en","caption":"CreditRequest"}]}'
    );
end if;
return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert()^
drop function if exists baseInsert()^
--Insert new doc type for credit$CreditRequest
insert into TS_CARD_TYPE (ID, CREATE_TS, CREATED_BY, NAME, DISCRIMINATOR,FIELDS_XML) values ('4b15d4f9-e7ee-457f-a4e1-d27969e703a6', current_timestamp, 'admin', 'credit$CreditRequest', 1100, '')^
--Add default doc kind for credit$CreditRequest
CREATE OR REPLACE FUNCTION baseInsert()
RETURNS integer
AS $$
DECLARE
cnt integer = 0;
BEGIN
cnt = (select count(CATEGORY_ID) from DF_DOC_KIND where category_id = '4eb37cd5-657a-48a2-93a1-a8e57a7ec220');
if(cnt = 0) then
    insert into SYS_CATEGORY (ID, NAME, ENTITY_TYPE, IS_DEFAULT, CREATE_TS, CREATED_BY, VERSION, DISCRIMINATOR)
    values ( '4eb37cd5-657a-48a2-93a1-a8e57a7ec220', 'Заявка на кредит', 'credit$CreditRequest', false, now(), USER, 1, 1);
    insert into DF_DOC_KIND (category_id, create_ts, created_by, version, doc_type_id, numerator_id, 
    numerator_type, category_attrs_place, tab_name, portal_publish_allowed, disable_add_process_actors, create_only_by_template)
    values ('4eb37cd5-657a-48a2-93a1-a8e57a7ec220', 'now()', 'admin', 1, '4b15d4f9-e7ee-457f-a4e1-d27969e703a6', 'd9e73b4c-0f67-45a3-b52b-22bc6a67175d', 
    1, 1, 'Ð”Ð¾Ð¿. Ð¿Ð¾Ð»Ñ�', false, false, false);
end if;return 0;
END;
$$
LANGUAGE plpgsql;
^
select baseInsert();
^
drop function if exists baseInsert()^
--Update process card_types for entity credit$CreditRequest
update wf_proc set card_types = regexp_replace(card_types, E',credit\\$CreditRequest', '') where code in ('Endorsement','Resolution','Acquaintance','Registration')^
update wf_proc set updated_by='admin', card_types = card_types || 'credit$CreditRequest,' where code in ('Endorsement','Resolution','Acquaintance','Registration')^
--Update security for entity credit$CreditRequest
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:create',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:update',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:delete',0,(select ID from SEC_ROLE where NAME = 'SimpleUser'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:create',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:update',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:delete',1,(select ID from SEC_ROLE where NAME = 'Administrators'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:create',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:update',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));
insert into SEC_PERMISSION (ID, CREATE_TS, CREATED_BY, VERSION, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, PERMISSION_TYPE, TARGET, VALUE, ROLE_ID) values (newid(),now(),'system',1,now(),null,null,null,20,'credit$CreditRequest:delete',1,(select ID from SEC_ROLE where NAME = 'doc_initiator'));
-- begin TM_TASK
alter table TM_TASK add column CREDIT_APPLICATION_ID uuid ^
-- end TM_TASK
-- begin DF_BANK
alter table DF_BANK add column THE_MAXIMUM_NUMBER_OF_LOANS_FROM_THE_BORROWER integer not null default 0 ^
-- end DF_BANK
