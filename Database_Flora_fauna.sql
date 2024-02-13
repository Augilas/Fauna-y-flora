create database flora_fauna_utp
use flora_fauna_utp


--Tablas------------------------------------------

--Fauna
create table Fauna(
Cod_animal varchar(6)
constraint Cod_animal_Fauna_PK primary key,
Nombre_comun_animal varchar (20),
Nombre_cientifico_animal varchar(40),
Imagen_animal varbinary(max),
Fecha_visto_primero_animal date
)

--Comentarios fauna
create table Comentarios_fauna (
Cod_animal_comentarios varchar (6)
constraint Cod_animal_Comentarios_fauna_FK foreign key
references Fauna(Cod_animal),
Comentario_animal varchar(500),
Fecha_comentario_fauna datetime,
constraint Cod_animal_Comentario_animal_Comentarios_fauna_PK primary key
(Cod_animal_comentarios, Comentario_animal),
Cod_usuario int 
constraint Comentarios_fauna_cod_usuariofk foreign key references Usuario(Cod_usuario)
)

--Flora
create table Flora (
Cod_flora varchar(6)
constraint Cod_flora_Flora_PK primary key,
Nombre_comun_flora varchar(20),
Nombre_cientifico_flora varchar(40),
Imagen_flora varbinary(max),
Fecha_visto_primero_flora date
)

--Comentarios flora
create table Comentarios_flora (
Cod_flora_comentarios varchar (6)
constraint Cod_flora_Comentarios_flora_FK foreign key
references Flora(Cod_flora),
Comentario_flora varchar(500),
Fecha_comentario_flora datetime
constraint Cod_flora_Comentario_flora_Comentarios_flora_PK primary key
(Cod_flora_comentarios, Comentario_flora),
Cod_usuario int 
constraint Comentarios_flora_cod_usuariofk foreign key references Usuario(Cod_usuario)

)

--Lugar
create table Lugar (
Cod_lugar int identity
constraint Cod_lugar_Lugar_PK primary key,
Nombre_lugar varchar(50)
)

--Lugar fauna
Create table Lugar_fauna_estudiante (
Cod_lugar_fauna int
constraint Cod_lugar_fauna_Lugar_Fauna_FK foreign key
references Lugar(Cod_lugar),
Cod_animal_lugar_fauna varchar(6)
constraint Cod_animal_Lugar_fauna_FK foreign key
references Fauna(Cod_animal),
Cedula_estudiante_lugar_fauna varchar(14)
constraint Cedula_estudiante_lugar_fauna_FK foreign key
references Estudiante(Cedula_estudiante),
constraint Lugar_fauna_estudiante_PK primary key (Cod_lugar_fauna, Cod_animal_Lugar_fauna, Cedula_estudiante_lugar_fauna)
)

--Lugar flora
create table Lugar_flora_estudiante (
Cod_lugar_flora int
constraint Cod_lugar_flora_Lugar_Flora_FK foreign key
references Lugar(Cod_lugar),
Cod_flora_Lugar_flora varchar(6)
constraint Cod_flora_Lugar_flora_FK foreign key
references Flora(Cod_flora),
Cedula_estudiante_lugar_flora varchar(14)
constraint Cedula_estudiante_lugar_flora_FK foreign key
references Estudiante(Cedula_estudiante)
constraint Lugar_flora_estudiante_PK primary key (Cod_lugar_flora, Cod_flora_Lugar_flora, Cedula_estudiante_lugar_flora)
)


