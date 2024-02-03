package com.capstonelegal.judge.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "audit_log", schema = "capstonelegalschema")
public class AuditLog {
    @Id
    @Column(name = "audit_log_id", nullable = false, length = 50)
    private String auditLogId;

    @Column(name = "audit_log_timestamp")
    private Instant auditLogTimestamp;

    @Column(name = "audit_log_entry", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String auditLogEntry;

}