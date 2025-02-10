package com.be_servicie.saigon_travel.be_service.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (this.createdBy == null) {
            this.createdBy = "Admin";
        }
        this.createdAt = LocalDateTime.now();

        if (this.updatedBy == null) {
            this.updatedBy = "Admin";
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (this.updatedBy == null) {
            this.updatedBy = "Admin";
        }
        this.updatedAt = LocalDateTime.now();
    }
}