/*-----------------------------USUARIOS--------------------------*/
CREATE TABLE Usuario (
    Cod_usuario INT IDENTITY CONSTRAINT Cod_usuario_PK PRIMARY KEY,
    Usuario VARCHAR(30) 
	constraint Usuario_usuario_UK unique,
    Contrasena VARCHAR(80),
    Creado DATE DEFAULT GETDATE(),
    Cedula_biologo VARCHAR(14),
    Cedula_admin VARCHAR(14),
    Cedula_estudiante VARCHAR(14),
	cod_tipo int
	constraint cod_tipo_Usuario_FK foreign key
	references Tipo_usuario (cod_tipo),
    CONSTRAINT Usuario_Biologo_FK FOREIGN KEY (Cedula_biologo) REFERENCES Biologo(Cedula_biologo),
    CONSTRAINT Usuario_Administrador_FK FOREIGN KEY (Cedula_admin) REFERENCES Administrador(Cedula_admin),
    CONSTRAINT Usuario_Estudiante_FK FOREIGN KEY (Cedula_estudiante) REFERENCES Estudiante(Cedula_estudiante)
)

--Tipo de usuario
create table Tipo_usuario (
cod_tipo int
constraint Cod_tipo_Tipo_usuario_PK primary key,

Tipo varchar(14)
)

--Estudiante
create table Estudiante (
Cedula_estudiante varchar(14)
constraint Cedula_estudiante_Estudiante_PK primary key
	constraint Estudiante_Cedula_estudiante_ck
	check ((Cedula_estudiante like '[0][1-9][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]') or
		   (Cedula_estudiante like '[1][0-3][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]') or
		   (Cedula_estudiante like '[2][0][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]')),

Nombre_estudiante varchar(20),

Apellido_estudiante varchar(30),
Telefono_estudiante varchar(9)
	constraint Telf_Estud_Telefono_ck
	check ((Telefono_estudiante like '[23479][0-9][0-9][-][0-9][0-9][0-9][0-9]') or (Telefono_estudiante like '[6][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9]')),


Correo_estudiante varchar(50)
constraint Correo_estudiante_CK
check (Correo_estudiante like '%@utp.ac.pa'),

cod_tipo int constraint Estudiante_cod_tipo_fk foreign key (cod_tipo)
references Tipo_usuario (cod_tipo)
)

--Biologo
create table Biologo (
Cedula_biologo varchar(14)
constraint Cedula_biologo_Biologo_PK primary key
	constraint Biologo_Cedula_Biologo_ck
	check ((Cedula_biologo like '[0][1-9][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]') or
		   (Cedula_biologo like '[1][0-3][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]') or
		   (Cedula_biologo like '[2][0][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]')),

Nombre_biologo varchar(20),

Apellido_biologo varchar(30),
Telefono_biologo varchar(9)
	constraint Telf_biologo_Telefono_ck
	check ((Telefono_biologo like '[23479][0-9][0-9][-][0-9][0-9][0-9][0-9]') or (Telefono_biologo like '[6][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9]')),
Correo_biologo varchar(50)
constraint Correo_biologo_CK
check (Correo_biologo like '%@utp.ac.pa'),

cod_tipo int constraint Biologo_cod_tipo_fk foreign key (cod_tipo)
references Tipo_usuario (cod_tipo)
)

--Biologo_animal
create table Biologo_fauna (
Cod_animal_M varchar(6)
constraint Cod_animal_M_FK foreign key
references Fauna(Cod_animal),
Cedula_biologo_Fauna varchar(14)
constraint Cedula_biologo_Fauna_FK foreign key
references Biologo(Cedula_biologo),
constraint Cod_animal_Cedula_Biologo_Biologo_Fauna_PK primary key (Cod_animal_M, Cedula_biologo_Fauna)
)


--Biologo_flora
create table Biologo_flora (
Cod_flora_M varchar(6)
constraint Cod_flora_M_FK foreign key
references Flora(Cod_flora),
Cedula_biologo_Flora varchar(14)
constraint Cedula_biologo_Flora_FK foreign key
references Biologo(Cedula_biologo),
constraint Cod_flora_Cedula_Biologo_Biologo_Flora_PK primary key (Cod_flora_M, Cedula_biologo_Flora)
)

