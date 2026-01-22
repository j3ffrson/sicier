package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditRepository extends JpaRepository<AuditEntity,Long> {
    List<AuditEntity> findByOperation(String operation);
    List<AuditEntity> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
