/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uag.sys.booklender.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Smirnoff
 */
@Entity
@Table(name = "uag_sys_lend_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UagSysLendService.findAll", query = "SELECT u FROM UagSysLendService u")
    , @NamedQuery(name = "UagSysLendService.findByLendServiceId", query = "SELECT u FROM UagSysLendService u WHERE u.lendServiceId = :lendServiceId")
    , @NamedQuery(name = "UagSysLendService.findByLendServiceStartDate", query = "SELECT u FROM UagSysLendService u WHERE u.lendServiceStartDate = :lendServiceStartDate")
    , @NamedQuery(name = "UagSysLendService.findByLendServiceEndDate", query = "SELECT u FROM UagSysLendService u WHERE u.lendServiceEndDate = :lendServiceEndDate")
    , @NamedQuery(name = "UagSysLendService.findByLendServiceActive", query = "SELECT u FROM UagSysLendService u WHERE u.lendServiceActive = :lendServiceActive")})
public class UagSysLendService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LEND_SERVICE_ID")
    private Integer lendServiceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEND_SERVICE_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date lendServiceStartDate;
    @Column(name = "LEND_SERVICE_END_DATE")
    @Temporal(TemporalType.DATE)
    private Date lendServiceEndDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEND_SERVICE_ACTIVE")
    private boolean lendServiceActive;
    @JoinColumn(name = "LEND_SERVICE_RESOURCE_ID", referencedColumnName = "RESOURCE_ID")
    @ManyToOne(optional = false)
    private UagSysInventory lendServiceResourceId;
    @JoinColumn(name = "LEND_SERVICE_USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private UagSysUser lendServiceUserId;

    public UagSysLendService() {
    }

    public UagSysLendService(Integer lendServiceId) {
        this.lendServiceId = lendServiceId;
    }

    public UagSysLendService(Integer lendServiceId, Date lendServiceStartDate, boolean lendServiceActive) {
        this.lendServiceId = lendServiceId;
        this.lendServiceStartDate = lendServiceStartDate;
        this.lendServiceActive = lendServiceActive;
    }

    public Integer getLendServiceId() {
        return lendServiceId;
    }

    public void setLendServiceId(Integer lendServiceId) {
        this.lendServiceId = lendServiceId;
    }

    public Date getLendServiceStartDate() {
        return lendServiceStartDate;
    }

    public void setLendServiceStartDate(Date lendServiceStartDate) {
        this.lendServiceStartDate = lendServiceStartDate;
    }

    public Date getLendServiceEndDate() {
        return lendServiceEndDate;
    }

    public void setLendServiceEndDate(Date lendServiceEndDate) {
        this.lendServiceEndDate = lendServiceEndDate;
    }

    public boolean getLendServiceActive() {
        return lendServiceActive;
    }

    public void setLendServiceActive(boolean lendServiceActive) {
        this.lendServiceActive = lendServiceActive;
    }

    public UagSysInventory getLendServiceResourceId() {
        return lendServiceResourceId;
    }

    public void setLendServiceResourceId(UagSysInventory lendServiceResourceId) {
        this.lendServiceResourceId = lendServiceResourceId;
    }

    public UagSysUser getLendServiceUserId() {
        return lendServiceUserId;
    }

    public void setLendServiceUserId(UagSysUser lendServiceUserId) {
        this.lendServiceUserId = lendServiceUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lendServiceId != null ? lendServiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UagSysLendService)) {
            return false;
        }
        UagSysLendService other = (UagSysLendService) object;
        if ((this.lendServiceId == null && other.lendServiceId != null) || (this.lendServiceId != null && !this.lendServiceId.equals(other.lendServiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.uag.sys.booklender.entities.UagSysLendService[ lendServiceId=" + lendServiceId + " ]";
    }
    
}
