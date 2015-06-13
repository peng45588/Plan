package com.plan.data;

import javax.persistence.*;

/**
 * Created by snow on 15-6-13.
 */
@Entity
@Table(name = "Friend", schema = "", catalog = "Plan")
@IdClass(FriendEntityPK.class)
public class FriendEntity {
    private String friendAccount;
    private String userAccount;

    @Id
    @Column(name = "friendAccount", nullable = false, insertable = true, updatable = true, length = 45)
    public String getFriendAccount() {
        return friendAccount;
    }

    public void setFriendAccount(String friendAccount) {
        this.friendAccount = friendAccount;
    }

    @Id
    @Column(name = "userAccount", nullable = false, insertable = true, updatable = true, length = 45)
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendEntity that = (FriendEntity) o;

        if (friendAccount != null ? !friendAccount.equals(that.friendAccount) : that.friendAccount != null)
            return false;
        if (userAccount != null ? !userAccount.equals(that.userAccount) : that.userAccount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendAccount != null ? friendAccount.hashCode() : 0;
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        return result;
    }
}
