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
