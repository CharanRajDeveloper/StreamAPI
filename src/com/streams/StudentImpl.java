package com.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentImpl {
	public static void main(String[] args) {
		List<Student> studentList = Arrays.asList(
				new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Delhi", 122),
				new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
				new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
				new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
				new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
				new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
				new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
				new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
				new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
				new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));

		// ForEach Method
		// studentList.stream().forEach(System.out::println);

		System.out.println("List of Students whose names start with 'A'");
		studentList.stream().filter(p -> p.getFirstName().startsWith("A") || p.getFirstName().startsWith("a"))
				.forEach(System.out::println);
		// System.out.println("List of Students whose Names starts with 'A':" +
		// studentName);
		System.out.println("***************************************");
		System.out.println("Grouping the students based on Departments");
		Map<String, List<Student>> studentgroupbydept = studentList.stream()
				.collect(Collectors.groupingBy(Student::getDepartmantName));
		for (Entry<String, List<Student>> m : studentgroupbydept.entrySet()) {
			System.out.println(m.getKey() + "::" + m.getValue());
		}
		System.out.println("***************************************");
		System.out.println("Find the total count of student using stream");
		long count = studentList.stream().count();
		System.out.println("Total count of Students:" + count);
		System.out.println("*********************************************");
		System.out.println("Find the max age of student");
		OptionalInt i = studentList.stream().mapToInt(p -> p.getAge()).max();
		System.out.println("Max age of student : " + i);
		System.out.println("****************************************");
		System.out.println("Find all departments names");
		List<String> distinctDept = studentList.stream().map(p -> p.getDepartmantName()).distinct()
				.collect(Collectors.toList());
		System.out.println("Distinct Departments");
		distinctDept.stream().forEach(System.out::println);
		System.out.println("******************************************************************");
		// Find the count of student in each department
		System.out.println("Find the count of student in each department");
		Map<String, Long> countofStudent = studentList.stream()
				.collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()));
		for (Map.Entry<String, Long> c : countofStudent.entrySet()) {
			System.out.println(c.getKey() + ":::" + c.getValue());
		}

		System.out.println("--------------------");
		countofStudent.forEach((k, v) -> System.out.println(k + "::" + v));
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("Find the list of students whose age is less than 30");
		List<Student> sag30 = studentList.stream().filter(p -> p.getAge() > 30).map(p -> p)
				.collect(Collectors.toList());
		sag30.forEach(System.out::println);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("Find the list of students whose rank is in between 50 and 100");
		Long cou = studentList.stream().filter(o -> o.getRank() > 50 && o.getRank() < 100)
				.collect(Collectors.counting());
		studentList.stream().filter(o -> o.getRank() > 50 && o.getRank() < 100).map(o -> o).collect(Collectors.toList())
				.forEach(System.out::println);
		System.out.println(cou);
		System.out.println(":::::::::::::::::::::::::::::");
		List<String> l = new ArrayList<String>(Arrays.asList("C", "A", "X", "z"));
		l.stream().filter(p -> p.startsWith("C")).forEach(System.out::println);
		System.out.println(" Find the average age of male and female students");
		studentList.stream()
				.collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)))
				.forEach((k, v) -> System.out.println(k + ":::" + v));

		System.out.println("Find the department who is having maximum number of students");
		Entry<String, Long> d = studentList.stream()
				.collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting())).entrySet().stream()
				.max(Map.Entry.comparingByValue()).get();
		System.out.println("Department having maximum number of students : " + d);

		System.out.println("Find the Students who stays in Delhi and sort them by their names");
		List<Student> s = studentList.stream().filter(p -> p.getCity().equalsIgnoreCase("delhi"))
				.sorted((a, b) -> b.getFirstName().compareTo(a.getFirstName())).collect(Collectors.toList());
		s.forEach(System.out::println);

		System.out.println("Find the average rank in all departments");
		studentList.stream()
				.collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.averagingInt(Student::getRank)))
				.forEach((a, b) -> System.out.println(a + "::" + b));

		System.out.println("Find the highest rank in each department");
		studentList.stream()
				.collect(Collectors.groupingBy(Student::getDepartmantName,
						Collectors.maxBy(Comparator.comparing(Student::getRank))))
				.forEach((a, b) -> System.out.println(a + "::" + b));
		System.out.println("Find the list of students and sort them by their rank");
		System.out.println("Ascending");
		studentList.stream().sorted(Comparator.comparing(Student::getRank)).forEach(System.out::println);
		System.out.println("Descending");
		studentList.stream().sorted(Comparator.comparing(Student::getRank).reversed()).forEach(System.out::println);
		System.out.println("Find the student who has second rank");
		Student secrank = studentList.stream().sorted(Comparator.comparing(Student::getRank)).skip(1).findFirst().get();
		System.out.println("Second highest rank student  : " + secrank);
	}
}
