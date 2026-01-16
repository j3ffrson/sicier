package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.InformEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InformRepository extends JpaRepository<InformEntity, Long> {

    Optional<InformEntity> findByTitle(String title);
    Page<InformEntity> findInformEntitiesByUserEmisor_Id(Long identifier,Pageable pageable);

}
