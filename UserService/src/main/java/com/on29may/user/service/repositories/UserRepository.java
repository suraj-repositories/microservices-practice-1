package com.on29may.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.on29may.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
