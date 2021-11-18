create table item_pedido (preco float8, quantidade int4, pedido_id int4 not null, produto_id int4 not null, primary key (pedido_id, produto_id));
create table pedido (id int4 generated by default as identity, descricao varchar(255), endereco varchar(255), instante timestamp, latitude float8, longitude float8, payment_status varchar(255), status int4, user_id int8, primary key (id));
create table produto (id int4 generated by default as identity, descricao varchar(255), imagem_uri varchar(255), nome varchar(255), preco float8, primary key (id));
create table tb_role (id int8 generated by default as identity, description varchar(255), primary key (id));
create table tb_user (id int8 generated by default as identity, email varchar(255) not null, first_name varchar(255) not null, last_name varchar(255) not null, passwords varchar(255) not null, telephone varchar(255) not null, primary key (id));
create table tb_user_role (user_id int8 not null, role_id int8 not null, primary key (user_id, role_id));
alter table if exists tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email);
alter table if exists item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido;
alter table if exists item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto;
alter table if exists pedido add constraint FKiq31d668orseg8lbslg0kmvm4 foreign key (user_id) references tb_user;
alter table if exists tb_user_role add constraint FKea2ootw6b6bb0xt3ptl28bymv foreign key (role_id) references tb_role;
alter table if exists tb_user_role add constraint FK7vn3h53d0tqdimm8cp45gc0kl foreign key (user_id) references tb_user;
