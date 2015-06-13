package com.plan.data;

import javax.persistence.*;

/**
 * Created by snow on 15-6-13.
 */
@Entity
@Table(name = "PeopleInPlan", schema = "", catalog = "Plan")
public class PeopleInPlanEntity {
    private String account;
    private int planId;
    private Long returnTime;
    private int id;

    @Basic
    @Column(name = "account", nullable = false, insertable = true, updatable = true, length = 45)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "planId", nullable = false, insertable = true, updatable = true)
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "returnTime", nullable = true, insertable = true, updatable = true)
    public Long getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Long returnTime) {
        this.returnTime = returnTime;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeopleInPlanEntity that = (PeopleInPlanEntity) o;

        if (planId != that.planId) return false;
        if (id != that.id) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (returnTime != null ? !returnTime.equals(that.returnTime) : that.returnTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + planId;
        result = 31 * result + (returnTime != null ? returnTime.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
