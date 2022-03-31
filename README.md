# jbdc

Proyecto **JAVA** contra una Base de Datos **MySQL** para gestionar los alumnos mediante sentencias **CRUD** de SQL.


## Descripcion

En primera instancia se debe introducir un usuario y una contraseña valida para poder acceder al menu principal. Si los credenciales son incorrectos se volveran a pedir.
Una vez dentro, lo primero que se ve es un menu con 4 opciones mas la opcion de salir

![menu](screenshots/menu.PNG)

El usuario podra Listar los alumnos que estan registrados en la bbdd (opcion 1)

Insertar un nuevo alumno con los campos nombre e email (opcion 2)

Modificar un alumno existente en la bbdd, introduciendo su id y dando un nuevo nombre e email (opcion 3)

Borrar un alumno de la bbdd introduciendo una id (opcion 4)

Y salir del programa (opcion 0)


## Tecnologias que se han usado

- MySql
- Java 8
- Maven 4.0.0

## Montaje y ejecucion

Al ser un proyecto JAVA contra una BBDD vamos a necesitar un programa de gestion de BBDD, en nuestro caso hemo utilizado **MySQL Workbench**.

Un dato a tener en cuenta es tener bien el puerto de conexión con la BBDD, la conexion se especifica en la clase conexion.

```
	DriverManager.getConnection("jdbc:mysql://localhost:3306/clase?useSSL=false", "root", "root");
```

Para poder ejecutar el programa debemos loguearnos con el **[User: admin] [password: admin]**

## Diagrama BBDD

Disponemos de una bbdd en MySql. En este [enlace](https://github.com/elorrieta-errekamari-institutua/AppClase/blob/javier_ibon/clase.sql) se puede ver y descargar el script para montar en tu programa de gestion de BBDD.

A continuacion se muestra una imagen con la composicion de la tabla que utilizamos

![Estructura bbdd](screenshots/tablabbdd.PNG)



## Versiones

La version actual del proyecto es la inicial, 1.0.0.


