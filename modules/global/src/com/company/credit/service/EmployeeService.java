/*
 * Copyright (c) 2021 com.company.credit.service
 */
package com.company.credit.service;

import com.company.credit.EmployeeCount;
import com.haulmont.thesis.core.entity.Employee;

import java.util.List;
import java.util.Map;

/**
 * @author zaraevrs
 */
public interface EmployeeService {
    String NAME = "credit_EmployeeService";

     List<Map<String,Object>> getŠ”ompletedTasksEmployees (List<Employee> listEmployeesWithFinishedTask);

     List<Map<String,Object>> getŠ”ompletedTasksEmployeeCount(List<EmployeeCount> listEmployeesWithNumberOfCompletedTasks );

}