# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table formulario_empresa (
  id                            bigint auto_increment not null,
  nombre                        varchar(255),
  codigo_cfi                    varchar(255),
  direccion                     varchar(255),
  telefono_contacto             varchar(255),
  correo_empresa                varchar(255),
  perfil_empresarial            varchar(255),
  estado_contrataciones         varchar(255),
  otras_contrataciones          varchar(255),
  constraint pk_formulario_empresa primary key (id)
);

create table formulario_estudiante (
  id                            bigint auto_increment not null,
  fecha_nacimiento              varchar(255),
  nombre                        varchar(255),
  primer_apellido               varchar(255),
  segundo_apellido              varchar(255),
  cedula                        varchar(255),
  correo                        varchar(255),
  estado_civil                  varchar(255),
  pais_nacimiento               varchar(255),
  lugar_residencia              varchar(255),
  direccion                     varchar(255),
  telefono_casa                 varchar(255),
  telefono_movil                varchar(255),
  licencia                      varchar(255),
  perfil_personal               varchar(255),
  profesion                     varchar(255),
  perfil_profesional            varchar(255),
  anos_experiencia              varchar(255),
  empresa                       varchar(255),
  puesto                        varchar(255),
  anos_trabajo                  varchar(255),
  titulo                        varchar(255),
  institucion                   varchar(255),
  idiomas                       varchar(255),
  otros_titulos                 varchar(255),
  estado_laboral                varchar(255),
  ano_ingreso_formal            varchar(255),
  ano_final_formal              varchar(255),
  traslado                      varchar(255),
  genero                        varchar(255),
  registro_usuario_id           bigint,
  constraint uq_formulario_estudiante_registro_usuario_id unique (registro_usuario_id),
  constraint pk_formulario_estudiante primary key (id)
);

create table registro_empresa (
  id                            bigint auto_increment not null,
  nombre                        varchar(255),
  cfi                           varchar(255),
  correo                        varchar(255),
  telefono                      integer,
  contrasenia                   varchar(255),
  password_hash                 varchar(255),
  creation_date                 datetime(6),
  constraint uq_registro_empresa_cfi unique (cfi),
  constraint pk_registro_empresa primary key (id)
);

create table registro_usuario (
  id                            bigint auto_increment not null,
  nombre                        varchar(255),
  correo                        varchar(255),
  contrasenia                   varchar(255),
  telefono                      integer,
  password_hash                 varchar(255),
  creation_date                 datetime(6),
  constraint pk_registro_usuario primary key (id)
);

alter table formulario_estudiante add constraint fk_formulario_estudiante_registro_usuario_id foreign key (registro_usuario_id) references registro_usuario (id) on delete restrict on update restrict;


# --- !Downs

alter table formulario_estudiante drop foreign key fk_formulario_estudiante_registro_usuario_id;

drop table if exists formulario_empresa;

drop table if exists formulario_estudiante;

drop table if exists registro_empresa;

drop table if exists registro_usuario;

