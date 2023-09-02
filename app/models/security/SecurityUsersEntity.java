package models.security;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "security_users", schema = "framework_db", catalog = "")
public class SecurityUsersEntity {
    private long userId;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private Date creationDate;
    private Date updateDate;
    private Integer active;
    private Long roleId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "active")
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityUsersEntity that = (SecurityUsersEntity) o;
        return userId == that.userId &&
                Objects.equals(username, that.username) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(password, that.password) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(active, that.active) &&
                Objects.equals(roleId, that.roleId) ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, firstname, lastname, password, creationDate, updateDate, active, roleId);
    }


    public  String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }


    @SuppressWarnings({"Duplicates", "unchecked"})
    public  String getRoleByUserId(Long userId , EntityManager entityManager) {
        String role="";
        SecurityUsersEntity securityUsersEntity = entityManager.find(SecurityUsersEntity.class,userId);
        String sqlSaaRole="select * from security_roles where roleid="+securityUsersEntity.getRoleId();
        List<SecurityRolesEntity> securityRolesEntityList = entityManager.createNativeQuery(sqlSaaRole,SecurityRolesEntity.class).getResultList();
        if(securityRolesEntityList.size()>0){
            role=securityRolesEntityList.get(0).getTitle();
        }else{
            role="-";
        }
        return role;
    }


    public Timestamp stringToTimestampHms(String date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if ((date != null) && (!date.equalsIgnoreCase("")) && !date.equalsIgnoreCase("Invalid date") ) {
            Date dateUserTimeStamp = null;
            try {
                dateUserTimeStamp = formatter.parse(date);
                Timestamp timestamp = new Timestamp(dateUserTimeStamp.getTime());
                return timestamp;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }


}
