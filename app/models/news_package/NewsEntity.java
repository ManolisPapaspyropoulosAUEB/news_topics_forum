package models.news_package;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "news", schema = "assigment_forum", catalog = "")
public class NewsEntity {
    private long id;
    private String title;
    private String content;
    private String status;
    private Date submitionDate;
    private Date approvalDate;
    private Date publishDate;
    private String rejectionReason;
    private Date creationDate;
    private Date updateDate;
    private Long createdBy;
    private Long rejectedBy;
    private Long approvalBy;
    private Long submittedBy;
    private Date rejectionDate;
    private Long publishBy;

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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "submition_date")
    public Date getSubmitionDate() {
        return submitionDate;
    }

    public void setSubmitionDate(Date submitionDate) {
        this.submitionDate = submitionDate;
    }

    @Basic
    @Column(name = "approval_date")
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Basic
    @Column(name = "publish_date")
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "rejection_reason")
    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
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

    @Basic
    @Column(name = "rejected_by")
    public Long getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(Long rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    @Basic
    @Column(name = "approval_by")
    public Long getApprovalBy() {
        return approvalBy;
    }

    public void setApprovalBy(Long approvalBy) {
        this.approvalBy = approvalBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(status, that.status) &&
                Objects.equals(submitionDate, that.submitionDate) &&
                Objects.equals(approvalDate, that.approvalDate) &&
                Objects.equals(publishDate, that.publishDate) &&
                Objects.equals(rejectionReason, that.rejectionReason) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(rejectedBy, that.rejectedBy) &&
                Objects.equals(approvalBy, that.approvalBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status, submitionDate, approvalDate, publishDate, rejectionReason, creationDate, updateDate, createdBy, rejectedBy, approvalBy);
    }

    @Basic
    @Column(name = "submitted_by")
    public Long getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Long submittedBy) {
        this.submittedBy = submittedBy;
    }

    @Basic
    @Column(name = "rejection_date")
    public Date getRejectionDate() {
        return rejectionDate;
    }

    public void setRejectionDate(Date rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    @Basic
    @Column(name = "publish_by")
    public Long getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(Long publishBy) {
        this.publishBy = publishBy;
    }
}
