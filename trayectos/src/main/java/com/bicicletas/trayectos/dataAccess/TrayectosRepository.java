package com.bicicletas.trayectos.dataAccess;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bicicletas.trayectos.modelo.Trayecto;
import java.util.List;
import java.util.Date;


@Repository
public interface TrayectosRepository 
    extends JpaRepository<Trayecto, UUID>
{

    // obtiene el trayecto con "enProceso == true"")
    Trayecto findByEnProcesoTrue();

    // obtiene los trayectos entre fecha inico y fecha fin
    @Modifying
    @Query("select t from Trayecto t where t.horaFin > :horaInicio and t.horaInicio < :horaFin")
    List<Trayecto> findEnRangoFechas(Date horaInicio, Date horaFin);

}
