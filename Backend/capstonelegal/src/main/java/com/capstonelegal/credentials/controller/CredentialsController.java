package com.capstonelegal.credentials.controller;

import com.capstonelegal.credentials.model.entities.Credentials;
import com.capstonelegal.credentials.service.CredentialsService;
import com.capstonelegal.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/credentials")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    /**
     * Returns all credentials.
     *
     * @return list of all credentials
     */
    @GetMapping
    public ResponseEntity<List<Credentials>> getAllCredentials() {
        List<Credentials> credentials = credentialsService.getAllCredentialss();
        return new ResponseEntity<>(credentials, HttpStatus.OK);
    }

    /**
     * Returns the credentials with the specified id.
     *
     * @param id id of the credentials to return
     * @return credentials with the specified id or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Credentials> getCredentialsById(@PathVariable String id) {
        Credentials credentials = credentialsService.getCredentialsById(id);
        if (credentials == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(credentials, HttpStatus.OK);
    }

    /**
     * Creates or updates the specified credentials.
     *
     * @param credentials credentials to create or update
     * @return created or updated credentials
     */
    @PostMapping
    public ResponseEntity<Credentials> createCredentials(@RequestBody Credentials credentials) {
        credentials.setCredentialsId(UUIDUtil.generateUUID());
        Credentials createdOrUpdatedCredentials = credentialsService.createOrUpdateCredentials(credentials);
        return new ResponseEntity<>(createdOrUpdatedCredentials, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credentials> updateCredentials(@PathVariable String id,@RequestBody Credentials credentials) {
        Credentials existingCredentials = credentialsService.getCredentialsById(id);
        if(existingCredentials == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Credentials createdOrUpdatedCredentials = credentialsService.createOrUpdateCredentials(credentials);
        return new ResponseEntity<>(createdOrUpdatedCredentials, HttpStatus.CREATED);
    }

    /**
     * Deletes the credentials with the specified id.
     *
     * @param id id of the credentials to delete
     * @return 204 No Content on success, 404 Not Found if credentials not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredentials(@PathVariable String id) {
        Credentials credentials = credentialsService.getCredentialsById(id);
        if (credentials == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        credentialsService.deleteCredentials(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
