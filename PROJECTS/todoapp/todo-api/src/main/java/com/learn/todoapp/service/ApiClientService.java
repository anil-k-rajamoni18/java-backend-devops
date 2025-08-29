package com.learn.todoapp.service;

import com.learn.todoapp.enity.ApiClient;
import com.learn.todoapp.repository.ApiClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiClientService {

    @Autowired
    private ApiClientRepository apiClientRepository;

    public ApiClient save(ApiClient apiClient) {
        return apiClientRepository.save(apiClient);
    }

    public ApiClient findActiveByUserId(Long userId) {
        return apiClientRepository.findByUserIdAndActiveTrue(userId);
    }

    public void deactivateClientsByUserId(Long userId) {
        apiClientRepository.deactivateByUserId(userId);
    }

    public boolean deactivateClient(String clientId) {
        return apiClientRepository.deactivateByClientId(clientId) > 0;
    }

    public ApiClient findByClientId(String clientId) {
        return apiClientRepository.findByClientId(clientId);
    }
}
