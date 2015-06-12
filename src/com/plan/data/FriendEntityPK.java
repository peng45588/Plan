package com.plan.data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by snow on 15-6-12.
 */
public class FriendEntityPK implements Serializable {
    private String friendAccount;
    private String userAccount;

    @Column(name = "friendAccount")
    @Id
    public String getFriendAccount() {
        return friendAccount;
    }

    public void setFriendAccount(String friendAccount) {
        this.friendAccount = friendAccount;
    }

    @Column(name = "userAccount")
    @Id
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

        FriendEntityPK that = (FriendEntityPK) o;

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
