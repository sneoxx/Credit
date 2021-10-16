/*
 * Copyright (c) 2021 com.company.credit.service
 */
package com.company.credit.service;

import com.company.credit.EmployeeCount;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.thesis.core.entity.Employee;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

/**
 * @author zaraevrs
 */
@Service(EmployeeService.NAME)
public class EmployeeServiceBean implements EmployeeService {

    @Inject
    protected Persistence persistence;

    @Inject
    protected Metadata metadata;

    @Override
    public List<Map<String, Object>> getСompletedTasksEmployees(List<Employee> listEmployeesWithFinishedTask) {
//        Map<String, Long> resultMap = list.stream().collect(Collectors.groupingBy(, Collectors.counting()));
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<Employee, Integer> resultMapEmployeesWithNumberOfCompletedTasks = new HashMap<>();
        Set<Employee> setEmployeesWithFinishedTask = new HashSet<>(listEmployeesWithFinishedTask);
        for (Employee employee : setEmployeesWithFinishedTask) {
            int occurrences = Collections.frequency(listEmployeesWithFinishedTask, employee);
            resultMapEmployeesWithNumberOfCompletedTasks.put(employee, occurrences);
        }
        for (Map.Entry<Employee, Integer> entry : resultMapEmployeesWithNumberOfCompletedTasks.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("firstName", entry.getKey().getFirstName());
            map.put("lastName", entry.getKey().getLastName());
            map.put("department.name", entry.getKey().getDepartment().getName());
            map.put("numberOfCompletedTasks", entry.getValue());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getСompletedTasksEmployeeCount(List<EmployeeCount> listСompletedTasksEmployeeCount) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listСompletedTasksEmployeeCount)) {
            for (EmployeeCount item : listСompletedTasksEmployeeCount) {
                Map<String, Object> map = new HashMap<>();
                map.put("firstName", item.getEmployee().getFirstName());
                map.put("lastName", item.getEmployee().getFirstName());
                map.put("department.name", item.getEmployee().getDepartment() != null ? item.getEmployee().getDepartment().getName() : "");
                map.put("numberOfCompletedTasks", item.getCount());
                resultList.add(map);
            }
        }
        return resultList;
    }
}
//    public List<Map<String,Object>> getСompletedTasksEmployees1 (List<Employee> list) {
//        Map<String, Long> resultMap = list.stream().collect(Collectors.groupingBy(, Collectors.counting()));
//        List<Map<String, Object>> resultList = new ArrayList<>();
//
//            for (Employee u : list) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("firstName", u.getFirstName());
//                map.put("lastName", u.getLastName());
//                map.put("department.name", u.getDepartment().getName());
//                for (Map.Entry entry : resultMap.entrySet()) {
//                    if (entry.getKey().equals(u.getFirstName())) {
//                        map.put("numberOfCompletedTasks", entry.getValue());
//                    }
//                }
//                resultList.add(map);
//            }
//
//        return resultList;
//    }

//    //Создать отчет по всем сотрудникам и показывать количество завершенных ими задач
//    @Override
//    public Employee[] getСompletedTasksEmployees (Employee employee) {
//        Transaction tx = persistence.createTransaction();
//        List<Employee> list = new ArrayList<>();
//        List<Employee> result = new ArrayList<>();
//
//        if (employee != null) {
//            try {
//                EntityManager em = persistence.getEntityManager();
//
//                TypedQuery<Employee> query = em.createNativeQuery(
//                  //      "select count(*) from df$Employee u where id = :employeeId ", Employee.class);
//                        "select u from df$Employee u, credit$Task c where u.user = c.executor and c.state = ',Finished,'", Employee.class);
//                query.setParameter("employeeId", employee.getId());
//                query.setView(metadata.getViewRepository().getView(Employee.class, View.MINIMAL));
//                list = query.getResultList();
//
////                Query query = persistence.getEntityManager().createNativeQuery(
////                        "select count(*) from SEC_USER where login = #login");
////                query.setParameter("login", "admin");
////                long count = (long) query.getSingleResult();
//
//
////                SELECT AVG(o.quantity) FROM app_Order o
////                TypedQuery<Employee> query = em.createQuery(
////                        "select c from df$Employee u, credit$Task c where u.id = :employeeId and c.state = \",Finished,\"", Employee.class);
////                        ('select u from df$Employee u, credit$Task c where u.id = ?1 and c.state = ",Finished,"')
//                tx.commit();
//            } finally {
//                tx.end();
//            }
//            result.add(result.get(list.size()-1));
//        }
//        Employee[] myArray = new Employee[list.size()];
//        myArray[0] = result.get(list.size()-1);
//        return myArray;
//    }


//    //Создать отчет по всем сотрудникам и показывать количество завершенных ими задач
//    @Override
//    public Employee[] getСompletedTasksEmployees3 (Employee employee) {
//        Transaction tx = persistence.createTransaction();
//        List<Employee> list = new ArrayList<>();
//        List<Employee> result = new ArrayList<>();
//
//        if (employee != null) {
//            try {
//                EntityManager em = persistence.getEntityManager();
//                Query query = em.createQuery(
//                        "select c from df$Employee u where u.id = :employeeId ");
////                SELECT AVG(o.quantity) FROM app_Order o
////                TypedQuery<Employee> query = em.createQuery(
////                        "select c from df$Employee u, credit$Task c where u.id = :employeeId and c.state = \",Finished,\"", Employee.class);
////                        ('select u from df$Employee u, credit$Task c where u.id = ?1 and c.state = ",Finished,"')
//
//                query.setView(metadata.getViewRepository().getView(Employee.class, View.MINIMAL));
//                query.setParameter("employeeId", employee.getId());
//                list = query.getResultList();
//                                tx.commit();
//              } finally {
//                tx.end();
//            }
//
//            result.add(result.get(list.size()-1));
//        }
//        Employee[] myArray = new Employee[list.size()];
//        myArray[0] = result.get(list.size()-1);
//        return myArray;
//    }

//
//    def result = []
//    int a = 0
//    def result1 = []
//    transactional { em ->
//            def query = em.createQuery('select u from df$Employee u, credit$Task c where u.id = ?1 and c.state = ",Finished,"')
//        query.setParameter(1, params['entities'])
//        query.resultList.each { employee ->
//                a = a+1
//            result.add(['firstName': employee.firstName, 'lastName': employee.lastName, 'department.name': employee.department.name, 'numberOfCompletedTasks': a],)
//        }
//
//        result1.add(result.get(result.size-1))
//
//    }
//return result1


