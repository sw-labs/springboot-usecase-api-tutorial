# Diagrama de clases

![](https://www.plantuml.com/plantuml/png/NO-_IWH13CRxF4MOyk0Asolai10MB2pq0R8pOGtC939_2X7VtIoxkdhTp8zyNtwvjawrZv8XPceDNofzSEo6du0e2IyuQUU5gt_VhDAa4iKksAMmHylxpXz57RAK7vZ4Nng5lEB9T5dHgEHXFGZhSxN8pHmzc6KcXI-0tULrbhW5DfbRb_yMsIkb3vzADkRCAwB-b-3Q_NjWk0juEf_lCPp2iGe0hgnfbFmD)

Clases:
* Trayecto: Cada uno de los taryectos de entrenamiento del usuario.
* Ubicacion: Secuencia de ubicaciones (con longitud y latitud) que se obtienen durante un trayecto específico.


---

```uml
@startuml

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

Trayecto "1" *--> "*" Ubicacion

@enduml
```