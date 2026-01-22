# 📄 Sistema Institucional de Envío y Gestión de Informes

## 📌 Descripción general

Este proyecto consiste en el desarrollo de un **sistema institucional para el envío, recepción y gestión de informes y solicitudes**, orientado a mejorar la **comunicación interna entre las áreas administrativas y académicas** de una institución de educación superior.

El sistema busca **eliminar ineficiencias** asociadas a procesos manuales, correos dispersos o falta de trazabilidad, proporcionando un **flujo estructurado, seguro y auditable** para el intercambio de información institucional.

El backend fue desarrollado como una **API REST con Spring Boot**, complementada con **WebSocket (STOMP)** para notificaciones en tiempo real, y está diseñado para integrarse con un frontend desarrollado en **React**.

---

## 🎯 Objetivo del proyecto

- Centralizar el envío y recepción de informes institucionales.
- Permitir la comunicación formal entre áreas y usuarios.
- Proveer trazabilidad completa de los procesos (estados, responsables y tiempos).
- Notificar eventos relevantes en tiempo real.
- Garantizar seguridad y control de acceso mediante roles y autenticación JWT.

---

## 🧭 Alcance del sistema

El sistema implementa **la lógica completa de negocio** relacionada con:

- Creación, envío y gestión de informes.
- Gestión de solicitudes formales entre usuarios y áreas.
- Bandejas de entrada filtradas por estado.
- Auditoría técnica y funcional.
- Notificaciones en tiempo real.

El manejo de archivos adjuntos fue **diseñado pero no integrado** en esta versión debido a un ajuste en los tiempos de despliegue.

---

## 🧩 Módulos implementados

### 🔐 Seguridad
- Autenticación basada en **JWT**.
- Control de acceso por roles y permisos.
- Protección de endpoints REST y WebSocket.

---

### 📝 Informes
- Creación de informes en estado **BORRADOR**.
- Completar informes y cambiar su estado.
- Envío de informes a:
    - Áreas institucionales.
    - Usuarios específicos.
- Gestión de estados globales del informe.
- Gestión de estados por destino (recepción, lectura, aprobación, rechazo).

---

### 📬 Destinos de Informe
- Manejo de recepción de informes por área o usuario.
- Estados independientes por destinatario.
- Bandejas de entrada basadas en el estado del destino.
- Separación clara entre el origen del informe y sus destinatarios.

---

### 📡 Notificaciones en tiempo real (WebSocket)
- Implementación de WebSocket con STOMP.
- Notificación automática cuando:
    - Un informe es enviado.
    - Un informe es recibido por un área o usuario.
    - Una solicitud es creada o respondida.
- Canales diferenciados:
    - **Broadcast por área**.
    - **Mensajes directos por usuario**.

---

### 📨 Solicitudes
- Creación de solicitudes formales:
    - A áreas.
    - A usuarios.
- Estados del ciclo de vida de la solicitud.
- Respuesta y cambio de estado de solicitudes.
- Notificación en tiempo real al destinatario y al solicitante.

---

### 🗂 Bandejas y filtros
- Bandejas de informes recibidos:
    - Por área.
    - Por usuario.
- Filtros por estado del destino.
- Consultas optimizadas a nivel de base de datos (queries JPQL).

---

### 🕵️ Auditoría

El sistema cuenta con **dos niveles de auditoría**:

#### 1️⃣ Auditoría técnica (CRUD)
- Implementada mediante **Entity Listeners JPA**.
- Registro automático de operaciones:
    - INSERT
    - UPDATE
    - DELETE
- Aplica a entidades críticas del sistema.
- Enfocada en control técnico y cumplimiento.

#### 2️⃣ Auditoría funcional (negocio)
- Registro explícito desde los servicios de negocio.
- Historial del ciclo de vida de los informes.
- Permite conocer:
    - Qué acción se realizó.
    - Quién la realizó.
    - Cuándo ocurrió.
    - Contexto del cambio de estado.

---

## ✅ Funcionalidades disponibles

Actualmente, el sistema permite:

- Autenticarse y autorizar usuarios.
- Crear y gestionar informes.
- Enviar informes a áreas o usuarios.
- Recibir y gestionar informes desde bandejas filtradas.
- Crear y responder solicitudes institucionales.
- Recibir notificaciones en tiempo real.
- Auditar todas las acciones relevantes del sistema.

---

## ⏳ Funcionalidades pendientes

### 📎 Carga y gestión de archivos

La funcionalidad para subir y descargar archivos adjuntos **no fue integrada en esta versión**, debido a un ajuste en el cronograma de despliegue.

No obstante:

- El diseño del sistema contempla este módulo.
- La lógica de negocio ya funciona como un **servicio de mensajería de informes**.
- La integración de archivos puede realizarse como un **módulo técnico independiente**, sin afectar la arquitectura existente.

---

## 🧱 Arquitectura general

- **Backend:** Spring Boot
- **API:** REST
- **Comunicación en tiempo real:** WebSocket (STOMP)
- **Seguridad:** Spring Security + JWT
- **Persistencia:** JPA / Hibernate
- **Base de datos:** Relacional
- **Frontend:** React (desarrollado por el equipo)

---

## 📦 Requisitos del entorno

Para ejecutar el backend del sistema se requieren los siguientes componentes:

- **Java:** 21
- **Gradle:** 8.x o superior
- **Base de datos:** PostgreSQL
- **Docker:** (opcional, recomendado para despliegue)

---

## 🔐 Variables de entorno

El sistema requiere las siguientes variables de entorno para su ejecución:

- `DATASOURCE_USERNAME`
- `DATASOURCE_PASSWORD`
- `DATASOURCE_URL`
- `SPRING_PROFILES_ACTIVE=prod`
- `JWT_PRIVATE_KEY` (Generar key: https://tools.keycdn.com/sha256-online-generator)
- `JWT_USER_GENERATOR` (opcional, valor por defecto: `sicierDev`)

Estas variables deben estar definidas en el entorno de ejecución o en el contenedor Docker.


## 🏁 Estado del proyecto

El proyecto se encuentra **funcional y desplegable**, con los principales flujos institucionales implementados, probados y documentados.

Las funcionalidades pendientes no afectan la lógica central del sistema y pueden ser integradas en una fase posterior.

---

## 👤 Autor / Equipo

Proyecto desarrollado como parte de un **requerimiento institucional académico**, orientado a resolver problemáticas reales de comunicación interna en una institución de educación superior.
