package com.capstonelegal.credentials.service;
import com.capstonelegal.credentials.model.entities.Credentials;
import com.capstonelegal.credentials.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CredentialsService {
    @Autowired
    private CredentialsRepository credentialsRepository;
    public List<Credentials> getAllCredentialss() {
        return credentialsRepository.findAll();
    }

    public Credentials getCredentialsById(String id) {
        return credentialsRepository.findById(id).orElse(null);
    }

    public Credentials createOrUpdateCredentials(Credentials credentials) {
        return credentialsRepository.save(credentials);
    }

    public void deleteCredentials(String id) {
        credentialsRepository.deleteById(id);
    }
}