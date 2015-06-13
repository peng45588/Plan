package com.plan.data;

import javax.persistence.*;

/**
 * Created by snow on 15-6-13.
 */
@Entity
@Table(name = "LocationOfPlan", schema = "", catalog = "Plan")
public class LocationOfPlanEntity {
    private int planId;
    private String location;
    private int number;
    private int id;

    @Basic
    @Column(name = "planId", nullable = false, insertable = true, updatable = true)
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "location", nullable = false, insertable = true, updatable = true, length = 45)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "number", nullable = false, insertable = true, updatable = true)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

        LocationOfPlanEntity that = (LocationOfPlanEntity) o;

        if (planId != that.planId) return false;
        if (number != that.number) return false;
        if (id != that.id) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = planId;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + id;
        return result;
    }
}
