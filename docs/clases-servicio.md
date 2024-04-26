# Diagrama de clases

![](https://www.plantuml.com/plantuml/png/PP51I_H038Rl_HMXfs-NRmjUUrWM3yA21q7z0TbEh85f8fcfeE9_DzLEjwlNPzvadfSSKaRBun2hgekO4joJlNB7-c3uHbtM11yL08ipj0B_RQ25esJgoVpLgEUK3Utnn1twi2n13almFupj4jYivvzOCF8xsdfiVbu5FujZYVseXGkNPpKyJhRQGkQ1lD_mClCR5eV4Gu71jL6ycQ-uLUadDDgtu9cGt9bsbDJHjMea55SfBakZMUQtowL5z9MSHqy57K-H9eHvJM3QlHIihshOxi9k3_MsFlzLLN_Fr3JMD7iem0KF9C4l-mK0)

Clases:
* Trayecto: Cada uno de los taryectos de entrenamiento del usuario.
* Ubicacion: Secuencia de ubicaciones (con longitud y latitud) que se obtienen durante un trayecto específico.


---

```uml
@startuml

class ServicioTrayectos {
  iniciarTrayecto() : Integer
  registrarUbicacionTrayecto(id: Integer, u:Ubicacion): Integer
  finalizarTrayecto(id:Integer): Integer
}

class Trayecto {
  id : Integer
  horaInicio: timestamp
  horaFin: teimstamp
  distancia : Long
  duracion : Long
  enProceso : Boolean
}


class Ubicacion {
  id: Integer
  hora: timestamp
  longitud: double
  latitud: double 
}

Trayecto "1" *-d-> "*" Ubicacion

ServicioTrayectos ..r..> Trayecto

@enduml
```