package com.revature.P1DemoBackend.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reImbId;
    private String description;
    private double amount;
    private String status="Pending";
    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;

    public Reimbursement() {
    }

    public Reimbursement(Long reImbId, String description, double amount, String status, User userId) {
        this.reImbId = reImbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }

    public Long getReImbId() {
        return reImbId;
    }

    public void setReImbId(Long reImbId) {
        this.reImbId = reImbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement that)) return false;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(reImbId, that.reImbId) && Objects.equals(description, that.description) && Objects.equals(status, that.status) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reImbId, description, amount, status, userId);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reImbId=" + reImbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
