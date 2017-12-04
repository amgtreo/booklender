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
@Table(name = "uag_sys_inventory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UagSysInventory.findAll", query = "SELECT u FROM UagSysInventory u")
    , @NamedQuery(name = "UagSysInventory.findByResourceId", query = "SELECT u FROM UagSysInventory u WHERE u.resourceId = :resourceId")
    , @NamedQuery(name = "UagSysInventory.findByResourceType", query = "SELECT u FROM UagSysInventory u WHERE u.resourceType = :resourceType")
    , @NamedQuery(name = "UagSysInventory.findByResourceIsbn", query = "SELECT u FROM UagSysInventory u WHERE u.resourceIsbn = :resourceIsbn")
    , @NamedQuery(name = "UagSysInventory.findByResourceTitle", query = "SELECT u FROM UagSysInventory u WHERE u.resourceTitle = :resourceTitle")
    , @NamedQuery(name = "UagSysInventory.findByResourceAuthor", query = "SELECT u FROM UagSysInventory u WHERE u.resourceAuthor = :resourceAuthor")
    , @NamedQuery(name = "UagSysInventory.findByResourcePublisher", query = "SELECT u FROM UagSysInventory u WHERE u.resourcePublisher = :resourcePublisher")
    , @NamedQuery(name = "UagSysInventory.findByResourceLanguage", query = "SELECT u FROM UagSysInventory u WHERE u.resourceLanguage = :resourceLanguage")
    , @NamedQuery(name = "UagSysInventory.findByResourcePages", query = "SELECT u FROM UagSysInventory u WHERE u.resourcePages = :resourcePages")
    , @NamedQuery(name = "UagSysInventory.findByResourceLocation", query = "SELECT u FROM UagSysInventory u WHERE u.resourceLocation = :resourceLocation")})
public class UagSysInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESOURCE_ID")
    private Integer resourceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "RESOURCE_TYPE")
    private String resourceType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "RESOURCE_ISBN")
    private String resourceIsbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "RESOURCE_TITLE")
    private String resourceTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "RESOURCE_AUTHOR")
    private String resourceAuthor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "RESOURCE_PUBLISHER")
    private String resourcePublisher;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "RESOURCE_LANGUAGE")
    private String resourceLanguage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "RESOURCE_PAGES")
    private String resourcePages;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "RESOURCE_LOCATION")
    private String resourceLocation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lendServiceResourceId")
    private Collection<UagSysLendService> uagSysLendServiceCollection;

    public UagSysInventory() {
    }

    public UagSysInventory(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public UagSysInventory(Integer resourceId, String resourceType, String resourceIsbn, String resourceTitle, String resourceAuthor, String resourcePublisher, String resourceLanguage, String resourcePages, String resourceLocation) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.resourceIsbn = resourceIsbn;
        this.resourceTitle = resourceTitle;
        this.resourceAuthor = resourceAuthor;
        this.resourcePublisher = resourcePublisher;
        this.resourceLanguage = resourceLanguage;
        this.resourcePages = resourcePages;
        this.resourceLocation = resourceLocation;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceIsbn() {
        return resourceIsbn;
    }

    public void setResourceIsbn(String resourceIsbn) {
        this.resourceIsbn = resourceIsbn;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getResourceAuthor() {
        return resourceAuthor;
    }

    public void setResourceAuthor(String resourceAuthor) {
        this.resourceAuthor = resourceAuthor;
    }

    public String getResourcePublisher() {
        return resourcePublisher;
    }

    public void setResourcePublisher(String resourcePublisher) {
        this.resourcePublisher = resourcePublisher;
    }

    public String getResourceLanguage() {
        return resourceLanguage;
    }

    public void setResourceLanguage(String resourceLanguage) {
        this.resourceLanguage = resourceLanguage;
    }

    public String getResourcePages() {
        return resourcePages;
    }

    public void setResourcePages(String resourcePages) {
        this.resourcePages = resourcePages;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(String resourceLocation) {
        this.resourceLocation = resourceLocation;
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
        hash += (resourceId != null ? resourceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UagSysInventory)) {
            return false;
        }
        UagSysInventory other = (UagSysInventory) object;
        if ((this.resourceId == null && other.resourceId != null) || (this.resourceId != null && !this.resourceId.equals(other.resourceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edu.uag.sys.booklender.entities.UagSysInventory[ resourceId=" + resourceId + " ]";
    }
    
}
