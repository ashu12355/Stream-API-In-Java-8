package com.streamApi.stream_Api.StreamJava8;

import org.springframework.beans.factory.annotation.Value;

import javax.sound.midi.Soundbank;
import java.security.Key;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiPart2 {
    static List<Employee> employees = Arrays.asList(
            new Employee(101, "Amit", "IT", 60000),
            new Employee(102, "Rahul", "HR", 45000),
            new Employee(103, "Priya", "IT", 75000),
            new Employee(104, "Neha", "Finance", 55000),
            new Employee(105, "Rohit", "IT", 50000),
            new Employee(101, "Amit", "IT", 60000),
            new Employee(106, "Sneha", "HR", 65000),
            new Employee(107, "Vikas", "Finance", 70000),
            new Employee(108, "Anjali", "IT", 80000),
            new Employee(109, "Karan", "Sales", 48000),
            new Employee(110, "Pooja", "Sales", 58000)

    );

    //1. Count the total number of employees.
    public static void getTotalEmployee() {

        Long totalEmployee = employees
                .stream()
                .count();
        System.out.println(totalEmployee);

    }

    //2. Find the first employee whose salary is greater than 50,000.
    public static void getSalaryGreaterThan() {

        Optional<Employee> ep = employees
                .stream()
                .filter(employee -> employee.getSalary() > 50000)
                .findFirst();

        ep.ifPresent(System.out::println);

    }
    //3.Group employees by department and count employees in each department.

    public static void deptCount() {

        Map<String, Long> deptCount = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment(),
                        Collectors.counting()));

        System.out.println(deptCount);

    }
    //4. Find the average salary of all employees.

    public static void averageSalary() {

        Double averageSalary = employees
                .stream()
                .mapToDouble(employees -> employees.getSalary())
                .average()
                .getAsDouble();

        System.out.println(averageSalary);
    }
    //5. Find the average salary department-wise

    public static void avgSalryDeptWise() {

        Map<String, Double> avgSalryDeptWise = employees
                .stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment(),
                        Collectors.averagingDouble(employees -> employees.getSalary())));

        System.out.println(avgSalryDeptWise);
    }

    // 6. Find the total salary department-wise.

    public static void totalSalDeptWise() {

        Map<String, Double> totalSalDeptWise =
                employees.stream()
                        .collect(Collectors.groupingBy(employee -> employee.getDepartment(),
                                Collectors.summingDouble(employees -> employees.getSalary())));

        System.out.println(totalSalDeptWise);
    }

//  7. Check if any employee has a salary greater than 81,000.

    public static void checkSalGreaterThan() {

        List<Employee> empp = employees
                .stream()
                .filter(e -> e.getSalary() > 81000)
                .collect(Collectors.toList());

        System.out.println(empp);

    }
    //8. Check if all employees have salaries greater than 55,000.

    public static void checkAllEmpHaveSalaryGreaterThan() {

        Boolean result =
                employees.stream()
                        .noneMatch(employees -> employees.getSalary() < 55000);

        System.out.println(result);
    }

    // 9. Check if no employee has a salary less than 45,000.
    public static void checkAllEmpHaveSalaryLessThan() {
        Boolean result = employees.stream()
                .allMatch(employees -> employees.getSalary() >= 45000);

        System.out.println(result);
    }

    // 10. Find the sum of all employee salaries.

    public static void sumSal() {

        Double totalSal = employees
                .stream()
                .mapToDouble(e -> e.getSalary())
                .sum();
        System.out.println(totalSal);
    }