--Administrador
create table Administrador(
Cedula_admin varchar(14)
constraint Cedula_admin_Administrador_PK primary key
	constraint Administrador_Cedula_Administrador_ck
	check ((Cedula_admin like '[0][1-9][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]') or
		   (Cedula_admin like '[1][0-3][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]') or
		   (Cedula_admin like '[2][0][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][0-9][0-9]')),

Nombre_admin varchar(20),

Apellido_admin varchar(30),

Correo_admin varchar(50)
constraint Correo_admin_CK
check (Correo_admin like '%@utp.ac.pa'),

cod_tipo int constraint 
foreign key (cod_tipo)
references Tipo_usuario (cod_tipo)
)


--Token
create table Token (
	correo varchar(100),
	token varchar(100),
	constraint Token_correo_token_PK primary key (correo, token)
)

--Procesos------------------------------------------------------

--Creación de secuencias
create sequence no_animal
	start with 1000
	increment by 1

create sequence no_flora
	start with 1000
	increment by 1

--Trigger para cod_fauna
CREATE TRIGGER Insertar_fauna
ON Fauna
INSTEAD OF INSERT
AS
BEGIN
    DECLARE
        @codigo_animal varchar(6),
        @nombre_animal varchar(30),
        @imagen_animal varbinary(max),
        @no_secuencia INT;

    SELECT
        @nombre_animal = inserted.Nombre_comun_animal,
        @imagen_animal = inserted.Imagen_animal
    FROM
        inserted;

    IF NOT EXISTS (SELECT 1 FROM Fauna WHERE Nombre_comun_animal = @nombre_animal)
    BEGIN
        SET @no_secuencia = NEXT VALUE FOR no_animal;
        SET @codigo_animal = CONCAT('1', '-', @no_secuencia);

        INSERT INTO Fauna (Cod_animal, Nombre_comun_animal, Imagen_animal, Fecha_visto_primero_animal)
        VALUES (@codigo_animal, @nombre_animal, @imagen_animal, GETDATE());
    END
    ELSE
    BEGIN
        PRINT 'Este animal ya se encuentra en nuestros registros. ¿Deseas escribir algún comentario?';
    END
END;

--Trigger para cod_flora
CREATE TRIGGER Insertar_flora
ON Flora
INSTEAD OF INSERT
AS
BEGIN
    DECLARE
        @codigo_flora varchar(6),
        @nombre_flora varchar(30),
        @imagen_flora varbinary(max),
        @no_secuencia INT;

    SELECT
        @nombre_flora = inserted.Nombre_comun_flora,
        @imagen_flora = inserted.Imagen_flora
    FROM
        inserted;

    IF NOT EXISTS (SELECT 1 FROM Flora WHERE Nombre_comun_flora = @nombre_flora)
    BEGIN
        SET @no_secuencia = NEXT VALUE FOR no_flora;
        SET @codigo_flora = CONCAT('2', '-', @no_secuencia);

        INSERT INTO Flora (Cod_flora, Nombre_comun_flora, Imagen_flora, Fecha_visto_primero_flora)
        VALUES (@codigo_flora, @nombre_flora, @imagen_flora, GETDATE());
    END
    ELSE
    BEGIN
        PRINT 'Esta planta ya se encuentra en nuestros registros. ¿Deseas escribir algún comentario?';
    END
END;

--Trigger para comentario fauna
create trigger comentario_fauna
on Comentarios_fauna
instead of insert
	as
	begin
	declare 
	@cod_animal varchar(6),
	@Comentario_animal varchar(500),
	@Fecha_comentario datetime,
	@Cod_usuario int;
	SELECT
        @cod_animal = inserted.Cod_animal_comentarios,
        @Comentario_animal = inserted.Comentario_animal,
		@Cod_usuario = inserted.Cod_usuario
    FROM
        inserted;

			INSERT INTO Comentarios_fauna (Cod_animal_comentarios, Comentario_animal, Fecha_comentario_fauna, Cod_usuario)
			VALUES (@cod_animal, @Comentario_animal, GETDATE(), @Cod_usuario)
	end


