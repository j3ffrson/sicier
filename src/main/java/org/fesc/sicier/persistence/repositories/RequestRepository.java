package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestEntity,Long> {
}
