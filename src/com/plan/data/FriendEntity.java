package com.plan.data;

import javax.persistence.*;

/**
 * Created by snow on 15-6-10.
 */
@Entity
@Table(name = "Friend", schema = "", catalog = "Plan")
public class FriendEntity {
    private String friendAccount;
    private String userAccount;
    private int id;

    @Basic
    @Column(name = "friendAccount")
    public String getFriendAccount() {
        return friendAccount;
    }

    public void setFriendAccount(String friendAccount) {
        this.friendAccount = friendAccount;
    }

    @Basic
    @Column(name = "userAccount")
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
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

        FriendEntity that = (FriendEntity) o;

        if (id != that.id) return false;
        if (friendAccount != null ? !friendAccount.equals(that.friendAccount) : that.friendAccount != null)
            return false;
        if (userAccount != null ? !userAccount.equals(that.userAccount) : that.userAccount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendAccount != null ? friendAccount.hashCode() : 0;
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
