package com.microservices.repository.contracts;


import com.microservices.repository.entities.AdmconsLogEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AdmconsLogRepository extends CrudRepository<AdmconsLogEntity, String> {
    @Query("FROM AdmconsLogEntity m WHERE  m.action =:action AND  m.logId =:logId ")
    List<AdmconsLogEntity> findByIdLike(@Param("logId") String logId, @Param("action") String action);
}
