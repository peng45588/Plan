package com.plan.data;

import javax.persistence.*;

/**
 * Created by snow on 15-6-10.
 */
@Entity
@Table(name = "PeopleInPlan", schema = "", catalog = "Plan")
public class PeopleInPlanEntity {
    private String account;
    private int planId;
    private int time;
    private int id;

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "planId")
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "time")
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Id
    @Column(name = "id")
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
        if (time != that.time) return false;
        if (id != that.id) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + planId;
        result = 31 * result + time;
        result = 31 * result + id;
        return result;
    }
}
