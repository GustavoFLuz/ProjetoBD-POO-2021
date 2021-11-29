DROP DATABASE IF EXISTS ProjetoBDPOO;
create database ProjetoBDPOO;
use ProjetoBDPOO;

create table heroi(
	id int auto_increment,
    nome varchar(45) unique,
	vida int,
    classe int,
    ouro int,
    danoarma int,
    raridadearma varchar(10),

    primary key(id)
);

create table inventario(
	id_heroi int primary key,
    pocoesvida int,
    pocoesdano int,
    pocoesdefesa int,
    
    foreign key(id_heroi) references heroi(id) on delete cascade on update cascade
);

create table encontraveis(
	id int auto_increment,
    nome varchar(45) unique not null,
    tipo varchar(45),
    
    primary key(id)
);

create table inimigo(
	id int auto_increment primary key,
	id_encontravel int,
    vida int,
    dano int,
    defesa int,
	recompensa_min int,
    recompensa_max int,
    
    foreign key(id_encontravel) references encontraveis(id) on delete cascade on update cascade
);

create table tesouro(
	id_encontravel int,
	recompensa int,
    foreign key(id_encontravel) references encontraveis(id) on delete cascade on update cascade
);


create table logs(
	idLogs int auto_increment,
    nome_heroi varchar(45),
    nome_encontravel varchar(45),
    ganho int,
    
    primary key(idLogs),
    foreign key(nome_heroi) references heroi(nome) on delete cascade on update cascade,
    foreign key(nome_encontravel) references encontraveis(nome) on delete set null on update cascade
);