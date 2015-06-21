package com.plan.data;

import javax.persistence.*;

/**
 * Created by snow on 15-6-21.
 */
@Entity
@Table(name = "LocationOfPlan", schema = "", catalog = "Plan")
public class LocationOfPlanEntity {
    private int planId;
    private String location;
    private int number;
    private int id;
    private double lat;
    private double lon;

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

    @Basic
    @Column(name = "lat", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lon", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationOfPlanEntity that = (LocationOfPlanEntity) o;

        if (planId != that.planId) return false;
        if (number != that.number) return false;
        if (id != that.id) return false;
        if (Double.compare(that.lat, lat) != 0) return false;
        if (Double.compare(that.lon, lon) != 0) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = planId;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + id;
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
