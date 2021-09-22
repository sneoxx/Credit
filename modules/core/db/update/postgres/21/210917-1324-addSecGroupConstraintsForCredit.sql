-- begin addSecGroupConstraintsForCredit
insert into SEC_CONSTRAINT (ID, CREATE_TS, CREATED_BY, ENTITY_NAME, JOIN_CLAUSE, WHERE_CLAUSE, GROUP_ID) values 
('c7c8e433-ddcc-45e3-b804-99bd169597d7', current_timestamp, 'admin', 'credit$Credit', ', ts$CardAcl acl', '{E}.id = acl.card.id and (acl.user.id = :session$userId or acl.global = true)', '8e6306e2-9e10-414a-b437-24c91ffef804')^

-- end addSecGroupConstraintsForCredit
