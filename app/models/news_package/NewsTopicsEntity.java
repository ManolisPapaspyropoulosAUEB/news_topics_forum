package models.news_package;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "news_topics", schema = "assigment_forum", catalog = "")
public class NewsTopicsEntity {
    private long id;
    private Long subjectId;
    private Long newId;


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
    @Column(name = "subject_id")
    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "new_id")
    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsTopicsEntity that = (NewsTopicsEntity) o;
        return id == that.id &&
                Objects.equals(subjectId, that.subjectId) &&
                Objects.equals(newId, that.newId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectId, newId);
    }


}
