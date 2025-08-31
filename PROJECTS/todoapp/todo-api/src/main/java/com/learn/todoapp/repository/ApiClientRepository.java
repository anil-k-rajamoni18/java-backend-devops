package com.learn.todoapp.repository;

import com.learn.todoapp.enity.ApiClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApiClientRepository extends JpaRepository<ApiClient, Long> {

    ApiClient findByUserIdAndActiveTrue(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE ApiClient a SET a.active = false WHERE a.user.id = :userId")
    void deactivateByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE ApiClient a SET a.active = false WHERE a.clientId = :clientId")
    int deactivateByClientId(String clientId);

    ApiClient findByClientId(String clientId);
}
