package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.InformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformRepository extends JpaRepository<InformEntity, Long> {
}
