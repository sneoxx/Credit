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
    VALUES ('d9e73b4c-0f67-45a3-b52b-22bc6a67175d', now(), 'system', 1, 'CreditRequestNumerator', 'REQ-[number]', FALSE, 'Y', 1,
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
