# 🚀 Agencia Espacial — Práctica Programación Tema 9

Aplicación de consola en **Java** que gestiona una base de datos de una agencia espacial ficticia, desarrollada como práctica del Tema 9 (JPA / Persistencia).

## 📋 Descripción

El sistema permite gestionar todas las entidades de una agencia espacial: misiones, astronautas, vehículos de lanzamiento, satélites, estaciones de seguimiento y telemetría. La persistencia se realiza mediante **JPA (Jakarta Persistence API)** sobre una base de datos **ObjectDB**.

## 🛠️ Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 17 |
| Jakarta Persistence API | 3.1.0 |
| ObjectDB (con soporte Jakarta) | 2.9.5 |
| Maven | 3.x |

## 📁 Estructura del proyecto

```
Agencia----Espacial/
├── src/main/java/
│   ├── Entidades/          # Clases JPA mapeadas a la BD
│   ├── Repositorio/        # Acceso a datos (CRUD)
│   ├── Servicios/          # Lógica de negocio
│   └── Main/
│       └── main.java       # Punto de entrada y menú interactivo
├── src/main/resources/
│   └── META-INF/
│       └── persistence.xml # Configuración de la unidad de persistencia
├── agencia_espacial.odb    # Archivo de base de datos ObjectDB
└── pom.xml
```

## 🗃️ Entidades

| Entidad | Descripción |
|---|---|
| `Astronauta` | Datos personales y especialidad del astronauta |
| `Mision` | Misiones espaciales (tripuladas o no), estado y fechas |
| `VehiculoLanzamiento` | Vehículos usados en misiones |
| `ModeloVehiculo` | Modelos/tipos de vehículo de lanzamiento |
| `Satelite` | Satélites artificiales gestionados por la agencia |
| `EstacionSeguimiento` | Estaciones terrestres de control y rastreo |
| `Telemetria` | Datos de telemetría recibidos de satélites |
| `Ubicacion` | Coordenadas geográficas embebidas |
| `MisionAstronauta` | Relación N:M entre Misión y Astronauta (tabla intermedia) |
| `SateliteEstacion` | Relación N:M entre Satélite y EstacionSeguimiento |

## ▶️ Cómo ejecutar

### Requisitos previos

- JDK 17 o superior
- Maven 3.x

### Compilar y ejecutar

```bash
# Clonar o descomprimir el proyecto
cd Agencia----Espacial

# Compilar
mvn clean package

# Ejecutar
java -jar target/agencia-espacial-app-1.0-SNAPSHOT.jar
```

> La base de datos `agencia_espacial.odb` se crea automáticamente en el directorio de ejecución si no existe.

## 🖥️ Menú principal

Al ejecutar la aplicación se muestra un menú de consola con las siguientes opciones:

```
=== AGENCIA ESPACIAL ===
1. Misiones
2. Modelos de vehículo
3. Vehículos de lanzamiento
4. Astronautas
5. Satélites
6. Telemetría
7. Estaciones de seguimiento
0. Salir
```

Cada submenú permite realizar operaciones **CRUD** completas sobre la entidad correspondiente.

## ⚙️ Configuración de persistencia

La unidad de persistencia `AgenciaEspacialPU` está definida en `META-INF/persistence.xml` y utiliza ObjectDB como proveedor JPA:

```xml
<property name="jakarta.persistence.jdbc.url" value="objectdb:agencia_espacial.odb"/>
<property name="jakarta.persistence.jdbc.user" value="admin"/>
<property name="jakarta.persistence.jdbc.password" value="admin"/>
```

## 👥 Autores

- **Manuel Jesús Jiménez**
- **Carlos Martín**
- **Iker Iglesias**
- **Sergio Pérez**
- **Daniel Lagares**
