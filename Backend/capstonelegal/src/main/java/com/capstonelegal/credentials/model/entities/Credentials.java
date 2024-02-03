package com.capstonelegal.credentials.model.entities;
import com.google.gson.Gson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "CREDENTIALS")
public class Credentials {

    @Id
    @Column(name = "CREDENTIALS_ID")
    private String credentialsId;

    @Column(name = "LOGIN_ID", nullable = false)
    private String loginId;

    @Column(name = "LOGIN_PASSWORD", nullable = false)
    private String loginPassword;

    @Column(name = "CREDENTIALS_CREATED_TIMESTAMP", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp credentialsCreatedTimestamp;

    @Column(name = "LAST_SUCCESSFUL_LOGIN")
    private Timestamp lastSuccessfulLogin;

    @Column(name = "LAST_FAILED_LOGIN")
    private Timestamp lastFailedLogin;

    @Column(name = "LAST_UPDATED")
    private Timestamp lastUpdated;

    /**
     * Get the credentials ID.
     *
     * @return the credentials ID
     */
    public String getCredentialsId() {
        return credentialsId;
    }

    /**
     * Set the credentials ID.
     *
     * @param credentialsId the credentials ID
     */
    public void setCredentialsId(String credentialsId) {
        this.credentialsId = credentialsId;
    }

    /**
     * Get the login ID.
     *
     * @return the login ID
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Set the login ID.
     *
     * @param loginId the login ID
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * Get the login password.
     *
     * @return the login password
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * Set the login password.
     *
     * @param loginPassword the login password
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    /**
     * Get the credentials created timestamp.
     *
     * @return the credentials created timestamp
     */
    public Timestamp getCredentialsCreatedTimestamp() {
        return credentialsCreatedTimestamp;
    }

    /**
     * Set the credentials created timestamp.
     *
     * @param credentialsCreatedTimestamp the credentials created timestamp
     */
    public void setCredentialsCreatedTimestamp(Timestamp credentialsCreatedTimestamp) {
        this.credentialsCreatedTimestamp = credentialsCreatedTimestamp;
    }

    /**
     * Get the last successful login timestamp.
     *
     * @return the last successful login timestamp
     */
    public Timestamp getLastSuccessfulLogin() {
        return lastSuccessfulLogin;
    }

    /**
     * Set the last successful login timestamp.
     *
     * @param lastSuccessfulLogin the last successful login timestamp
     */
    public void setLastSuccessfulLogin(Timestamp lastSuccessfulLogin) {
        this.lastSuccessfulLogin = lastSuccessfulLogin;
    }

    /**
     * Get the last failed login timestamp.
     *
     * @return the last failed login timestamp
     */
    public Timestamp getLastFailedLogin() {
        return lastFailedLogin;
    }

    /**
     * Set the last failed login timestamp.
     *
     * @param lastFailedLogin the last failed login timestamp
     */
    public void setLastFailedLogin(Timestamp lastFailedLogin) {
        this.lastFailedLogin = lastFailedLogin;
    }

    /**
     * Get the last updated timestamp.
     *
     * @return the last updated timestamp
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Set the last updated timestamp.
     *
     * @param lastUpdated
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Returns the credentials object in JSON format.
     *
     * @return the credentials object in JSON format
     */
    public String toJSON() {
        return new Gson().toJson(this);

    }
}