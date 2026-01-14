package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.InformEntity;
import org.fesc.sicier.persistence.entities.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformRepository extends JpaRepository<InformEntity, Long> {

    Optional<InformEntity> findByTitle(String title);
    List<InformEntity> findInformEntitiesByUserEmisor_FirstName(String firstName);
    List<InformEntity> findInformEntitiesByAreasEmisor_Name(String name);

}
