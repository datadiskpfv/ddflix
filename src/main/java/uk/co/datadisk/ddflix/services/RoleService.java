package uk.co.datadisk.ddflix.services;

import uk.co.datadisk.ddflix.entities.user.Role;

import java.util.List;

public interface RoleService {

    // CRUD methods (create, read, update and delete)
    Role createRole(Role role);

    Role findRole(Long id);
    List<Role> findAll();
    List<Role> findAllRolesByUsers_Id(Long id);
    Role findByName(String name);

    Role saveRole(Role role);

    void deleteRoleById(Long id);
}
