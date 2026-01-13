package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.security.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntitiesByRolesIn(List<String> roleList);

}
