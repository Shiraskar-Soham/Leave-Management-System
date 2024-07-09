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

    @Column(name= "DateModified")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Long dateModified;

    @Column(name= "isDeleted")
    private Boolean isDeleted;

    public BasicDetails(Long dateCreated, Long dateModified, Boolean isDeleted) {
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
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

    public Long getDateModified() {
        return dateModified;
    }

    public void setDateModified(Long dateModified) {
        this.dateModified = dateModified;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}