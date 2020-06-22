create table usuario (
    codigo serial not null,
    nome varchar(80) not null unique,
    login varchar(40) not null unique,
    senha varchar(255) not null,
    primary key(codigo)
);
alter table usuario owner to odousys;


CREATE TABLE dentista (
    codigo serial NOT NULL,
    nome varchar(60) NOT NULL,
    cpf varchar(14),
    cro varchar(8),
    endereco varchar(60),
    cidade varchar(30),
    estado varchar(2),
    cep varchar(10),
    telefone varchar(13),
    celular varchar(14),
    email varchar(60),
    primary key(codigo)
);
ALTER TABLE dentista OWNER TO odousys;


CREATE TABLE paciente (
    codigo serial NOT NULL,
    nome varchar(60) NOT NULL,
    cpf varchar(14),
    endereco varchar(60),
    cidade varchar(30),
    estado varchar(2),
    cep varchar(10),
    telefone varchar(13),
    celular varchar(14),
    email varchar(60),
    primary key(codigo)
);
ALTER TABLE paciente OWNER TO odousys;


CREATE TABLE horarios_agenda (
    hora time without time zone NOT NULL,
    primary key(hora)
);
ALTER TABLE horarios_agenda OWNER TO odousys;


CREATE TABLE agenda (
    data date NOT NULL,
    hora time without time zone NOT NULL,
    cod_dentista int,
    cod_paciente int,
    anotacao varchar(120),
    feito boolean DEFAULT false,
    primary key(data, hora, cod_dentista),
    foreign key(cod_dentista) references dentista(codigo),
    foreign key(cod_paciente) references paciente(codigo)
);
ALTER TABLE agenda OWNER TO odousys;


CREATE TABLE status (
    codigo int NOT NULL,
    nome varchar(20) NOT NULL,
    primary key(codigo)
);
ALTER TABLE status OWNER TO odousys;


create table tabela_preco (
    codigo serial not null,
    nome varchar(30) not null unique,
    ativo boolean default false,
    primary key(codigo)
);
ALTER TABLE tabela_preco OWNER TO odousys;

CREATE TABLE preco (
    codigo serial NOT NULL,
    cod_tabela_preco int not null,
    nome varchar(80) not null,
    valor numeric(14,2) NOT NULL,
    primary key(codigo),
    foreign key(cod_tabela_preco) references tabela_preco(codigo)
);
ALTER TABLE preco OWNER TO odousys;


CREATE TABLE orcamento (
    codigo serial NOT NULL,
    data date NOT NULL,
    cod_tabela_preco int not null,
    cod_dentista int not null,
    cod_paciente int not null,
    porcentagem_desconto numeric(14,2) DEFAULT 0,
    valor_desconto numeric(14,2) DEFAULT 0,
    valor_final numeric(14,2) NOT NULL,
    cod_status integer DEFAULT 1 NOT NULL,
    observacao text,
    primary key(codigo),
    foreign key(cod_tabela_preco) references tabela_preco(codigo),
    foreign key(cod_dentista) references dentista(codigo),
    foreign key(cod_paciente) references paciente(codigo),
    foreign key(cod_status) references status(codigo)
);
ALTER TABLE orcamento OWNER TO odousys;


CREATE TABLE tipo_pagamento (
    codigo serial NOT NULL,
    nome varchar(20) NOT NULL,
    primary key(codigo)
);
ALTER TABLE tipo_pagamento OWNER TO odousys;


CREATE TABLE pagamento (
    codigo serial NOT NULL,
    data date NOT NULL,
    cod_paciente int NOT NULL,
    cod_tipo_pagamento int NOT NULL,
    cod_status integer DEFAULT 1 NOT NULL,
    valor numeric(14,2) NOT NULL,
    data_pagamento date,
    observacao text,
    primary key(codigo),
    foreign key(cod_paciente) references paciente(codigo),
    foreign key(cod_tipo_pagamento) references tipo_pagamento,
    foreign key(cod_status) references status(codigo)
);
ALTER TABLE pagamento OWNER TO odousys;

-- Inserts --

insert into usuario(nome, login, senha) values ('Administrador', 'admin', '$2a$10$JUl9f9PEtMxmn9n4QBCwfuOaa2hAwdY8Kjr3sDvRoPrm98R/lTnkm');

insert into tabela_preco(nome, ativo) values ('Tabela 2020', true);

insert into horarios_agenda(hora) values ('08:00:00');
insert into horarios_agenda(hora) values ('08:30:00');
insert into horarios_agenda(hora) values ('09:00:00');
insert into horarios_agenda(hora) values ('09:30:00');
insert into horarios_agenda(hora) values ('10:00:00');
insert into horarios_agenda(hora) values ('10:30:00');
insert into horarios_agenda(hora) values ('11:00:00');
insert into horarios_agenda(hora) values ('11:30:00');
insert into horarios_agenda(hora) values ('12:00:00');
insert into horarios_agenda(hora) values ('12:30:00');
insert into horarios_agenda(hora) values ('13:00:00');
insert into horarios_agenda(hora) values ('13:30:00');
insert into horarios_agenda(hora) values ('14:00:00');
insert into horarios_agenda(hora) values ('14:30:00');
insert into horarios_agenda(hora) values ('15:00:00');
insert into horarios_agenda(hora) values ('15:30:00');
insert into horarios_agenda(hora) values ('16:00:00');
insert into horarios_agenda(hora) values ('16:30:00');
insert into horarios_agenda(hora) values ('17:00:00');
insert into horarios_agenda(hora) values ('17:30:00');
insert into horarios_agenda(hora) values ('18:00:00');
insert into horarios_agenda(hora) values ('18:30:00');
insert into horarios_agenda(hora) values ('19:00:00');

insert into status values (1, 'Aberto');
insert into status values (2, 'Cancelado');
insert into status values (3, 'Finalizado');
