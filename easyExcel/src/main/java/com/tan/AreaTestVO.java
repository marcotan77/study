package com.tan;

/**
 * @author: Tan.
 * @create: 2020-10-14 14:58
 **/
public class AreaTestVO{
    private String areaName ;
    private Integer number;
    private String manageName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    @Override
    public String toString() {
        return "AreaTestVO{" +
                "areaName='" + areaName + '\'' +
                ", number=" + number +
                ", manageName='" + manageName + '\'' +
                '}';
    }
}
