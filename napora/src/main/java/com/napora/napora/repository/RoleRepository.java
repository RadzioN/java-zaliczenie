package com.napora.napora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.napora.napora.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
