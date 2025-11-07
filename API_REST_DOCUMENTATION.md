# API REST - Sistema de Gestión Clínica

Este documento describe la arquitectura y endpoints de la API REST del sistema de gestión clínica.

## 📁 Estructura del Proyecto

```
infrastructure/
└── adapter/
    └── api/
        └── rest/
            ├── controller/              # Controladores REST separados por rol
            │   ├── HumanResourcesController.java
            │   ├── AdministrativeController.java
            │   ├── InventoryMedicineController.java
            │   ├── InventoryProcedureController.java
            │   ├── InventoryDiagnosticAidController.java
            │   ├── NursingController.java
            │   └── MedicalAttentionController.java
            ├── dto/                     # DTOs de Request/Response
            │   ├── user/
            │   ├── patient/
            │   ├── inventory/
            │   ├── nursing/
            │   └── medical/
            └── exception/               # Manejo global de excepciones
                ├── GlobalExceptionHandler.java
                └── ErrorResponse.java
```

## 🎯 Controladores por Rol

### 1. **HumanResourcesController** - Gestión de Usuarios (Recursos Humanos)
**Base URL:** `/api/users`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/users` | Crear usuario |
| GET | `/api/users` | Listar todos los usuarios |
| GET | `/api/users/{id}` | Obtener usuario por cédula |
| GET | `/api/users/role/{role}` | Obtener usuarios por rol |
| DELETE | `/api/users/{id}` | Eliminar usuario |

**Request DTO:** `CreateUserRequest`
**Response DTO:** `UserResponse`

---

### 2. **AdministrativeController** - Gestión de Pacientes (Personal Administrativo)
**Base URL:** `/api/patients`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/patients` | Registrar paciente |
| GET | `/api/patients` | Listar todos los pacientes |
| GET | `/api/patients/{id}` | Obtener paciente por cédula |
| GET | `/api/patients/username/{username}` | Obtener paciente por username |
| PUT | `/api/patients/{id}` | Actualizar paciente |
| GET | `/api/patients/{id}/bill` | Generar factura del paciente |

**Request DTO:** `CreatePatientRequest`
**Response DTO:** `PatientResponse`, `BillResponse`

---

### 3. **Inventory Controllers** - Gestión de Inventarios (Soporte de Información)

#### 3.1 **InventoryMedicineController**
**Base URL:** `/api/inventory/medicines`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/inventory/medicines` | Crear medicamento |
| GET | `/api/inventory/medicines` | Listar todos los medicamentos |
| GET | `/api/inventory/medicines/{id}` | Obtener medicamento por ID |
| PUT | `/api/inventory/medicines/{id}` | Actualizar medicamento |
| DELETE | `/api/inventory/medicines/{id}` | Eliminar medicamento |

**Request DTO:** `CreateMedicineRequest`
**Response DTO:** `MedicineResponse`

#### 3.2 **InventoryProcedureController**
**Base URL:** `/api/inventory/procedures`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/inventory/procedures` | Crear procedimiento |
| GET | `/api/inventory/procedures` | Listar todos los procedimientos |
| GET | `/api/inventory/procedures/{id}` | Obtener procedimiento por ID |
| PUT | `/api/inventory/procedures/{id}` | Actualizar procedimiento |
| DELETE | `/api/inventory/procedures/{id}` | Eliminar procedimiento |

**Request DTO:** `CreateProcedureRequest`
**Response DTO:** `ProcedureResponse`

#### 3.3 **InventoryDiagnosticAidController**
**Base URL:** `/api/inventory/diagnostic-aids`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/inventory/diagnostic-aids` | Crear ayuda diagnóstica |
| GET | `/api/inventory/diagnostic-aids` | Listar todas las ayudas diagnósticas |
| GET | `/api/inventory/diagnostic-aids/{id}` | Obtener ayuda diagnóstica por ID |
| PUT | `/api/inventory/diagnostic-aids/{id}` | Actualizar ayuda diagnóstica |
| DELETE | `/api/inventory/diagnostic-aids/{id}` | Eliminar ayuda diagnóstica |

**Request DTO:** `CreateDiagnosticAidRequest`
**Response DTO:** `DiagnosticAidResponse`

---

### 4. **NursingController** - Enfermería (Signos Vitales)
**Base URL:** `/api/nursing/vital-signs`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/nursing/vital-signs` | Registrar signos vitales |
| GET | `/api/nursing/vital-signs/patient/{patientId}` | Obtener signos vitales por paciente |
| GET | `/api/nursing/vital-signs/nurse/{nurseId}` | Obtener signos vitales por enfermera |
| GET | `/api/nursing/vital-signs/{id}` | Obtener signos vitales por ID |

