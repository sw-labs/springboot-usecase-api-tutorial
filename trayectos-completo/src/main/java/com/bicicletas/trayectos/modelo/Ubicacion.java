package com.bicicletas.trayectos.modelo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime fechaHora;

    Double longitud;

    Double latitud;

    @JsonBackReference
    @ManyToOne
    Trayecto trayecto;

}