--Trigger para comentario flora
create trigger comentario_flora
on Comentarios_flora
instead of insert
as
	begin
	declare 
	@cod_flora varchar(6),
	@Comentario_flora varchar(500),
	@Fecha_comentario datetime,
	@Cod_usuario int;
	SELECT
        @cod_flora = inserted.Cod_flora_comentarios,
        @Comentario_flora = inserted.Comentario_flora,
		@Cod_usuario = inserted.Cod_usuario
    FROM
        inserted;

			INSERT INTO Comentarios_flora (Cod_flora_comentarios, Comentario_flora, Fecha_comentario_flora, Cod_usuario)
			VALUES (@cod_flora, @Comentario_flora, GETDATE(), @Cod_usuario)
	end

--Registro estudiante
CREATE PROCEDURE InsertarUsuario
    @nombre_estudiante varchar(20),
    @apellido_estudiante varchar(30),
    @cedula varchar(14),
    @correo varchar(50),
    @usuario varchar(30),
    @password varchar(80)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION; 
        IF NOT EXISTS (SELECT 1 FROM usuario WHERE Usuario = @usuario) AND NOT EXISTS (SELECT 1 FROM Estudiante WHERE Correo_estudiante = @correo OR Cedula_estudiante = @cedula)
        BEGIN
            INSERT INTO Estudiante (Cedula_estudiante, Nombre_estudiante, Apellido_estudiante, Correo_estudiante)
            VALUES (@cedula, @nombre_estudiante, @apellido_estudiante, @correo);

            INSERT INTO Usuario (Usuario, Contrasena, Cod_tipo, Cedula_estudiante)
            VALUES (@usuario, @password, 1, @cedula);

            COMMIT TRANSACTION; 
        END
        ELSE
        BEGIN
            PRINT 'Error: User already exists';
        END
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION; 
    END CATCH
END;

GRANT EXECUTE ON OBJECT::InsertarUsuario to FloraFauna

CREATE PROCEDURE BuscarUsuario
    @correo VARCHAR(50),
    @encontrado BIT OUTPUT 
AS
BEGIN
    SET @encontrado = 0 

    IF EXISTS(SELECT * FROM Biologo WHERE Correo_biologo = @correo)
        SET @encontrado = 1 
    ELSE IF EXISTS(SELECT * FROM Administrador WHERE correo_admin = @correo)
        SET @encontrado = 1 
    ELSE
        BEGIN
            IF EXISTS(
                SELECT * FROM Usuario u
                JOIN Estudiante e ON u.Cedula_estudiante = e.Cedula_estudiante
                WHERE e.Correo_estudiante = @correo
            )
                SET @encontrado = 1 -- Se encontró el correo en la tabla Usuario
        END
END

GRANT EXECUTE ON dbo.BuscarUsuario TO FloraFauna;



--Cambio contrasena (modificar)
create procedure CambioContrasena
@token varchar(100),
@contrasena VARCHAR(80)
as
begin
	declare @correo varchar(50)
	declare @cedula varchar(15)
	select @correo = correo from Token where token = @token

	IF EXISTS(SELECT * FROM Biologo WHERE Correo_biologo = @correo)
		begin
		select @cedula = Cedula_biologo from Biologo where Correo_biologo = @correo
		update Usuario
		set Contrasena = @contrasena
		where Cedula_biologo = @cedula
		delete from Token
		where correo = @correo
		end
    ELSE IF EXISTS(SELECT * FROM Administrador WHERE correo_admin = @correo)
		begin
		select @cedula = Cedula_admin from Administrador where Correo_admin = @correo
		update Usuario
		set Contrasena = @contrasena
		where Cedula_admin = @cedula
		delete from Token
		where correo = @correo
		end
    ELSE
		begin
		select @cedula = Cedula_estudiante from Estudiante where Correo_estudiante = @correo
		update Usuario
		set Contrasena = @contrasena
		where Cedula_estudiante = @cedula
		delete from Token
		where correo = @correo
		end
end

GRANT EXECUTE ON dbo.CambioContrasena TO FloraFauna;

