package org.wyy.tech.entity.biz;

import org.wyy.tech.entity.base.BaseEntity;

/**
 * @author Wyy
 **/
public class Employee extends BaseEntity {
    private static final long serialVersionUID = 8497318806574906870L;
    private Long id;
    private String name;
    private String empNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
}
