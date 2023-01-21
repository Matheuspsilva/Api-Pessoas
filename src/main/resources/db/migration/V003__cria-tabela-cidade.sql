create table cidade (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	estado_id bigint not null,
	
	primary key(id)
)engine=InnoDB default charset=utf8;

alter table pessoa add constraint fk_pessoa_cidade foreign key (endereco_cidade_id) references cidade(id);

alter table cidade add constraint fk_cidade_estado foreign key (estado_id) references estado(id);
