package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.HistoryStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryStateRepository  extends JpaRepository<HistoryStateEntity,Long> {
}
