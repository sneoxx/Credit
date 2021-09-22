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
