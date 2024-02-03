package com.capstonelegal.credentials.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import com.capstonelegal.credentials.model.entities.Credentials;

/**
 * DTO for {@link Credentials}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CredentialsDTO implements Serializable {
    private String credentialsId;
    private String loginId;
    private String loginPassword;
    private Timestamp credentialsCreatedTimestamp;
    private Timestamp lastSuccessfulLogin;
    private Timestamp lastFailedLogin;
    private Timestamp lastUpdated;

    /**
     * Returns the credentials object in JSON format.
     *
     * @return the credentials object in JSON format
     */
    public String toJSON() {
        return new Gson().toJson(this);
    }
    /**
     * Static method to convert Credentials entity to CredentialsDTO.
     *
     * @param credentials The Credentials entity to convert.
     * @return The corresponding CredentialsDTO object.
     */
    public static CredentialsDTO fromEntity(Credentials credentials) {
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setCredentialsId(credentials.getCredentialsId());
        credentialsDTO.setLoginId(credentials.getLoginId());
        credentialsDTO.setLoginPassword(credentials.getLoginPassword());
        credentialsDTO.setCredentialsCreatedTimestamp(credentials.getCredentialsCreatedTimestamp());
        credentialsDTO.setLastSuccessfulLogin(credentials.getLastSuccessfulLogin());
        credentialsDTO.setLastFailedLogin(credentials.getLastFailedLogin());
        credentialsDTO.setLastUpdated(credentials.getLastUpdated());
        return credentialsDTO;
    }
}
