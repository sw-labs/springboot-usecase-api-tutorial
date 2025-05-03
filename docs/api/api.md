# API de Trayectos

API para que programas externos y aplicciones móviles puedan interactuar con el sistema de trayectos.

| API  | Descripción |
|------|-------------|
| `[GET]`  `/api/trayectos/`              | (obtiene todos los trayectos)    |
| `[GET]`  `/api/trayectos/{id}`          | (obtiene un trayecto por id)     |
| `[POST]` `/api/trayectos/`              | (inicia un trayecto)             |
| `[POST]` `/api/trayectos/{id}`          | (agrega ubicación a un trayecto) |
| `[POST]` `/api/trayectos/{id}/finalizar`| (finaliza un trayecto)           |

----

## Listar Trayectos

<details>
 <summary>
   <code>[GET]</code>
   <code><b>/api/trayectos</b></code>
   (obtiene todos los trayectos)
  </summary>

##### Parametros

> None

##### Respuestas

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | JSON                                                          |

##### Example cURL

> ```bash
>  http GET localhost:8080/api/trayectos
> ```

</details>

<details>
 <summary>
   <code>[GET]</code>
   <code><b>/api/trayectos/{id}</b></code>
   (obtiene un trayecto por id)
  </summary>

##### Parametros

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | `id` |  required | UUID   |  id del trayecto  |


##### Respuestas

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | JSON                                                          |
> | `400`         | `application/json`                | `{"code":"400","message":"Bad Request"}`                            |

##### Example HTTPie

> ```bash
>  http GET localhost:8080/api/trayectos/1a42bdc6-114e-471f-82a4-c62f9f5b893b
> ```

</details>


---

## Crear y gestionar trayecto


<details>
 <summary>
 <code>[POST]</code> 
 <code><b>/api/trayectos</b></code> 
 (inicia un trayecto)
 </summary>

##### Parametros

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | `ubicacion` |  required | object (JSON)   |  `{ "longitud": "4.6289767","latitud": "-74.0651983"}`  |


##### Respuestas

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `xxx-xxx-xxx-xxx` (UUID)                                |
> | `400`         | `application/json`                | `{"code":"400","message":"Bad Request"}`                            |

##### Ejemplo en HTTPie

> ```bash
>  http --json \
>     POST localhost:8080/api/trayectos \
>     longitud=4.6289767 \
>     latitud=-74.0651983
> ```

</details>

<details>
 <summary>
 <code>[POST]</code> 
 <code><b>/api/trayectos/{id}/ubicaciones</b></code> 
 (agrega una ubicación a un trayecto)
 </summary>

##### Parametros

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | `id` |  required | UUID   |  id del trayecto  |
> | `ubicacion` |  required | object (JSON)   |  `{ "longitud": "4.6289767","latitud": "-74.0651983"}`  |


##### Respuestas

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `xxx-xxx-xxx-xxx` (UUID)                                |
> | `400`         | `application/json`                | `{"code":"400","message":"Bad Request"}`                            |

##### Ejemplo en HTTPie

> ```bash
>  http --json \
>     POST localhost:8080/api/trayectos/1a42bdc6-114e-471f-82a4-c62f9f5b893b/ubicaciones/ \
>     longitud=4.6289767 \
>     latitud=-74.0651983
> ```

</details>

<details>
 <summary>
 <code>[POST]</code> 
 <code><b>/api/trayectos/{id}/finalizar</b></code> 
 (finaliza un trayecto)
 </summary>

##### Parametros

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | `id` |  required | UUID   |  id del trayecto  |
> | `ubicacion` |  required | object (JSON)   |  `{ "longitud": "4.6289767","latitud": "-74.0651983"}`  |


##### Respuestas

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/plain;charset=UTF-8`        | `xxx-xxx-xxx-xxx` (UUID)                                |
> | `400`         | `application/json`                | `{"code":"400","message":"Bad Request"}`                            |

##### Ejemplo en HTTPie

> ```bash
>  http --json \
>     POST localhost:8080/api/trayectos/1a42bdc6-114e-471f-82a4-c62f9f5b893b/ultimaUbicacion/ \
>     longitud=4.6289767 \
>     latitud=-74.0651983
> ```

</details>