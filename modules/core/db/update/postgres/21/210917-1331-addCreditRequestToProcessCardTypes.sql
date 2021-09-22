--Update process card_types for entity credit$CreditRequest
update wf_proc set card_types = regexp_replace(card_types, E',credit\\$CreditRequest', '') where code in ('Endorsement','Resolution','Acquaintance','Registration')^
update wf_proc set updated_by='admin', card_types = card_types || 'credit$CreditRequest,' where code in ('Endorsement','Resolution','Acquaintance','Registration')^
