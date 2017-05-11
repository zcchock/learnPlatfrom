package com.zc.shiro.model;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于验证时角色信息的model
 */
@SuppressWarnings("serial")
public class RoleCertificate implements Serializable {
    private Long id; //编号
    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述
    private List<Long> permissionIds; //权限

    public RoleCertificate() {
    }

    public RoleCertificate(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getpermissionIds() {
        if(permissionIds == null) {
            permissionIds = new ArrayList<Long>();
        }
        return permissionIds;
    }

    public void setpermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

    public String getpermissionIdsStr() {
        if(CollectionUtils.isEmpty(permissionIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long permissionId : permissionIds) {
            s.append(permissionId);
            s.append(",");
        }
        return s.toString();
    }

    public void setpermissionIdsStr(String permissionIdsStr) {
        if(StringUtils.isEmpty(permissionIdsStr)) {
            return;
        }
        String[] permissionIdStrs = permissionIdsStr.split(",");
        for(String permissionIdStr : permissionIdStrs) {
            if(StringUtils.isEmpty(permissionIdStr)) {
                continue;
            }
            getpermissionIds().add(Long.valueOf(permissionIdStr));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleCertificate roleCertificate = (RoleCertificate) o;

        if (id != null ? !id.equals(roleCertificate.id) : roleCertificate.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