//11. Find the highest salary among employees.

    public static void maxSalary() {
        Double maxSal = employees
                .stream()
                .mapToDouble(e -> e.getSalary())
                .max()
                .getAsDouble();
        System.out.println(maxSal);

    }

    //12. Find the top 2 highest-paid employees.

    public static void highestPaidEmp() {

        List<Employee> empList =
                employees
                        .stream()
                        .sorted((a, b) -> b.getSalary() > a.getSalary() ? 1 : -1)
                        .limit(2)
                        .collect(Collectors.toList());

        System.out.println(empList);

        // //13. Find the names of the top 2 highest-paid employees.

        empList.forEach(
                e -> System.out.println("Name : " + e.getName()));

    }

    //14. Group employee names by department.

    public static void empNameByDept() {


        Map<String, List<String>> empList =
                employees
                        .stream()
                        .collect(Collectors.groupingBy(e -> e.getDepartment(),
                                Collectors.mapping(e -> e.getName(),
                                        Collectors.toList())));
        System.out.println(empList);
    }

    //15. Find the department with the highest average salary. [* * * * *]

    public static void deptWithHighestAvgSalary() {

        Map<String, Double> result = employees
                .stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(),
                        Collectors.averagingDouble(e -> e.getSalary())
                ));

        result.entrySet()
                .stream()
                .max((a, b) -> Double.compare(a.getValue(), b.getValue()))
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);
    }

    //16. Print the department name along with its highest average salary. [* * * * *]

    public static void deptNameWithHighestAvgSalary() {

        Map<String, Double> result = employees
                .stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(),
                        Collectors.averagingDouble(e -> e.getSalary())
                ));

        result.entrySet()
                .stream()
                .max((a, b) -> a.getValue().compareTo(b.getValue()))
                .ifPresent(System.out::println);
    }

    //17. Find the department with the highest total salary payout.  [* * * * *]

    public static void deptWithHighTotalSal() {

        Map<String, Double> result = employees
                .stream()
                .collect(Collectors.groupingBy(a -> a.getDepartment(),
                        Collectors.summingDouble(a -> a.getSalary())
                ));

        result.entrySet()
                .stream()
                .max((a, b) -> a.getValue().compareTo(b.getValue()))
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);
    }

    //18. Find the second-highest salary among employees.

    public static void secondHighestSalary() {


        Optional<Double> result = employees
                .stream()
                .map(employee -> employee.getSalary())
                .distinct()
                .sorted((a, b) -> b.compareTo(a))
                .skip(1)
                .findFirst();

        result.ifPresent(System.out::print);

    }

    // 19. Find the employee with the second-highest salary.

    public static void empWithSecondHighSalary() {

        Optional<Employee> result = employees.stream()
                .sorted((a, b) -> b.getSalary() > a.getSalary() ? 1 : -1)
                .distinct()
                .skip(1)
                .findFirst();

        result.ifPresent(System.out::print);
    }

    //20. Group employees department-wise and sort them by salary in descending order.

    public static void groupEmpSortBySalDesc() {

        Map<String,List<Employee>> result = employees
                .stream()
                .collect(Collectors.groupingBy(e->e.getDepartment()));


        result
                .forEach((Key, Value)->{

            Value.sort((a,b)-> Double.compare(b.getSalary(),a.getSalary()));
        });

        System.out.println(result);

    }

