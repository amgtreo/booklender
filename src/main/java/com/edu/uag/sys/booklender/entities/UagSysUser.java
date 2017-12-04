/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uag.sys.booklender.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Smirnoff
 */
@Entity
@Table(name = "uag_sys_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UagSysUser.findAll", query = "SELECT u FROM UagSysUser u")
    , @NamedQuery(name = "UagSysUser.findByUserId", query = "SELECT u FROM UagSysUser u WHERE u.userId = :userId")
    , @NamedQuery(name = "UagSysUser.findByUserCredentialId", query = "SELECT u FROM UagSysUser u WHERE u.userCredentialId = :userCredentialId")
    , @NamedQuery(name = "UagSysUser.findByUserName", query = "SELECT u FROM UagSysUser u WHERE u.userName = :userName")
    , @NamedQuery(name = "UagSysUser.findByUserEmail", query = "SELECT u FROM UagSysUser u WHERE u.userEmail = :userEmail")
    , @NamedQuery(name = "UagSysUser.findByUserPassword", query = "SELECT u FROM UagSysUser u WHERE u.userPassword = :userPassword")
    , @NamedQuery(name = "UagSysUser.findByUserCareer", query = "SELECT u FROM UagSysUser u WHERE u.userCareer = :userCareer")
    , @NamedQuery(name = "UagSysUser.findByUserActiveCode", query = "SELECT u FROM UagSysUser u WHERE u.userActiveCode = :userActiveCode")
    , @NamedQuery(name = "UagSysUser.findByUserActive", query = "SELECT u FROM UagSysUser u WHERE u.userActive = :userActive")})
public class UagSysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_CREDENTIAL_ID")
    private int userCredentialId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "USER_NAME")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "USER_CAREER")
    private String userCareer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "USER_ACTIVE_CODE")
    private String userActiveCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ACTIVE")
    private boolean userActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lendServiceUserId")
    private Collection<UagSysLendService> uagSysLendServiceCollection;

    public UagSysUser() {
    }

    public UagSysUser(Integer userId) {
        this.userId = userId;
    }

    public UagSysUser(Integer userId, int userCredentialId, String userName, String userEmail, String userPassword, String userCareer, String userActiveCode, boolean userActive) {
        this.userId = userId;
        this.userCredentialId = userCredentialId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCareer = userCareer;
        this.userActiveCode = userActiveCode;
        this.userActive = userActive;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getUserCredentialId() {
        return userCredentialId;
    }

    public void setUserCredentialId(int userCredentialId) {
        this.userCredentialId = userCredentialId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCareer() {
        return userCareer;
    }

    public void setUserCareer(String userCareer) {
        this.userCareer = userCareer;
    }

    public String getUserActiveCode() {
        return userActiveCode;
    }

    public void setUserActiveCode(String userActiveCode) {
        this.userActiveCode = userActiveCode;
    }

    public boolean getUserActive() {
        return userActive;
    }

    public void setUserActive(boolean userActive) {
        this.userActive = userActive;
    }

    @XmlTransient
    public Collection<UagSysLendService> getUagSysLendServiceCollection() {
        return uagSysLendServiceCollection;
    }

    public void setUagSysLendServiceCollection(Collection<UagSysLendService> uagSysLendServiceCollection) {
        this.uagSysLendServiceCollection = uagSysLendServiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UagSysUser)) {
            return false;
        }
        UagSysUser other = (UagSysUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.uag.sys.booklender.entities.UagSysUser[ userId=" + userId + " ]";
    }
    
}
