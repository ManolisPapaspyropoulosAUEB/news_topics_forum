package models.news_package;

import models.core_data.CoreSubjectsEntity;
import models.security.SecurityUsersEntity;

import javax.persistence.*;
import java.util.*;

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




    @SuppressWarnings({"Duplicates", "unchecked"})
    public HashMap<String, Object> getNewsHashmap(NewsEntity j , EntityManager entityManager ,String roleId, String userId){
        HashMap<String, Object> sHmpam = new HashMap<String, Object>();

        sHmpam.put("id", j.getId());
        sHmpam.put("title", j.getTitle());
        sHmpam.put("content", j.getContent());
        sHmpam.put("status", j.getStatus());

        sHmpam.put("createdBy", j.getCreatedBy());
        sHmpam.put("creationDate", j.getCreationDate());
        SecurityUsersEntity createdBy = entityManager.find(SecurityUsersEntity.class,j.getCreatedBy());
        sHmpam.put("createdByName", createdBy.getFirstname()+" "+createdBy.getLastname());

        sHmpam.put("approvalBy", j.getApprovalBy());
        sHmpam.put("approvalDate", j.getApprovalDate());
        SecurityUsersEntity approvalBy = entityManager.find(SecurityUsersEntity.class,j.getCreatedBy());
        sHmpam.put("approvalByName", approvalBy.getFirstname()+" "+approvalBy.getLastname());


        sHmpam.put("rejectedBy", j.getRejectedBy());
        sHmpam.put("rejectionDate", j.getRejectionDate());
        SecurityUsersEntity rejectedBy = entityManager.find(SecurityUsersEntity.class,j.getCreatedBy());
        sHmpam.put("rejectedByName", rejectedBy.getFirstname()+" "+rejectedBy.getLastname());
        sHmpam.put("rejectionReason", j.getRejectionReason());


        sHmpam.put("submittedBy", j.getSubmittedBy());
        sHmpam.put("submitionDate", j.getSubmitionDate());
        SecurityUsersEntity submittedBy = entityManager.find(SecurityUsersEntity.class,j.getCreatedBy());
        sHmpam.put("submittedByName", submittedBy.getFirstname()+" "+submittedBy.getLastname());


        sHmpam.put("publishBy", j.getPublishBy());
        sHmpam.put("publishDate", j.getPublishDate());
        SecurityUsersEntity publishBy = entityManager.find(SecurityUsersEntity.class,j.getCreatedBy());
        sHmpam.put("publishByName", publishBy.getFirstname()+" "+publishBy.getLastname());





        /***topics****/
        List<HashMap<String, Object>> topicsList = new ArrayList<HashMap<String, Object>>();
        String sqlTopics = "select * from news_topics where new_id="+j.getId();
        List<NewsTopicsEntity> newsTopicsEntityList = entityManager.createNativeQuery(sqlTopics,NewsTopicsEntity.class).getResultList();
        for(NewsTopicsEntity topic : newsTopicsEntityList){
            HashMap<String, Object> topicmap = new HashMap<String, Object>();
            topicmap.put("id", topic.getId());
            topicmap.put("topicId", topic.getSubjectId());
            CoreSubjectsEntity subjectsEntity = entityManager.find(CoreSubjectsEntity.class,topic.getSubjectId());
            topicmap.put("title", subjectsEntity.getTitle());
            topicsList.add(topicmap);
        }
        sHmpam.put("topics", topicsList);

        /**approval comments**/
        List<HashMap<String, Object>> commentsList = new ArrayList<HashMap<String, Object>>();
        String sqlcomments = "select * from news_comments comm where 1=1 and news_id= "+j.getId();

        if(Long.valueOf(roleId)== 1L){//episkepths
            sqlcomments += " and (comm.status) = 'Εγκεκριμένο'";
        }else if (Long.valueOf(roleId)== 2L){//dhmosiografos
            sqlcomments += " and ((comm.status) = 'Εγκεκριμένο' or (created_by="+userId+" and comm.news_id="+j.getId()+" ))"; //egkekrimenes + tis dikes tou
        }

        List<NewsCommentsEntity> newsCommentsEntityList
                = (List<NewsCommentsEntity>) entityManager.createNativeQuery(
                sqlcomments, NewsCommentsEntity.class).getResultList();
        for(NewsCommentsEntity commentsEntity : newsCommentsEntityList){
            HashMap<String, Object> cmap = new HashMap<String, Object>();
            cmap=commentsEntity.gatCommentObject(commentsEntity,entityManager);
            commentsList.add(cmap);
        }
        sHmpam.put("comments", commentsList);

        return sHmpam;
    }





}