//    21. Find the highest-paid employee in each department.
    //or
    //  24. Group employees by department and return the employee with the highest salary in
    //each department.
    public static void highesPaidEmployeeInEachDept() {

        Map<String,List<Employee>> result = employees
                .stream()
                        .collect(Collectors.groupingBy(e->e.getDepartment()));

        Map<String,Employee> highestPaid = new HashMap<>();

        result.forEach((dept,salary)->{

           Employee employee =  salary
                   .stream()
                   .distinct()
                   .max((a,b)-> Double.compare(a.getSalary(),b.getSalary()))
                   .get();

            highestPaid.put(dept,employee);
        });

        System.out.println(highestPaid);


        //25. Convert grouped department-wise highest salary employees into a formatted list output.

        List<String> formattedList = highestPaid
                .entrySet()
                .stream()
                .map(e->"Id : "+ e.getValue().getId() + " Name : "+ e.getValue().getName() + " Salary is : "+ e.getValue().getSalary())
                .collect(Collectors.toList());

        System.out.println(formattedList);
    }

    //22. Find the highest salary in each department.

    public static void highestSalInEachDept() {

        Map<String,List<Double>> result = employees
                .stream()
                .collect(Collectors.groupingBy(e->e.getDepartment(),
                        Collectors.mapping(e->e.getSalary(),
                                Collectors.toList())
                ));

        Map<String,Double> highestSal = new HashMap<>();
        result.forEach((Dept,Salary)-> {

                Double highSal = Salary
                        .stream()
                        .max((a,b)->Double.compare(a,b))
                        .get();

                    highestSal.put(Dept,highSal);
                }
                );
        System.out.println(highestSal);

    }

    //23. Group employees by department and return employee names sorted by salary descending within each department.

    public static void empNameByDeptSorted() {

        Map<String,List<Employee>> empListByDept = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        Map<String,List<String>> empName = new HashMap<>();

        empListByDept.forEach((dept,employeeList)->{

          employeeList.sort((a,b)->Double.compare(b.getSalary(),a.getSalary())

          );

          List<String> names = employeeList.stream()
                  .map(Employee::getName)
                  .collect(Collectors.toList());

            empName.put(dept,names);

        });

        System.out.println(empName);

    }

    //26. Find duplicate elements in an array using Java Streams.


    public static void duplicateEmployee() {

        Set<Integer> seen = new HashSet<Integer>();

        List<Employee> duplicateEmployee =
                employees
                        .stream()
                        .filter(e -> !seen.add(e.getId()))
                        .distinct()
                        .collect(Collectors.toUnmodifiableList());

        System.out.println(duplicateEmployee);
    }

    //27. Remove duplicate elements from a list while preserving insertion order.

    public static void removeDuplicateElement() {

        Set<Integer> seen = new HashSet<>();

        List<Employee> uniqueList = employees
                .stream()
                .filter(e->seen.add(e.getId()))
                .collect(Collectors.toList());


        System.out.println(uniqueList);

    }

    //28. Sort employees by salary in ascending order using Streams.

    public static void sortEmployeeBySalaryAsc() {

        List<Employee> sortEmployeeBySalaryAsc = employees
                .stream()
                .sorted((a,b)->Double.compare(a.getSalary(),b.getSalary()))
                .collect(Collectors.toList());

        System.out.println(sortEmployeeBySalaryAsc);

    }

