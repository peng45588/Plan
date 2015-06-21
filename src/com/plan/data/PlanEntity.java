package com.plan.data;

import javax.persistence.*;

/**
 * Created by snow on 15-6-21.
 */
@Entity
@Table(name = "Plan", schema = "", catalog = "Plan")
public class PlanEntity {
    private String title;
    private int planId;
    private Long time;
    private String location;
    private String info;
    private long deadline;
    private Double locationLat;
    private Double locationLon;

    @Basic
    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "planId", nullable = false, insertable = true, updatable = true)
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "time", nullable = true, insertable = true, updatable = true)
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Basic
    @Column(name = "location", nullable = true, insertable = true, updatable = true, length = 45)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "info", nullable = false, insertable = true, updatable = true, length = 180)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "deadline", nullable = false, insertable = true, updatable = true)
    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "locationLat", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    @Basic
    @Column(name = "locationLon", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(Double locationLon) {
        this.locationLon = locationLon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanEntity that = (PlanEntity) o;

        if (planId != that.planId) return false;
        if (deadline != that.deadline) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (locationLat != null ? !locationLat.equals(that.locationLat) : that.locationLat != null) return false;
        if (locationLon != null ? !locationLon.equals(that.locationLon) : that.locationLon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + planId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        result = 31 * result + (locationLat != null ? locationLat.hashCode() : 0);
        result = 31 * result + (locationLon != null ? locationLon.hashCode() : 0);
        return result;
    }
}
