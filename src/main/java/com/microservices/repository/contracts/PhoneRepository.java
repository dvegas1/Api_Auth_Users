package com.microservices.repository.contracts;


import com.microservices.repository.entities.PhonesEntity;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@DynamicInsert
public interface PhoneRepository extends JpaRepository<PhonesEntity, Long> {
}
