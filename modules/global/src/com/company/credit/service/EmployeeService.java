/*
 * Copyright (c) 2021 com.company.credit.service
 */
package com.company.credit.service;

import com.haulmont.thesis.core.entity.Employee;

import java.util.List;
import java.util.Map;

/**
 * @author zaraevrs
 */
public interface EmployeeService {
    String NAME = "credit_EmployeeService";

//    Employee[] getСompletedTasksEmployees (Employee employee);
     List<Map<String,Object>> getСompletedTasksEmployees (List<Employee> listEmployeesWithFinishedTask);
}