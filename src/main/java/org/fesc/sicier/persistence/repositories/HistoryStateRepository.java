package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.HistoryStateEntity;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.RequestStates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryStateRepository extends JpaRepository<HistoryStateEntity, Long> {
    List<HistoryStateEntity> findByInformId(Long informId);
    List<HistoryStateEntity> findByRequestId(Long requestId);
    List<HistoryStateEntity> findByState(InformStates state);
    List<HistoryStateEntity> findByRequestState(RequestStates requestState);
}
