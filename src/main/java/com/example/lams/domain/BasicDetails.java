package com.example.lams.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Builder
public class BasicDetails {
    @Column(name= "DateCreated")
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Long dateCreated;

    @Column(name= "CreatedBy")
    private String creatorId;

    @Column(name= "DateModified")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Long dateModified;

    @Column(name= "modifiedBy")
    private String modifierId;

    @Column(name= "isDeleted")
    private Boolean isDeleted;

    public BasicDetails(Long dateCreated, String creatorId, Long dateModified, String modifierId, Boolean isDeleted) {
        this.dateCreated = dateCreated;
        this.creatorId = creatorId;
        this.dateModified = dateModified;
        this.modifierId = modifierId;
        this.isDeleted = isDeleted;
    }

    public BasicDetails() {
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Long getDateModified() {
        return dateModified;
    }

    public void setDateModified(Long dateModified) {
        this.dateModified = dateModified;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}