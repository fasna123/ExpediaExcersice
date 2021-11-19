package com.ibs.expedia.project1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

import java.util.ArrayList;



class SolMethods{
	
//Q1.Write a program to print employee details in each department
	
	public void employeeListByDepartment(List<Employee> empList) {
		Map<Integer, List<Employee>> employeeListByDepartment = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepId));
		Set<java.util.Map.Entry<Integer, List<Employee>>> entrySet = employeeListByDepartment.entrySet();

		for (java.util.Map.Entry<Integer, List<Employee>> entry : entrySet) {
			System.out.println("Employees in Department: " + entry.getKey());
			System.out.println();
			for (Employee employee : entry.getValue()) {
				System.out.println("Employee Name: " + employee.getEmpName());
				System.out.println("Employee Id: " + employee.getEmpId());
				System.out.println("Employee Status: " + employee.getStatus());
				System.out.println("Employee Salary: " + employee.getSalary());
				System.out.println();

			}
			System.out.println();
		}

	}
//Q2.Write a program to print employees count working in each department
	
	public void employeeCountInEachDepartment(List<Employee> empList) {

		Map<Integer, Long> employeeCountByDepartment = empList.stream()
				.collect(Collectors.groupingBy(Employee::getDepId, Collectors.counting()));

		Set<java.util.Map.Entry<Integer, Long>> entrySet1 = employeeCountByDepartment.entrySet();

		for (java.util.Map.Entry<Integer, Long> entry : entrySet1) {

			System.out.println("Department " + ((java.util.Map.Entry<Integer, Long>) entry).getKey());
			System.out.println("Count of Employees: " + entry.getValue());
		}
	}
	
//Q3.Write a program to print active and inactive employees in the given collection
	public void employeeByStatus(List<Employee> empList) {

		System.out.println("Employees Who are Active:\n");
		empList.stream().filter(t -> t.getStatus().equals("active")).map(Employee::getEmpName)
				.forEach(System.out::println);
		System.out.println();
		System.out.println("Employees Who are Inactive:\n");
		empList.stream().filter(t -> t.getStatus().equals("inactive")).map(Employee::getEmpName)
				.forEach(System.out::println);

	}
//Q4.Write a program to print Max/Min Salary from the given collection
	
	public void highestAndLowestSalary(List<Employee> empList) {
		
		
		Optional<Employee> highestPaidEmployee = empList.stream()
				.max(Comparator.comparingDouble(e -> Double.parseDouble(e.getSalary())));
		System.out.println("Highest Salary in the Colletion: \n" + highestPaidEmployee.get().getSalary());

		Optional<Employee> lowestPaidEmployee = empList.stream()
				.min(Comparator.comparingDouble(e -> Double.parseDouble(e.getSalary())));
		System.out.println("Lowest Salary in the Colletion: \n" + lowestPaidEmployee.get().getSalary());
	}

	
//Q5.Write a program to print the max salary of an employee from each department
	
	public void maximumSalaryFromEachDept(List<Employee> empList) {
		Map<Object,Object> topEmployees = empList.stream().collect(groupingBy(e -> e.getDepId(), collectingAndThen(
				maxBy(Comparator.comparingDouble(e -> Double.parseDouble(e.getSalary()))), Optional::get)));

		Set<Entry<Object, Object>> entrySet = topEmployees.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			System.out.println("Department: " + entry.getKey() + " : " + entry.getValue());
			
		}

	}
}

public class MainClass {

	private static List<Employee> empList = Arrays.asList(new Employee("E101", "Ramesh", 1, "active", "5000"),
			new Employee("E102", "Sumesh", 2, "inactive", "15000"), new Employee("E103", "Hila", 3, "active", "15000"),
			new Employee("E104", "Ritz", 4, "active", "25000"), new Employee("E105", "Hazel", 4, "inactive", "10000"),
			new Employee("E106", "Heza", 3, "active", "25000"), new Employee("E107", "Izza", 1, "inactive", " 15000"),
			new Employee("E108", "Ishal", 2, "active", "5000"), new Employee("E109", "Iman", 3, "inactive", "35000"),
			new Employee("E110", "Aman", 1, "active", "15000"));

	public static void main(String[] args) {

		SolMethods s = new SolMethods();

		System.out.println("EMPLOYEE DETAILS BY DEPARTMENT WISE:\n");
		s.employeeListByDepartment(empList);
		System.out.println();

		System.out.println("EMPLOYEE COUNT BY DEPARTMENT WISE:\n");
		s.employeeCountInEachDepartment(empList);
		System.out.println();

		System.out.println("EMPLOYEE NAMES GROUPBY STATUS:\n");
		s.employeeByStatus(empList);
		System.out.println();

		System.out.println("HIGHEST AND LOWEST SALARY IN COLLECTION:\n");
		s.highestAndLowestSalary(empList);
		System.out.println();

		System.out.println("HIGHEST SALARY FROM EACH DEPARTMENT:\n");
		s.maximumSalaryFromEachDept(empList);
		System.out.println();
	}

}
