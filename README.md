# Api Auth -  Create User
##### Esta API permite el registro de usuarios y validar el registro en la respuesta de la petición.
    
* ### **Compilar y ejecutar proyecto:**.
1. Compilar
```sh
gradle build
```
2. Ejecutar 

###### Nos colocamos en la ruta donde se genera el jar:
```sh
cd \apit_auth_sp\build\libs\
```
###### Ejecutamos con java el proyecto:
```java
java -jar api-auth-users-1.0.0.jar
```

###### Otra opción: Desde un editor, en mi caso lo realicé en el editor Intellij IDEA si es así puedes solo darle clic a el run del proyecto ya que tiene configurado un perfil con el nombre start:
<img src="src/main/resources/img/run.png" alt="img">

* ### **Ejecución de Test:**
```sh
gradle test --tests api-auth-users
```

* ### **Request y Response:**.
  
    *  ##### **Request**.
 * **name**: Campo en el que se permite nombre completo basado en la expresion regular:**^(?!-)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(?<!-)\@([a-zA-Z]+(\.[a-zA-Z]+))+$**
 * **email**: Campo que se permite un correo electronico basado en la expresion regular:*^(?!-)[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(?<!-)\@([a-zA-Z]+(\.[a-zA-Z]+))+$*
* **password**: Campo que se permite una contrasena segura basado en la expresion regular:**^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$*$** en los que se debe cumplir las siguientes caracteristica:
  * Al menos una letra mayúscula o minúscula
  * Al menos un dígito numérico
  * Al menos un carácter especial
  * La longitud mínima es de 8 caracteres
  * Al menos una letra mayúscula o minúscula
  * *Ejemplo password*: *MyStr0ngP@$$w0rd*

* **phones**: Campo en el que se permite un array con los numeros de telefonos a agregar completo, el mismo no tiene limite y es opcional

  **Ejemplo del Request**
   ```json
   {
    "name": "Darwin Vegas",
    "email": "darwinvegas1@gmail.com",
    "password": "Pa$$w0ddd!",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        },
        {
            "number": "4242727712",
            "citycode": "2",
            "contrycode": "58"
        },
        {
            "number": "987654321",
            "citycode": "3",
            "contrycode": "51"
        }
             ]
    }
   ```
  **Ejemplo del Response** 
    ```json
      {
    "datetime": "2024-04-22T01:15:31.971Z[UTC]",
    "status": {
        "code": "00",
        "message": "Successful User Create",
        "user": {
            "created": "2024-04-21T21:15:31.750216",
            "id": "40285c818f035f29018f035f5a090000",
            "isactive": "YES",
            "last_login": "2024-04-21T21:15:31.750216",
            "modified": "2024-04-21T21:15:31.750216",
            "phones": [
                {
                    "citycode": "1",
                    "contrycode": "57",
                    "number": "1234567"
                },
                {
                    "citycode": "2",
                    "contrycode": "58",
                    "number": "4242727712"
                },
                {
                    "citycode": "3",
                    "contrycode": "51",
                    "number": "987654321"
                }
                     ],
            "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXJ3aW52ZWdhczFAZ21haWwuY29tIiwiZXhwIjoxNzE0NjEyNTMxfQ.NhaZ_3Lbyeu-T6L3OXkUj6STIXlB3UKQjebwj_xN9-NgeihxxOTdqn-zYgqak3dEq0j87gj2LXiM2jA5QOEJvQ"
            }
        }
    }
    ```
* ### **SQL:**.
    * #### No aplica, ya que se usa JPA y se crean las tablas de manera automáticas, en la ejecución se tiene que ver las siguientes líneas
   ```sql
    Hibernate: drop table if exists admcons_log CASCADE 
    Hibernate: drop table if exists phones CASCADE 
    Hibernate: drop table if exists users CASCADE 
    Hibernate: drop sequence if exists seq_log
    Hibernate: create sequence seq_log start with 1 increment by 1
    Hibernate: create table admcons_log (log_id bigint not null, action varchar(20), client varchar(32), country varchar(20), email varchar(30), ip varchar(64), msg varchar(100), rc varchar(4), time_stamp timestamp, primary key (log_id))
    Hibernate: create table phones (id integer not null, citycode varchar(255), contrycode varchar(255) not null, id_user varchar(255) not null, number varchar(255) not null, primary key (id))
    Hibernate: create table users (id varchar(32) not null, active boolean, created timestamp, email varchar(100) not null, last_login timestamp, modified timestamp, name varchar(100) not null, primary key (id))
    ```
* ### **Swagger:**
  ###### /api-auth-users/v1/swagger-ui/index.html
  * ### **Swagger - Post Request:**.
  <img src="src/main/resources/img/post_request_creatUser.png" alt="img">
  
  * ### **Swagger - Post Request:**.
  <img src="src/main/resources/img/post_response_creatUser.png" alt="img">

* ### **Diagrama funcional:**.
    * ##### Disponible en: \src\main\resources\diagram\
  <img src="src/main/resources/diagram/diagrama.png" alt="img">

* ### **Tecnologías usadas:**
	* Java 11+
	* Spring Boot
	* Lombok
    * Swagger
	* JUnit Framework
	* Hamcrest
	* AssertJ
	* JsonPath
	* Mockito
	* IntelliJ IDEA
	* Gradle


  
  





