package com.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wj.dao.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
