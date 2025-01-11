# 🐾 Sistema de Adopción de Mascotas

## 📋 Descripción
Sistema de gestión para veterinarias enfocado en el proceso de adopción de mascotas. Desarrollado con Spring Boot, esta API REST permite administrar mascotas, usuarios y procesos de adopción de manera segura y eficiente.

## ✨ Características Principales
- 🔐 Sistema de autenticación y autorización con roles (ADMIN, DOCTOR, CLIENTE)
- 🐕 Gestión completa de mascotas (CRUD)
- 👥 Administración de usuarios con diferentes niveles de acceso
- 📝 Registro y seguimiento de adopciones
- 🏷️ Categorización de mascotas por tipo
- 📊 Validaciones de negocio para el proceso de adopción
- 🧾 Logs de ejecucion detallados

## 🛠️ Tecnologías Utilizadas
- Java 21
- Spring Boot 3.3.6
- Spring Security
- PostgreSQL
- JPA/Hibernate
- Maven
- Swagger/OpenAPI
- JUnit 5
- Spring AOP

## 🚀 Instalación y Configuración

### Prerrequisitos
- Java JDK 21
- Maven
- PostgreSQL

## Configuración de Base de Datos

### Entorno de Desarrollo
El proyecto está configurado para funcionar con cualquier base de datos relacional compatible con JPA. Al ejecutar la aplicación por primera vez, Hibernate creará automáticamente las tablas y relaciones necesarias basándose en los modelos definidos.

### Variables de Entorno
Las credenciales de conexión deben configurarse mediante variables de entorno:

- SUPABASE_HOST=tu_host_de_base_de_datos
- SUPABASE_PORT=tu_puerto_de_base_de_datos
- SUPABASE_DB=tu_nombre_de_base_de_datos
- SUPABASE_USERNAME=tu_usuario
- SUPABASE_PASSWORD=tu_contraseña

Estas podran configurarse en las variables de entorno del sistema, o en un archivo .env alojado en la carpeta raiz del proyecto (donde se encuentra pom.xml)

### Pasos de Instalación
1. Clonar el repositorio
```
git clone https://github.com/Kdna-dev/sistema-de-adopcion.git
```

2. Navegar al directorio del proyecto
```
cd sistema-de-adopcion
```
#### Si tienes instalado maven en el sistema, utiliza el siguiente comando para compilar y ejecutar el proyecto

3. Ejecutar la aplicación
```
mvn spring-boot:run
```

#### Si NO tienes instalado maven en el sistema, puedes utilizar el maven wrapper que provee spring boot

3. Ejecutar la aplicación
```
./mvnw spring-boot:run
```

## 📚 Documentación API
La documentación de la API está disponible en tiempo de ejecución a través de Swagger UI:
[Swagger UI](http://localhost:8080/swagger-ui.html)


## 🔑 Roles y Permisos

### 👨‍💼 ADMIN
- Gestión completa de usuarios
- Gestión completa de mascotas
- Gestión de adopciones

### 👨‍⚕️ DOCTOR
- Registro de clientes
- Gestión de mascotas
- Gestión de adopciones

### 👤 CLIENTE
- Visualización de mascotas disponibles
- Solicitud de adopciones


## 🧪 Testing
El proyecto incluye tests unitarios y de integración.

### Ejecutar Tests
Para ejecutar todos los tests:
```
mvn test
```

Para ejecutar una clase específica de test:
```
mvn test -Dtest=UsuarioControllerTests
```

Para ejecutar un método específico:
```
mvn test -Dtest=UsuarioControllerTests#debeCrearUsuarioAdmin
```

### Tipos de Tests

#### Tests Unitarios
Ubicados en `src/test/java/.../unit/`:
- Controllers: Prueban endpoints y respuestas HTTP
- Services: Prueban lógica de negocio
- Repositories: Prueban operaciones de base de datos

#### Tests de Integración
Ubicados en `src/test/java/.../integration/`:
- Prueban el flujo completo de la aplicación
- Utilizan H2 como base de datos en memoria


### Configuración de Test
Los tests utilizan un perfil específico con:
- Base de datos H2 en memoria
- Configuración específica para testing
- Datos de prueba precargados

Ver configuración en:
`src/test/resources/application-test.properties`


## 📝 Sistema de Logs

El proyecto implementa un sistema de logs detallado para facilitar el debugging y monitoreo:

### 📂 Estructura de Logs
- `logs/adopcion.log`: Registro de eventos actuales
- `logs/archived/`: Histórico de logs comprimidos por fecha

### 🔍 Niveles de Log
- **DEBUG**: Información detallada para desarrollo
- **INFO**: Eventos normales del sistema
- **WARN**: Advertencias y situaciones inesperadas
- **ERROR**: Errores que requieren atención

### ⚙️ Configuración
Los logs están configurados en `logback-spring.xml` con las siguientes características:
- Rotación diaria de archivos
- Compresión automática de logs antiguos
- Retención de 30 días de histórico
- Límite de 1GB de almacenamiento total

### 📊 Ejemplo de Formato
```
2024-03-14 10:30:45.123 INFO  com.kdnadev.adopcion.Controller - Nueva adopción registrada: ID=123
```


## 🤝 Contribuir
1. Fork el proyecto
2. Crea tu rama de características (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add: alguna característica asombrosa'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request


## ✍️ Autor
Santiago Cabrera - [@Kdna-dev](https://github.com/Kdna-dev)


## 🙏 Agradecimientos
- A Codigo Facilito por brindarme la oportunidad de desarrollar este desafiante proyecto
- A la comunidad de Spring Boot por su excelente documentación