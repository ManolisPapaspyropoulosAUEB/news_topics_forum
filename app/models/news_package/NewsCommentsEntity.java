package models.news_package;

import models.security.SecurityUsersEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Entity
@Table(name = "news_comments", schema = "assigment_forum", catalog = "")
public class NewsCommentsEntity {
    private long id;
    private String message;
    private String status;
    private Long createdBy;
    private Date creationDate;
    private Long newsId;
    private Date updateDate;
    private Long approvalBy;
    private Date approvalDate;

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
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "created_by")
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsCommentsEntity that = (NewsCommentsEntity) o;
        return id == that.id &&
                Objects.equals(message, that.message) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, status, createdBy, creationDate);
    }

    @Basic
    @Column(name = "news_id")
    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Basic
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "approval_by")
    public Long getApprovalBy() {
        return approvalBy;
    }

    public void setApprovalBy(Long approvalBy) {
        this.approvalBy = approvalBy;
    }

    @Basic
    @Column(name = "approval_date")
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }


    public HashMap<String, Object> gatCommentObject(NewsCommentsEntity comment , EntityManager entityManager){
        HashMap<String, Object> sHmpam = new HashMap<String, Object>();

        sHmpam.put("id", comment.getId());
        sHmpam.put("message", comment.getMessage());
        sHmpam.put("status", comment.getStatus());
        sHmpam.put("getupdateDate", comment.getUpdateDate());

        sHmpam.put("createdBy", comment.getCreatedBy());
        sHmpam.put("creationDate", comment.getCreationDate());
        SecurityUsersEntity createdBy = entityManager.find(SecurityUsersEntity.class,comment.getCreatedBy());
        sHmpam.put("createdByName", createdBy.getFirstname()+" "+createdBy.getLastname());

        sHmpam.put("approvalBy", comment.getApprovalBy());
        sHmpam.put("approvalDate", comment.getApprovalDate());
        SecurityUsersEntity approvalBy = entityManager.find(SecurityUsersEntity.class,comment.getCreatedBy());
        sHmpam.put("approvalByName", approvalBy.getFirstname()+" "+approvalBy.getLastname());
        return sHmpam;
    }


}
