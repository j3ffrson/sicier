package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.ArchiveAdjuntEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveAdjuntRepository extends JpaRepository<ArchiveAdjuntEntity,Long> {
}
