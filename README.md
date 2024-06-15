# Sistema de Venta de Entradas

[![pipeline status](https://img.shields.io/gitlab/pipeline/giofrank/CURS-JUNITMOCK/master)](https://gitlab.com/giofrank/CURS-JUNITMOCK/-/commits/master)
[![coverage report](https://img.shields.io/gitlab/coverage/giofrank/CURS-JUNITMOCK/master)](https://gitlab.com/giofrank/CURS-JUNITMOCK/-/commits/master)

Este proyecto es un sistema de venta de entradas para eventos, desarrollado utilizando Spring Boot y JPA. Permite a los usuarios comprar entradas para diferentes eventos, gestionar los pagos y consultar información sobre los eventos y sus asientos disponibles.

## Características Principales

*   **Gestión de Eventos:** Creación, edición y eliminación de eventos.
*   **Venta de Entradas:** Compra de entradas para eventos específicos.
*   **Gestión de Clientes:** Registro y gestión de información de clientes.
*   **Gestión de Pagos:** Procesamiento de pagos y registro de transacciones.
*   **Selección de Asientos:** Visualización y selección de asientos disponibles (opcional).
*   **Generación de Entradas:** Creación de comprobantes de entrada con códigos QR.
*   **Envío de Confirmaciones por Correo Electrónico:** Notificación a los clientes sobre sus compras.
*   **API REST:** Interfaz para interactuar con el sistema desde otras aplicaciones.

## Tecnologías Utilizadas

*   **Backend:**
    *   Spring Boot
    *   Java Persistence API (JPA)
    *   MySQL (u otra base de datos relacional)
    *   Lombok (opcional, para simplificar el código)
*   **Frontend:**
    *   HTML, CSS, JavaScript (o un framework como React, Angular o Vue.js)

## Cómo Ejecutar el Proyecto

1.  **Clonar el Repositorio:**

    ```bash
    git clone git@github.com:giofrank/CURS-JUNITMOCK.git
    ```
2.  **Configurar la Base de Datos:**
    *   Crea una base de datos MySQL (o la que estés utilizando).
    *   Actualiza las propiedades de conexión a la base de datos en el archivo `application.properties` (o `application.yml`).

3.  **Ejecutar la Aplicación:**
    *   Desde la línea de comandos: `./mvnw spring-boot:run` (o `gradlew bootRun`).
    *   Desde tu IDE: Ejecuta la clase principal de Spring Boot.

## Estructura del Proyecto

*   **`dominio`:** Contiene las entidades del dominio (Evento, Cliente, Entrada, etc.).
*   **`infraestructura`:** Contiene los repositorios JPA, servicios y adaptadores (API REST, etc.).
*   **`aplicacion`:** Contiene la lógica de negocio y los casos de uso.
*   **`configuración`:** Contiene clases de configuración de Spring.
*   **`controlador`:** Contiene los controladores REST.
*   **`test`:** Contiene las pruebas unitarias e de integración.