CREATE PROCEDURE ObtenerCodigoConMasComentarios
    @mes INT,
    @año INT,
	@nombre varchar(40) OUTPUT
AS
BEGIN
	declare @Cod_animal varchar(6), @Cod_flora varchar(6)
	declare @Cantidad_animal INT = 0, @Cantidad_flora INT = 0
    SET NOCOUNT ON;

    -- Para Comentarios_fauna
    SELECT TOP 1
        @Cod_animal = Cod_animal_comentarios,
        @Cantidad_animal = COUNT(*)
    FROM 
        Comentarios_fauna
    WHERE
        MONTH(Fecha_comentario_fauna) = @mes
        AND YEAR(Fecha_comentario_fauna) = @año
    GROUP BY 
        Cod_animal_comentarios
    ORDER BY 
        COUNT(*) DESC;

    -- Para Comentarios_flora
    SELECT TOP 1
        @Cod_flora = Cod_flora_comentarios,
        @Cantidad_flora = COUNT(*)
    FROM 
        Comentarios_flora
    WHERE
        MONTH(Fecha_comentario_flora) = @mes
        AND YEAR(Fecha_comentario_flora) = @año
    GROUP BY 
        Cod_flora_comentarios
    ORDER BY 
        COUNT(*) DESC;

	if (@Cantidad_animal > @Cantidad_flora) 
		select @nombre = Nombre_comun_animal from Fauna where Cod_animal = @Cod_animal
	else if (@Cantidad_flora > @Cantidad_animal)
		select @nombre = Nombre_comun_flora from Flora where Cod_flora = @Cod_flora
	else 
		set @nombre = 'No Existe Registro'

END

GRANT EXECUTE ON dbo.ObtenerCodigoConMasComentarios TO FloraFauna;

alter table Fauna
add Descripcion_cientifica_fauna varchar(500)

alter table Flora
add Descripcion_cientifica_flora varchar(500)

UPDATE Flora
SET Descripcion_cientifica_flora = 'El agutí centroamericano, también conocido como guaqueque, sereque ​, cotuza, guatín, ñeque, jochi colorado, cherenga, guatusa, sihuayro, carma, picure o añuje es una especie de roedor de la familia Dasyproctidae. '
WHERE Cod_flora = '2-1000';

--- Tipos de usuarios
insert into Tipo_usuario
values (1, 'Usuario'),
		(2, 'Biologo'),
		(4, 'Administrador')


insert into Lugar
values ('sendero'),
		('aljibe'), 
		('parque pitagora'), 
		('chato park'), 
		('cancha fut'), 
		('cancha baloncesto'), 
		('escalera'), 
		('edificio1'),
		('edificio2'),
		('edificio3'),
		('edificio4'),
		('edicio de labs')

--TRIGERS CRUD

--Borrar Animal
create trigger Borrar_fauna 
on Fauna
INSTEAD OF delete
as
	begin
	DECLARE @codigo_animal varchar(6)

	select @codigo_animal = deleted.Cod_animal from deleted

	Delete from Comentarios_fauna where Cod_animal_comentarios = @codigo_animal
	Delete from Lugar_fauna_estudiante where Cod_animal_lugar_fauna = @codigo_animal
	Delete from Fauna where Cod_animal = @codigo_animal
	end
--Borrar Planta
create trigger Borrar_flora
on Flora
INSTEAD OF delete
as
	begin
	DECLARE @codigo_flora varchar(6)

	select @codigo_flora = deleted.Cod_flora from deleted

	Delete from Comentarios_flora where Cod_flora_comentarios = @codigo_flora
	Delete from Lugar_flora_estudiante where Cod_flora_Lugar_flora = @codigo_flora
	Delete from Flora where Cod_flora = @codigo_flora
	end

