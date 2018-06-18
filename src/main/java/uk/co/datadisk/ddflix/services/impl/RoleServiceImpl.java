package uk.co.datadisk.ddflix.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.entities.user.Role;
import uk.co.datadisk.ddflix.exceptions.NotFoundException;
import uk.co.datadisk.ddflix.repositories.user.RoleRepository;
import uk.co.datadisk.ddflix.services.RoleService;


import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRole(Long id) {
        return roleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Role Not Found. For ID value: " + id.toString()));

    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAllRolesByUsers_Id(Long id) {
      return roleRepository.findAllRolesByUsers_Id(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
       roleRepository.deleteById(id);
    }
}