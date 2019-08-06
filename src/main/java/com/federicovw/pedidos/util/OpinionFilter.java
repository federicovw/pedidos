package com.federicovw.pedidos.util;

import java.time.LocalDateTime;

/**
 * This class contains the values that can be used to filter a search
 * for Opinions in the database.
 */
public class OpinionFilter {
    private Long id;
    private LocalDateTime createDateFrom;
    private LocalDateTime createDateTo;
    private LocalDateTime lastUpdateFrom;
    private LocalDateTime lastUpdateTo;
    private Long userId;
    private Long shopId;
    private Long purchaseId;
    private String comment;
    private Integer score;
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDateFrom() {
        return createDateFrom;
    }

    public void setCreateDateFrom(LocalDateTime createDateFrom) {
        this.createDateFrom = createDateFrom;
    }

    public LocalDateTime getCreateDateTo() {
        return createDateTo;
    }

    public void setCreateDateTo(LocalDateTime createDateTo) {
        this.createDateTo = createDateTo;
    }

    public LocalDateTime getLastUpdateFrom() {
        return lastUpdateFrom;
    }

    public void setLastUpdateFrom(LocalDateTime lastUpdateFrom) {
        this.lastUpdateFrom = lastUpdateFrom;
    }

    public LocalDateTime getLastUpdateTo() {
        return lastUpdateTo;
    }

    public void setLastUpdateTo(LocalDateTime lastUpdateTo) {
        this.lastUpdateTo = lastUpdateTo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
