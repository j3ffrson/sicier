package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.DestinationInformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationInformRepository extends JpaRepository<DestinationInformEntity,Long> {
}
