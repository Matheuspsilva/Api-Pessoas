create table pessoa (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	data_nascimento datetime not null,
	
	endereco_cidade_id bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	
	primary key (id)
) engine=InnoDB default charset=utf8;