--Borrar TipoUsuario
create trigger Borrar_TipoUsuario
on Tipo_usuario
INSTEAD OF delete
as
	begin
	DECLARE @codigo varchar(6)

	select @codigo = deleted.cod_tipo from deleted

	ALTER TABLE [dbo].[Usuario]
	NOCHECK CONSTRAINT cod_tipo_Usuario_FK
	ALTER TABLE [dbo].[Estudiante]
	NOCHECK CONSTRAINT Estudiante_cod_tipo_fk
	ALTER TABLE [dbo].[Biologo]
	NOCHECK CONSTRAINT Biologo_cod_tipo_fk
	ALTER TABLE [dbo].[Administrador]
	NOCHECK CONSTRAINT Administrador_cod_tipo_fk

	delete From Tipo_usuario where cod_tipo = @codigo and cod_tipo not in(1,2,3)

	ALTER TABLE [dbo].[Usuario]
	CHECK CONSTRAINT cod_tipo_Usuario_FK
	ALTER TABLE [dbo].[Estudiante]
	CHECK CONSTRAINT Estudiante_cod_tipo_fk
	ALTER TABLE [dbo].[Biologo]
	CHECK CONSTRAINT Biologo_cod_tipo_fk
	ALTER TABLE [dbo].[Administrador]
	CHECK CONSTRAINT Administrador_cod_tipo_fk

	end

--Borrar Usuario
create trigger Borrar_Usuario
on Usuario
INSTEAD OF delete
as
	begin
	DECLARE @codigo varchar(6)

	select @codigo = deleted.Cod_usuario from deleted

	Delete from Comentarios_fauna where Cod_usuario = @codigo
	Delete from Comentarios_flora where Cod_usuario = @codigo
	
	Delete from Usuario where Cod_usuario = @codigo

	end

--Guardar Usuario
CREATE PROCEDURE InsertarUsuarioNuevo
    @cod_tipo int,
    @cedula varchar(14),
    @usuario varchar(30),
    @password varchar(80)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION; 
        IF EXISTS (SELECT 1 FROM Estudiante WHERE Cedula_estudiante = @cedula)
			BEGIN
				INSERT INTO Usuario (Usuario, Contrasena, Cod_tipo, Cedula_estudiante)
				VALUES (@usuario, @password, @cod_tipo, @cedula);

				COMMIT TRANSACTION; 
			END
        ELSE IF EXISTS (SELECT 1 FROM Biologo WHERE Cedula_biologo = @cedula)
			BEGIN
				INSERT INTO Usuario (Usuario, Contrasena, Cod_tipo, Cedula_biologo)
				VALUES (@usuario, @password, @cod_tipo, @cedula);

				COMMIT TRANSACTION; 
			END
		ELSE IF EXISTS (SELECT 1 FROM Administrador WHERE Cedula_admin = @cedula)
			BEGIN
				INSERT INTO Usuario (Usuario, Contrasena, Cod_tipo, Cedula_admin)
				VALUES (@usuario, @password, @cod_tipo, @cedula);

				COMMIT TRANSACTION; 
			END
		ELSE 
			BEGIN
				PRINT 'Error: User not found';
				COMMIT TRANSACTION; 
			END
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION; 
    END CATCH
END;

GRANT EXECUTE ON OBJECT::InsertarUsuarioNuevo to FloraFauna

--Borrar Lugar
create trigger Borrar_lugar
on Lugar
INSTEAD OF delete
as
	begin
	DECLARE @codigo varchar(6)

	select @codigo = deleted.Cod_lugar from deleted
	ALTER TABLE [dbo].[Lugar_flora_estudiante]
	NOCHECK CONSTRAINT Cod_lugar_flora_Lugar_Flora_FK
	ALTER TABLE [dbo].[Lugar_fauna_estudiante]
	NOCHECK CONSTRAINT Cod_lugar_fauna_Lugar_Fauna_FK

	delete Lugar where Cod_lugar = @codigo

	ALTER TABLE [dbo].[Lugar_flora_estudiante]
	CHECK CONSTRAINT Cod_lugar_flora_Lugar_Flora_FK
	ALTER TABLE [dbo].[Lugar_fauna_estudiante]
	CHECK CONSTRAINT Cod_lugar_fauna_Lugar_Fauna_FK
	end