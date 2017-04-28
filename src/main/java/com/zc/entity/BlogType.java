package com.zc.entity;

/**
 * Created by chock on 2017/4/28.
 */
public class BlogType {
    private int typeId;
    private String typeName;
    private String typeDesc;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                '}';
    }
}
