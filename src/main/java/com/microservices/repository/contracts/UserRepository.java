package com.microservices.repository.contracts;


import com.microservices.repository.entities.PhonesEntity;
import com.microservices.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, PhonesEntity> {
    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT u,p  FROM UserEntity u LEFT JOIN PhonesEntity p ON p.idUser = u.id WHERE u.id = :id")
    List<Object[]> findUser(String id);

}

