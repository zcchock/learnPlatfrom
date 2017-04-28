package com.zc.entity;

/**
 * Created by chock on 2017/4/28.
 */
public class Role {
    private int roleId;
    private String typeContent;
    private String typeLimit;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getTypeLimit() {
        return typeLimit;
    }

    public void setTypeLimit(String typeLimit) {
        this.typeLimit = typeLimit;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", typeContent='" + typeContent + '\'' +
                ", typeLimit='" + typeLimit + '\'' +
                '}';
    }
}
