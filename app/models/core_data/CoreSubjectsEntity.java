package models.core_data;

import models.news_package.NewsCommentsEntity;
import models.security.SecurityUsersEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "core_subjects", schema = "assigment_forum", catalog = "")
public class CoreSubjectsEntity {
    private long id;
    private Long parentId;
    private String title;
    private Date creationDate;
    private String status;
    private Date updateDate;
    private Long createdBy;
    private Long approvedBy;
    private Date approvedDate;

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
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "created_by")
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreSubjectsEntity that = (CoreSubjectsEntity) o;
        return id == that.id &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(createdBy, that.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, title, creationDate, status, updateDate, createdBy);
    }

    @Basic
    @Column(name = "approved_by")
    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Basic
    @Column(name = "approved_date")
    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }








    public HashMap<String, Object> getTopicObject(CoreSubjectsEntity subjectsEntity , EntityManager entityManager){
        HashMap<String, Object> sHmpam = new HashMap<String, Object>();

        sHmpam.put("id", subjectsEntity.getId());
        sHmpam.put("title", subjectsEntity.getTitle());
        sHmpam.put("status", subjectsEntity.getStatus());
        sHmpam.put("parentId", subjectsEntity.getParentId());
        sHmpam.put("createdBy", subjectsEntity.getCreatedBy());
        sHmpam.put("approvedBy", subjectsEntity.getApprovedBy());
        sHmpam.put("creationDate", subjectsEntity.getCreationDate());
        sHmpam.put("approvedDate", subjectsEntity.getApprovedDate());

        return sHmpam;
    }













}
