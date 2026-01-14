package org.fesc.sicier.persistence.repositories;

import org.fesc.sicier.persistence.entities.security.ERoles;
import org.fesc.sicier.persistence.entities.security.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntitiesByRolesNameIn(List<ERoles> roleList);

}
