drop table if exists rates;
create table rates (
                       id bigint auto_increment,
                       rate varchar(100),
                       sell_currency varchar(10),
                       buy_currency varchar(10),
                       update_date timestamp DEFAULT CURRENT_TIMESTAMP (6),
                       primary key (id)
);