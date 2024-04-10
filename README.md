# Creando una aplicación web con Spring Boot

> En este tutorial creará una pequeña aplicación web usando [Spring Boot](https://spring.io/projects/spring-boot): un conjunto de herramientas y librerías que facilitan el desarrollo de aplicaciones empresariales. 

---

## Objetivos de aprendizaje

En este tutorial, aprenderá a 
- Desarrollar una aplicación web usando Spring Boot
- Generar respuestas a peticiones HTTP GET
- Mostrar páginas HTML como respuesta a peticiones HTTP GET


## Requisitos previos

- Conocimientos básicos de programación, Java y bases de datos.
- Una instancia de [Gitpod](https://gitpod.io/) o un computador con [Docker Desktop](https://www.docker.com/products/docker-desktop/) y [Visual Studio Code](https://code.visualstudio.com/) instalado. 

---

## Paso a paso

### 1. Crear una copia del repositorio

- Navegue hasta el repositorio del proyecto en Github
    - [https://github.com/sw-labs/springboot-holamundo-tutorial](https://github.com/sw-labs/springboot-holamundo-tutorial)

- Cree una copia del repositorio usando el botón "Use this Template", la opción "Create a new repository"
    - asigne un nombre al nuevo repositorio

### 2. Abra el nuevo repositorio 

- Abra el repositorio que acaba de crear usando Gitpod o Visual Studio Code
    - Para usar Gitpod, en el navegador, agregue  `gitpod.io` al inicio de la ruta URL del repositorio

    ```
    # Si el URL es https://github.com/ejemplo/spring
    # Use en el navegador:
    
    gitpod.io#https://github.com/ejemplo/spring
    ```
    - Para usar Visual Studio Code, haga una copia del proyecto y use el comando `code` 

    ```
    # Si el repositorio es https://github.com/ejemplo/spring
    # Ejecute los comandos:

    git clone https://github.com/ejemplo/spring
    cd spring
    code . 
    ```


### 3. Cree un nuevo proyecto de Spring

- En Gitpod o Visual Studio Code, presiones [F1] para mostrar las opciones del editor
- Seleccione la opción  `Spring Initializr: Create a new Maven Project...`
- Seleccione la última opción de Spring Boot
- Seleccione el lenguaje Java
- Indique el nombre del grupo, algo similar al nombre de una empresa, por ejemplo "com.empresa"
- Indique el nombre del proyecto, por ejemplo "tutorial"
- Seleccione el tipo de paquete "jar"
- Seleccione la versión de Java "22"
- Seleccione las dependencias "Spring Web" y "Sprint Boot DevTools". Presione Enter cuando haya terminado de seleccionar las dependencias.
-  Indique la carpeta donde se grabará el proyecto. Use el nombre recomendado por el editor.

---

Al crear el proyecto se crea un proyecto Spring con una serie de clases y archivos.

Si se usó el nombre de empresa "com.empresa" y el nombre de proyecto "tutorial", debieron crearse una serie de archivos similares a los siguientes:

```
/demo
  /src
    /main
      /java                         <-- carpeta con código fuente java
        /com/empresa/tutorial       <-- paquete en java
          TutorialApplication.java  <-- clase en java
      
      /resources                    <-- carpeta con archivos adicionales
        /static
        /templates
        application.properties      <-- archivo de configuración
  
            :  (otros archivos)
``` 

**NOTA:** Los nombres pueden variar de acuerdo a los datos suministrados en la configuración.

---

### 4. Agregue un paquete para Controladores

> Los controladores son clases Java que responden a eventos y a solicitudes de los usuarios. Para el ejemplo, estos controladores van a responder a solicitudes web hechas con el protocolo HTTP

- Seleccione el paquete (la carpeta) donde se encuentra la clase `Application`. Para el ejemplo, seleccione el paquete `com.empresa.tutorial`
- Haga clic derecho y seleccione "New..." y la opción "Package"
- Indique el nombre del nuevo paquete. Por ejemplo `com.empresa.tutorial.controllers`

### 5. Agregue una clase Controladora REST

- Seleccione el paquete que acaba de crear
- Haga clic derecho y seleccione "New..." y la opción "Class"
- Indique el nombre de la clase `HolaMundoController`

### 6. Escriba el código para crear el controlador

> Los Controladores REST atienden solicitudes web hechas con el protocolo HTTP. Estos controladores pueden atender, por ejemplo, solicitudes GET que son enviadas por un navegador web para obtener una página de internet.

- Agregue una anotación `@RestController" a la clase
- Agregue un método `hola()` que tenga la anotación `@GetMapping` indicando que este método debe responder a la ruta `/`

  ```
  package com.empresa.tutorial.controllers;

  import org.springframework.web.bind.annotation.RestController;
  import org.springframework.web.bind.annotation.GetMapping;


  @RestController
  public class HolaMundoController {

      @GetMapping("/")
      public String hola() {
          return "Hola Mundo";
      }

  }
  ```

### 7. Ejecute la aplicación

- Desde línea de comandos, cambie el directorio actual al directorio del proyecto
  ```
  # cd <nombre de la carpeta con el proyecto>
  cd tutorial
  ```

- Ejecute la aplicación usando `mvn spring-boot:run`
  ```
  mvn spring-boot:run
  ```

### 8. Visualice el funcionamiento del controlador

- Use un navegador y observe el resultado de la aplicación
  - En Gitpod, apenas ejecute la aplicación, debe aparecer una ventana preguntando si se desea ver la página web que acaba de lanzar
  - Haga clic en "Open in browser" para ver el funcionamiento de la aplicación

### 9. Elabore un método que reciba un parámetro

> Las peticiones HTTP GET pueden enviar parámetros por medio del URL

- En la misma clase (o en otra anotada con `@RestController`) agregue un método que permita saludar a alguna persona. Defina un controlador para la ruta `/saludo` que reciba como parámetro el campo `nombre`.

  ```
    @GetMapping("/saludo")
    public String saludo(@RequestParam String nombre) {
        return "Hola " + nombre;
    }
  ```

- Visualice el resultado usando el navegador
  ```
  https://<ruta de la aplicación>/saludo?nombre=Jaime
  ```

### 10. Elabore un método que reciba dos parámetros

- En la misma clase (o en otra anotada con `@RestController`) agregue un método que permita sumar dos números. Defina un controlador para la ruta `/suma` que reciba como parámetros los campos `a` y `b`.

  ```
    @GetMapping("/suma")
    public String Suma(@RequestParam Integer a, @RequestParam Integer b) {
        return "" + (a + b);
    }
  ```

- Visualice el resultado usando el navegador
  ```
  https://<ruta de la aplicación>/suma?a=10&b=11
  ```

### 11. Elabore un método que reciba una variable de ruta y un parámetro

- En la misma clase (o en otra anotada con `@RestController`) agregue un método que permita reciba un valor en la ruta del URL y reciba un parámetro adicional. Defina un controlador para la ruta `/validar` que reciba una variable de ruta `clase` y un parámetros `id`.

  ```
    @GetMapping("/validar/{clase}")
    public String validar(@PathVariable String clase, @RequestParam String id) {
        return "validando la clase " + clase +  " y el id " + id;
    }
  ```

- Visualice el resultado usando el navegador
  ```
  https://<ruta de la aplicación>/validar/libro?id=10
  ```


  