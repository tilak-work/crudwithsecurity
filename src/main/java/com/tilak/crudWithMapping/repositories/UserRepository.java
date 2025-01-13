package com.tilak.crudWithMapping.repositories;

import com.tilak.crudWithMapping.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.username = :identifier OR u.email = :identifier OR u.phoneNumber = :identifier")
    User findByUsernameOrEmailOrPhoneNumber(String identifier);
}
