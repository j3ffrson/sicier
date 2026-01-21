package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {

    Optional<AreaEntity> findByName(String areaName);

}
