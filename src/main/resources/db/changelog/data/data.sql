INSERT INTO USERS(name, username, password, authority)
values ('Alex', 'agertha', '$2a$12$esDECVwtLvuW/cBpp88hp.KzbfDYkvEfnk4g.gOZH7cLdIE6r5X5a', 'ROLE_OWNER');

INSERT INTO acl_sid (id, principal, sid) VALUES
(1, false, 'ROLE_ADMIN'),
(2, true, 'dasha');

INSERT INTO acl_class (id, class) VALUES
(1, 'nechto.dto.AclGameDto');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, '1', null, 1, false);

INSERT INTO acl_entry (id, ace_order, acl_object_identity, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 2, true, true, true);