**Request DTO:** `CreateVitalSignsRequest`
**Response DTO:** `VitalSignsResponse`

---

### 5. **MedicalAttentionController** - Atención Médica (Médicos)
**Base URL:** `/api/medical`

#### 5.1 Historias Clínicas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/medical/medical-records` | Crear historia clínica |
| GET | `/api/medical/medical-records/patient/{patientId}` | Obtener historias clínicas de un paciente |

**Request DTO:** `CreateMedicalRecordRequest`
**Response DTO:** `MedicalRecordResponse`

#### 5.2 Órdenes Médicas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/medical/orders` | Crear orden médica |
| GET | `/api/medical/orders/patient/{patientId}` | Obtener órdenes de un paciente |

**Request DTO:** `CreateOrderRequest`
**Response DTO:** `OrderResponse`

---

## ⚠️ Manejo de Errores

El sistema implementa un manejador global de excepciones (`GlobalExceptionHandler`) que retorna respuestas HTTP apropiadas:

### Códigos de Estado HTTP

| Código | Excepción | Descripción |
|--------|-----------|-------------|
| **400 BAD REQUEST** | `IllegalStateException` | Errores de validación en builders/validators |
| **409 CONFLICT** | `IllegalArgumentException` | Conflictos de negocio (recursos duplicados, etc.) |
| **500 INTERNAL SERVER ERROR** | `Exception` | Cualquier excepción no esperada |

### Formato de Respuesta de Error

```json
{
  "status": 400,
  "message": "Descripción del error",
  "timestamp": "2025-11-05T10:30:00"
}
```

## 📝 Ejemplos de Uso

### Crear Usuario (Recursos Humanos)

**Request:**
```http
POST /api/users
Content-Type: application/json

{
  "id": "1234567890",
  "fullName": "Juan Pérez",
  "email": "juan.perez@clinic.com",
  "phoneNumber": "3001234567",
  "birthDate": "1990-05-15",
  "address": "Calle 123 #45-67",
  "role": "DOCTOR"
}
```

**Response (201 CREATED):**
```json
{
  "id": "1234567890",
  "fullName": "Juan Pérez",
  "email": "juan.perez@clinic.com",
  "phoneNumber": "3001234567",
  "birthDate": "1990-05-15",
  "address": "Calle 123 #45-67",
  "role": "DOCTOR"
}
```

### Registrar Paciente (Personal Administrativo)

**Request:**
```http
POST /api/patients
Content-Type: application/json

{
  "id": "9876543210",
  "username": "maria_g",
  "password": "Secure@123",
  "fullName": "María González",
  "birthDate": "1985-08-20",
  "gender": "FEMALE",
  "address": "Carrera 45 #12-34",
  "phoneNumber": "3009876543",
  "email": "maria.g@email.com",
  "emergencyContact": {
    "name": "Pedro González",
    "relationship": "Hermano",
    "phoneNumber": "3001111111"
  },
  "insurance": {
    "companyName": "Seguros ABC",
    "policyNumber": "POL123456",
    "active": true,
    "expiryDate": "2026-12-31"
  }
}
```

### Registrar Signos Vitales (Enfermería)

**Request:**
```http
POST /api/nursing/vital-signs
Content-Type: application/json

{
  "patientId": "9876543210",
  "nurseId": "1111111111",
  "bloodPressure": "120/80",
  "temperature": 36.5,
  "pulse": 72,
  "oxygenLevel": 98
}
```

### Crear Historia Clínica (Médicos)

**Request:**
```http
POST /api/medical/medical-records
Content-Type: application/json

{
  "patientId": "9876543210",
  "doctorId": "1234567890",
  "reason": "Control de rutina",
  "symptoms": "Ninguno reportado",
  "diagnosis": "Paciente en buen estado de salud"
}
```

## 🔧 Tecnologías Utilizadas

- **Spring Boot** - Framework principal
- **Spring Web** - Para construir la API REST
- **Spring Data JPA** - Persistencia de datos
- **Arquitectura Hexagonal** - Separación de capas (Domain, Application, Infrastructure)

## 📌 Notas Importantes

1. **Separación por Roles**: Cada controlador está diseñado específicamente para un rol del sistema clínico
2. **DTOs**: Se utilizan DTOs separados para Request y Response, evitando exponer el modelo de dominio
3. **Validaciones**: Las validaciones de negocio se manejan en la capa de dominio/aplicación
4. **Manejo de Errores**: Sistema unificado de manejo de excepciones con códigos HTTP apropiados
