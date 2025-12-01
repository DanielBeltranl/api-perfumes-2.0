# 🛍️ Arquitectura de Perfulandia: Microservicios de Venta y Pagos

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.java.com/)
[![Spring-Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Build-Maven-orange.svg)](https://maven.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue.svg)](https://www.postgresql.org/)

## 📝 Visión General del Sistema

El sistema **Perfulandia** implementa una arquitectura de **Microservicios** diseñada para gestionar el ciclo completo de una venta. Desde el registro de la **Orden de Compra** hasta la **Facturación** y el **Envío** del producto, cada funcionalidad se maneja mediante un servicio o rama especializada.

La estructura del repositorio permite que cada rama/microservicio pueda ser analizada de forma independiente para comprender su estructura y dependencias.

---

## 🧱 Microservicios y Requerimientos Funcionales (RF)

La siguiente tabla resume las funcionalidades clave que componen el flujo de ventas, identificando la rama o servicio responsable:

| ID | Microservicio / Módulo | Título de la Funcionalidad | Descripción de las Operaciones |
| :---: | :--- | :--- | :--- |
| **RF1** | `api-orden-compra-v1` | **Ingreso de Órdenes de Compra** | Registro de órdenes con cliente, dirección, teléfono, productos y precios. Persistencia de datos. |
| **RF2** | `api-usuario-v1` | **Login de Acceso** | Flujo de autenticación. Formulario de inicio de sesión, validación de credenciales y acceso al menú principal de la aplicación. |
| **RF3** | *Home/Front* | **Menú Principal** | Interfaz de usuario para la navegación: Listar órdenes, acceder a la vista principal (Home) y la función de cerrar sesión. |
| **RF4** | `api-facturacion-v1` | **Emisión de Factura** | Proceso para seleccionar una orden de compra, cambiar su estado a “facturada”, calcular el **19% de IVA** por producto y determinar el monto total a pagar. |
| **RF5** | `api-envio-v1` | **Envío de Productos** | Proceso logístico. Permite seleccionar una factura y marcar los productos como despachados, registrando un texto indicativo de seguimiento en la base de datos. |

---

## 📂 Estructura del Código (Microservicio de Pagos - `Perfumes-2.0`)

La estructura de carpetas sigue el estándar de una aplicación **Spring Boot** con separación de responsabilidades (Modelo-Vista-Controlador/Servicio):
```
perfumes-2.0/
├── Dockerfile
├── pom.xml
├── render.yaml
├── .gitignore
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── perfumes/
│   │   │               ├── PerfumeApplication.java
│   │   │               ├── config/
│   │   │               │   ├── RestTemplateConfig.java
│   │   │               │   ├── SecurityConfig.java
│   │   │               │   └── CorsConfig.java
|   |   |               |   └── ThymeLeafConfig.java
│   │   │               ├── controller/
│   │   │               │   └── Controller.java
│   │   │               ├── model/
│   │   │               │   ├── Model.java
│   │   │               │   ├── DTO.java
│   │   │               ├── repository/
│   │   │               │   └── Repository.java
│   │   │               └── service/
│   │   │                   └── Services.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       │   ├── controller/
│       │   │   └── ControllerTest.java
│       │   ├── service/
│       │   │   └── ServiceTest.java
│       │   └── ApplicationTests.java
└── README.md
```

## 🛠️ Tecnologías y Comandos de Ejecución

El proyecto base utiliza **Java 17** con **Spring Boot 3** y **Maven**.

### Prerrequisitos
* **JDK 17** o superior.
* **Apache Maven**.
* Configuración de una base de datos **PostgreSQL**.

### Comandos de Ejecución Local

Para cualquier microservicio basado en esta estructura:

1.  **Clonar y Navegar**
    ```bash
    git clone [https://github.com/DanielBeltranl/Perfumes-2.0.git](https://github.com/DanielBeltranl/Perfumes-2.0.git)
    cd Perfumes-2.0
    ```

2.  **Compilar y Ejecutar Pruebas**
    ```bash
    # Limpia el proyecto, ejecuta todas las pruebas unitarias e integrales, y empaqueta el código en un JAR ejecutable.
    ./mvnw clean package
    ```

3.  **Ejecutar la Aplicación**
    ```bash
    # Ejecuta el archivo JAR generado. Reemplazar "OrdenCompra" con el nombre del JAR correspondiente al microservicio.
    java -jar target/OrdenCompra 0.0.1-SNAPSHOT.jar
    ```

---

## 🚀 Análisis del Pipeline de Despliegue (CI/CD)

El siguiente bloque YAML define la configuración de despliegue de los microservicios:

# Datos de la app
services:
- name: nombre-microservicios
  type: web

# Conexion con el repositorio
    git:
      url: https://github.com/DanielBeltranl/Perfumes-2.0.git
      branch: Api-orden-compra


# Empaquetamiento y ejecucion de pruebas
    build:

      command: ./mvnw clean package 

# Instrucciones de que ejecutar y donde hacerlo
    run:
      command: java -jar target/OrdenCompra 0.0.1-SNAPSHOT.jar

    instance:
      type: nano

    ports:
      - 8080

# Variables de entorno
    env:
      SPRING_PROFILES_ACTIVE: staging
      SERVER_PORT: "8080"
      DATABASE_URL: jdbc:postgresql://ep-bitter-brook-ah71hxwr-pooler.c-3.us-east-1.aws.neon.tech:5432/neondb?sslmode=require&channel_binding=require


