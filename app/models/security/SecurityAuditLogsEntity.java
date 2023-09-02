package models.security;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "security_audit_logs", schema = "framework_db", catalog = "")
public class SecurityAuditLogsEntity {
    private long id;
    private String systemName;
    private Long userId;
    private Long objectId;
    private String message;
    private String method;
    private Date creationDate;
    private String failUsername;
    private String failPassword;
    private String failIp;
    private String remoteAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "system_name")
    public String getSystem() {
        return systemName;
    }

    public void setSystem(String systemName) {
        this.systemName = systemName;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "object_id")
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "fail_username")
    public String getFailUsername() {
        return failUsername;
    }

    public void setFailUsername(String failUsername) {
        this.failUsername = failUsername;
    }

    @Basic
    @Column(name = "fail_password")
    public String getFailPassword() {
        return failPassword;
    }

    public void setFailPassword(String failPassword) {
        this.failPassword = failPassword;
    }

    @Basic
    @Column(name = "fail_ip")
    public String getFailIp() {
        return failIp;
    }

    public void setFailIp(String failIp) {
        this.failIp = failIp;
    }

    @Basic
    @Column(name = "remote_address")
    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityAuditLogsEntity that = (SecurityAuditLogsEntity) o;
        return id == that.id &&
                Objects.equals(systemName, that.systemName) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(objectId, that.objectId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(method, that.method) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(failUsername, that.failUsername) &&
                Objects.equals(failPassword, that.failPassword) &&
                Objects.equals(failIp, that.failIp) &&
                Objects.equals(remoteAddress, that.remoteAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, systemName, userId, objectId, message, method, creationDate, failUsername, failPassword, failIp, remoteAddress);
    }
}