//29. Sort employees by salary in descending order using Streams.

    //46. Sort employees by salary in descending order.

    public static void sortEmployeeBySalaryDesc() {

        List<Employee> sortEmployeeBySalaryDesc = employees
                .stream()
                .sorted((a,b)->Double.compare(b.getSalary(),a.getSalary()))
                .collect(Collectors.toList());

        System.out.println(sortEmployeeBySalaryDesc);

    }

    //30. Find the highest-paid employee from the HR department.

    public static void highestPaidEmpInHRDept() {
        Optional<Employee> result = employees
                .stream()
                .filter(e->e.getDepartment().equalsIgnoreCase("HR"))
                .sorted((a,b)->Double.compare(b.getSalary(), a.getSalary()))
                .findFirst();

        result.ifPresent(System.out::print);
    }

    //31. Find the average of even numbers in an array using Streams.

    public static void findAvgOfEvenMum() {


        List<Integer> numbers = Arrays.asList(3,4,6,8,9,12,14,15,34,23,32,78,65);

        Double avg = numbers.stream()
                .filter(a-> a % 2 == 0)
                .mapToInt(a->a)
                .average()
                .getAsDouble();

        System.out.println(avg);

    }

    //32. Count employees department-wise using groupingBy().

    public static void deptWiseEmployeeCount() {


        Map<String,Long> deptWiseEmployeeCount = employees
                .stream()
                .collect(Collectors.groupingBy(e->e.getDepartment(),
                        Collectors.counting()
                        ));

        System.out.println(deptWiseEmployeeCount);

    }

    //33. Filter employees by department/location and sort them alphabetically.


    public static void filterByDeptSortAlphabattically() {

        List<Employee> filterByDeptSortAlphabattically = employees
                .stream()
                .filter(e->e.getDepartment().equalsIgnoreCase("IT"))
                .sorted(Comparator.comparing(e->e.getName()))
                .collect(Collectors.toList());

        System.out.println(filterByDeptSortAlphabattically);
    }

    //34. Find the frequency of employee names using Streams.

    public static void freqNames() {

        Map<String,Long> nameFreq = employees
                .stream()
                .collect(Collectors.groupingBy(e->e.getName(),
                        Collectors.counting()));

        System.out.println(nameFreq);
    }

    //35. Extract only numeric characters from an alphanumeric char array {'a', '1', 'b', '2', 'c', '3',
    //'d', '4'} using Streams.

    public static void numeric() {

        List<Character> alphanumeric = Arrays.asList('a', '1', 'b', '2', 'c', '3');

        List<Character> numeric = alphanumeric
                .stream()
                .filter(character ->Character.isDigit(character))
                .collect(Collectors.toUnmodifiableList());

        System.out.println(numeric);
    }

    //36. Count the occurrence of each character "hello world" in a string using Streams.

    public static void occurence() {
        String str = "HelloWorld";

        Map<Character,Long> occurence = str.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(c->c,
                        Collectors.counting()
                        ));

        System.out.println(occurence);
    }

    //37. Find the sum of all elements in an array using Streams.

    static List<Integer> numList = Arrays.asList(2,34,53,1,4,3,5,54);

    public static void sumAllElements() {

        Integer sum = numList
                .stream()
                .reduce((a,b)->a+b)
                .get();

        System.out.println(sum);

    }

    //38. Find even numbers from a list and multiply them by 2 using Streams.

    public static void evenNumMultiplyBy2() {

        List<Integer> numbers = Arrays.asList(3,4,6,8,9,12,14,15,34,23,32,78,65);

        List<Integer> evenNumMultiplyBy2 = numbers
                .stream()
                .filter(a-> a % 2 == 0)
                .map(a->a*2)
                .collect(Collectors.toUnmodifiableList());

        System.out.println(evenNumMultiplyBy2);

    }

    //39. Count the occurrence of each word in a string “hello world hello java” using Streams.

    public static void countOccurenceOfWord() {

        String str =  "hello world";

        Map<String,Long> occurence = Arrays.stream(str.split(" "))
                .collect(Collectors.groupingBy(a->a,
                        Collectors.counting()));

        System.out.println(occurence);

    }

    //40. Find common elements from three lists using Streams.

    public static void commonElementFromNestedLists() {

        List<Integer> list1 = Arrays.asList(1, 2, 3);

        List<List<Integer>> nestedList = Arrays.asList(Arrays.asList(2, 3, 4, 2), Arrays.asList(4, 6, 3), list1);

        List<Integer> common = nestedList.get(0).stream()
                .filter(n -> nestedList.get(1).contains(n))
                .filter(n -> nestedList.get(2).contains(n))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(common);
    }

    //41. Convert a numeric string to an integer without using parsing APIs.

    public static void changeStringNum() {

        String str = "12345";
        int num = str.chars()
                .map(a->a-'0')
                .reduce(0,(a,b)-> a*10+b);

        System.out.println(num);

    }

    //42. Find the first occurrence/index of a character ‘o’ in a string “hello world”.

    public static void occurenceOfLetter() {

        String str1 = "hello world";
        Map<Character,Long> result =  str1.chars()
                .mapToObj(c -> (char)c)
                .filter(c->c.equals('o'))
                .collect(Collectors.groupingBy(a->a,
                        Collectors.counting()));

        System.out.println(result);
    }

    //43. Partition numbers into even and odd groups using partitioningBy().

    public static void partitionEvenOdd() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14);

        Map<Boolean,List<Integer>> result = list.stream()
                .collect(Collectors.partitioningBy((a)->a%2==0));

        System.out.println(result.get(true));
        System.out.println(result.get(false));
    }


    //44. Find the longest string in a list using Streams.

    public static void findLongestString() {


        List<String> text = Arrays.asList("java", "java", "Spring", "Spring", "lambda", "collections");

        String result = text.stream()
                .max((a,b)->a.length() > b.length() ? 1:-1)
                .orElse("");

        System.out.println(result);

    }

    //45. Convert a List<Employee> to a Map while handling duplicate keys.

    public static void convertListToMap() {

        Map<Integer,Employee> emp =
                employees.stream()
                        .collect(
                                Collectors.toMap(e->e.getId(),
                                        e->e,
                                        (existing,duplicate)->duplicate)
                        );
        System.out.println(emp);
    }

    //47. Flatten a nested list using flatMap().


    public static void flattenNestedList() {

        List<List<Integer>> nestedList = Arrays.asList(Arrays.asList(2, 3, 4, 2), Arrays.asList(4, 6, 3));


        List<Integer> flatList = nestedList
                .stream()
                .flatMap(a -> a.stream())
                .collect(Collectors.toList());

        System.out.println(flatList);
    }

    //48. Sort characters of a string “TOPIC” alphabetically using Streams.

    public static void sortString() {

        String str = "TOPIC";

        List<Character> sortedWord = str.chars()
                .mapToObj(a->(char)a)
                .sorted()
                .toList();

        System.out.println(sortedWord);
    }

    //49. Find duplicate characters in a string ‘APPLEET’ using Streams.

    public static void duplicateCharacter() {

        String str = "APPLEET";

        Set<Character> seen = new HashSet<>();

        List<Character> dupList = str.chars()
                .mapToObj(a->(char)a)
                .filter(a->!seen.add(a))
                .collect(Collectors.toUnmodifiableList());

        System.out.println(dupList);

    }

    //50. Sort words in a string “bb ddd aa cc aaa” alphabatically using Streams

    public static void sortWords() {

        String str = "bb ddd aa cc aaa";

        List<String> sortedList = Arrays.stream(str.split(" "))
                .map(a->(String)a)
                .sorted()
                .collect(Collectors.toUnmodifiableList());
        System.out.println(sortedList);
    }

    public static void main(String[] args) {
//        getTotalEmployee();
//        getSalaryGreaterThan();
//        deptCount();
//        averageSalary();
//        avgSalryDeptWise();
//        totalSalDeptWise();
//        checkSalGreaterThan();
//        checkAllEmpHaveSalaryGreaterThan();
//        checkAllEmpHaveSalaryLessThan();
//        sumSal();
//        maxSalary();
//        highestPaidEmp();
//        empNameByDept();
//        deptWithHighestAvgSalary();
//        deptNameWithHighestAvgSalary();
//        deptWithHighTotalSal();
//        secondHighestSalary();
//        empWithSecondHighSalary();
//        groupEmpSortBySalDesc();
//        highesPaidEmployeeInEachDept();
//        highestSalInEachDept();
//        empNameByDeptSorted();
//        duplicateEmployee();
//        removeDuplicateElement();
//        sortEmployeeBySalaryAsc();
//        sortEmployeeBySalaryDesc();
//        highestPaidEmpInHRDept();
//        findAvgOfEvenMum();
//        deptWiseEmployeeCount();
//        filterByDeptSortAlphabattically();
//        freqNames();
//        numeric();
//        occurence();
//        sumAllElements();
//        evenNumMultiplyBy2();
//        countOccurenceOfWord();
//        commonElementFromNestedLists();
//        changeStringNum();
//        occurenceOfLetter();
//        partitionEvenOdd();
//        findLongestString();
//        convertListToMap();
//        sortString();
//        duplicateCharacter();
//        sortWords();
        flattenNestedList();

    }
}