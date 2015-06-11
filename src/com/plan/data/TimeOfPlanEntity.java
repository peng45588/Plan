package com.plan.data;

import javax.persistence.*;

/**
 * Created by snow on 15-6-10.
 */
@Entity
@Table(name = "TimeOfPlan", schema = "", catalog = "Plan")
public class TimeOfPlanEntity {
    private int planId;
    private int time;
    private int number;
    private int id;

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

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

        TimeOfPlanEntity that = (TimeOfPlanEntity) o;

        if (planId != that.planId) return false;
        if (time != that.time) return false;
        if (number != that.number) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = planId;
        result = 31 * result + time;
        result = 31 * result + number;
        result = 31 * result + id;
        return result;
    }
}
