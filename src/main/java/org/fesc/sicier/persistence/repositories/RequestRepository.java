package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.RequestEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestEntity,Long> {

    List<RequestEntity> findRequestEntitiesByRequester(UserEntity user);
    List<RequestEntity> findRequestEntitiesByUserDestination(UserEntity user);

}
