/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */

package com.company.credit;

import com.haulmont.thesis.core.entity.Employee;

public class EmployeeCount {

    private Employee employee;
    private Long count;

    public EmployeeCount(Employee employee, Long count) {
        this.employee = employee;
        this.count = count;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}