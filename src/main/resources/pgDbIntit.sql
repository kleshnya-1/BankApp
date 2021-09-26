create table if not exists banks
(
    id serial
    constraint banks_pkey
    primary key,
    name varchar(255),
    transfer_fee_in_percent double precision not null,
    transfer_fee_in_percent_for_not_natural_persons double precision not null
    );

alter table banks owner to postgres;

create table if not exists clients
(
    id serial
    constraint clients_pkey
    primary key,
    is_natural_person char not null,
    name varchar(255)
    );

alter table clients owner to postgres;

create table if not exists accounts
(
    id serial
    constraint accounts_pkey
    primary key,
    acc_number varchar(255),
    amount double precision not null,
    currency varchar(255),
    bank_id integer
    constraint fkb78evw9x9jyy66ld572kl8rgx
    references banks,
    client_id integer
    constraint fkgymog7firrf8bnoiig61666ob
    references clients
    );

alter table accounts owner to postgres;

create table if not exists transfer_history
(
    id serial
    constraint transfer_history_pkey
    primary key,
    acc_source_num varchar(255),
    acc_target_num varchar(255),
    amount double precision not null,
    bank_source_name varchar(255),
    bank_target_name varchar(255),
    client_source_name varchar(255),
    client_target_name varchar(255),
    currency varchar(255),
    date timestamp
    );

alter table transfer_history owner to postgres;

