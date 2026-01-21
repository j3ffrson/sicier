package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditEntity,Long> {
}
