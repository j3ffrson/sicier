package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.RequestStates;
import org.fesc.sicier.services.StateDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationInformRepository extends JpaRepository<DestinationInformEntity,Long> {

    @Query("""
    SELECT d
    FROM DestinationInformEntity d
    WHERE d.areaDestination.id = :areaId
      AND d.state = :estado
""")
    List<DestinationInformEntity> findByAreaAndEstado(
            @Param("areaId") Long areaId,
            @Param("estado") StateDestination estado
    );

    @Query("""
    SELECT d
    FROM DestinationInformEntity d
    WHERE d.userDestination.id = :userId
      AND d.state = :estado
""")
    List<DestinationInformEntity> findByUsuarioAndEstado(
            @Param("userId") Long userId,
            @Param("estado") StateDestination estado
    );



}
