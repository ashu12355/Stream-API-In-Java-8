package com.streamApi.stream_Api.StreamJava8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Question {

    public static void FilterEvenNumbers() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14);

        List<Integer> even = list.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(even);
    }

    public static void ConvertListStringUpperCase() {
        List<String> text = Arrays.asList("java", "Spring", "lambda", "collections");

        List<String> uppCase = text
                .stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(uppCase);
    }

    public static void maxValueFromList() {

        List<Integer> maxlist = Arrays.asList(12, 19, 3, 5, 13, 65, 23, 76);

        Integer maxNum = maxlist
                .stream()
                //.max((a,b)-> a > b ? 1: -1)  ->1st Way
                .max((a, b) -> a.compareTo(b))
                .get();
        System.out.println(maxNum);
    }

    public static void groupStringByLength() {

        List<String> text = Arrays.asList("java", "Spring", "lambda", "collections");
        Map<Integer, List<String>> result = text
                .stream()
                .collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(result);

    }

    public static void listStartingWithA() {
        /*
        - Write a program to check if a list contains any word starting with 'A'
        using the Stream API.
         */

        List<String> names = Arrays.asList("Amit", "Suresh", "Animesh", "Vikram", "apple", "banana", "kiwi");

        List<String> results = names
                .stream()
                .filter(a -> a.startsWith("A") || a.startsWith("a"))
                .collect(Collectors.toList());

        System.out.println(results);

    }

    public static void findDuplicateInList() {

        List<Integer> list = Arrays.asList(2, 3, 5, 6, 8, 4, 3, 2, 6, 8, 1, 4, 5, 6, 9, 5, 2);

        Set<Integer> seen = new HashSet<Integer>();
        List<Integer> result = list
                .stream()
                .filter(a -> !seen.add(a)) //we want duplicates so
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public static void removeNullValuesFromList() {
        List<String> names = Arrays.asList("Amit", null, "Suresh", "Animesh", null, "apple", null, "banana", "kiwi");

        List<String> result = names
                .stream()
                .filter(x -> x != null)
                .collect(Collectors.toList());

        System.out.println(result);

    }

    public static void flatListOfList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        List<List<Integer>> listOfList = Arrays.asList(Arrays.asList(7, 8, 9), list1, list2);

        List<Integer> flatList = listOfList
                .stream()
                .flatMap(a -> a.stream())
                .sorted()
                .collect(Collectors.toList());

        System.out.println(flatList);


    }

    public static void partitionOddEven() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14);
        Map<Boolean, List<Integer>> result = list
                .stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println("Even :" + result.get(true));
        System.out.println("Odd :" + result.get(false));
    }

    public static void freqOfElement() {
        /*
Q. How to find the frequency of elements in a list using Java 8 Streams?
or
Q. How to group elements in a list and count their occurrences using Java 8?
or
Q. How to use Collectors.groupingBy and Collectors.counting to calculate element frequency in a list?
or
Q. How to create a frequency map from a list of strings using Java Streams?
         */
        List<String> list = Arrays.asList("MANGO", "APPLE", "BANANA", "GUAVA", "MANGO", "APPLE",
                "BANANA", "APPLE", "BANANA");

        Map<String, Long> result =
                list.stream()
                        .collect(Collectors.groupingBy
                                (word -> word, Collectors.counting()));
        System.out.println(result);
    }

    public static void mergeTwoList() {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(5, 6, 7, 8, 9, 10);

        List<Integer> result =
                Stream
                        .concat(list1.stream(), list2.stream())
                        .collect(Collectors.toList());

        System.out.println(result);

    }

    public static void convertListOfIntegersToTheirSquare() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);

        List<Integer> result = list1
                .stream()
                .map(a -> a * a)
                .collect(Collectors.toList());

        System.out.println(result);
    }

    public static void removeDuplicateWords() {
        List<String> text = Arrays.asList("java", "java", "Spring", "Spring", "lambda", "collections");

        List<String> result = text.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(result);

    }

    public static void findTheFirstElementInAList() {
        List<String> text = Arrays.asList("java", "java2", "Spring1", "Spring2", "lambda", "collections");

        String result = text
                .stream()
                .findFirst()
                .get();

        System.out.println(result);
    }

    public static void SumOfAllElementsInList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14);

        Integer res = list
                .stream()
                .mapToInt(a -> a) //Integer object को primitive int में बदलता है  इससे हमें numeric operations मिलते हैं जैसे sum(), average() |
                // a -> a का मतलब: value को जैसा है वैसा ही pass करना
                .sum();

        System.out.println(res);
    }

    public static void findElementsGreaterThan10() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14);

        List<Integer> result = list
                .stream()
                .filter(a -> a > 10)
                .collect(Collectors.toList());

        System.out.println(result);
    }

    public static void findLongestString() {
        List<String> text = Arrays.asList("java", "Spring", "lambda", "collections");

        Integer length = text
                .stream()
                .mapToInt(a -> a.length())
                .max()
                .orElse(0);

        System.out.println(length);
    }

    public static void findLongestStringAndLength() {
        List<String> text = Arrays.asList("java", "Spring", "lambda", "collections");

        String longest = text.stream()
                .max((a, b) -> a.length() > b.length() ? 1 : -1)
                .orElse("");

        System.out.println("String: " + longest);
        System.out.println("Length: " + longest.length());

    }

    public static void findTheSecondHighestElement() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10);

        Optional<Integer> result = list
                .stream()
                .distinct()
                .sorted((a, b) -> b.compareTo(a)).skip(1).findFirst();
        result.ifPresent(System.out::println);
    }

    public static void collectOddNumber() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10);

        List<Integer> result = list.stream()
                .filter(a -> a % 2 != 0)
                .collect(Collectors.toList());

        System.out.println(result);

    }

    public static void PositiveList() {
        List<Integer> list = Arrays.asList(1, -2, 3, -4, 5, 6, 7, 8, 9, -10, 10);

        Map<Boolean, List<Integer>> rs = list
                .stream()
                .collect(Collectors.partitioningBy(a -> a > 0));
        System.out.println(rs);
    }

    public static void isNegativeList() {
        List<Integer> list = Arrays.asList(1, 2, 3, -4, -5, -6, 7, 8, 9, 10, 10);

        boolean result = list
                .stream()
                .anyMatch(a -> a < 0);
        System.out.println(result);
    }

    public static void skipFirstThree() {
        List<Integer> list = Arrays.asList(1, 2, 3, -4, -5, -6, 7, 8, 9, 10, 10);

        List<Integer> result = list.stream().skip(3).collect(Collectors.toList());

        System.out.println(result);
    }

    public static void isPositiveList() {
        List<Integer> list = Arrays.asList(1, 2, 3, -4, -5, -6, 7, 8, 9, 10, 10);

        boolean result = list
                .stream()
                .noneMatch(a -> a < 0);
        System.out.println(result);
    }

    public static void findMinimum() {
        List<Integer> list = Arrays.asList(1, 2, 3, -4, -5, -6, 7, 8, 9, 10, 10);

        Integer result = list.stream()
                .min((a, b) -> a.compareTo(b)).get();
        System.out.println(result);
    }

    public static void findAverage() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, -5, 6, 7, 8, 9, 10, 10);

        Double result = list
                .stream()
                .mapToInt(a -> a)
                .average()
                .getAsDouble();
        System.out.println(result);
    }

    public static void stringContainingSpecificCharacter() {
        List<String> list = Arrays.asList("java", "Spring", "lambda", "collections");

        List<String> result = list
                .stream()
                .filter(a -> a.contains("a"))
                .collect(Collectors.toList());

        System.out.println(result);
    }

    public static void convertListOfStringToSingleConcatenatedString() {
        List<String> list = Arrays.asList("Ashutosh", "Sharma");

        String result = list.stream().collect(Collectors.joining(" "));

        System.out.println(result);
    }

    public static void sortListOfStringByLength() {
        List<String> list = Arrays.asList("java", "Spring", "lambda", "collections");

        List<String> result = list
                .stream()
                .sorted((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                .collect(Collectors.toList());

        System.out.println(result);
    }

    public static void productOfAllNumbers() {
        List<Integer> list = Arrays.asList(-2, 3, 4, -5);

        Integer result = list.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println(result);
    }

    public static void additionOfAllNumbers() {
        List<Integer> list = Arrays.asList(-2, 3, 4, -5);

        Integer result = list.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(result);
    }

    public static void removeElementLessThan5() {
        List<Integer> list = Arrays.asList(-2, 8, 7, -5);

        List<Integer> result = list.stream()
                .filter(a -> a > 5)
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public static void secondSmallest() {
        List<Integer> list = Arrays.asList(2, 8, 7, 5, 4, 3, 9, 32, 34, 23, 1);

        Optional<Integer> result = list.stream().sorted().skip(1).findFirst();
        result.ifPresent(System.out::println);
    }

    public static void palindromicList() {
        List<String> list = Arrays.asList("Bob", "Civic", "Dad", "Doctor", "Detartrated", "Eve", "Kalyan", "Level");

        List<String> result = list.stream()
                .filter(words -> new StringBuilder(words).reverse().toString()
                        .equalsIgnoreCase(words)).collect(Collectors.toList());
        System.out.println(result);
    }

        public static void convertListToSet() {

            List<String> text = Arrays.asList("java", "java","Spring","Spring", "lambda", "collections");

            Set<String> wordSet = new HashSet<>(text);

            System.out.println(wordSet);
        }

        public static void findMedian() {

        List<Integer> list = Arrays.asList(12,4,5,4,6,3);

        List<Integer> result = list.stream()
                .sorted().collect(Collectors.toList());
        int size = list.size();
        double median = size % 2 == 0 ?
                (result.get(size/2-1)+result.get(size/2))/2.0 : result.get(size/2);
            System.out.println(median);
        }

        public static void generateFibonacciSeries() {
        int n = 10;
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0]+t[1]})
                .limit(n)
                .map(t -> t[0])
                .forEach(a -> System.out.print(a + " "));
        }

        public static void palindromicListCount(){

            List<String> list = Arrays.asList("Bob", "Civic", "Dad", "Doctor", "Detartrated", "Eve", "Kalyan", "Level");

            Long result = list.stream()
                    .filter(words -> new StringBuilder(words)
                            .reverse()
                            .toString()
                            .equalsIgnoreCase(words))
                            .count();

            System.out.println(result);

        }
        public static void reverseList(){
            List<String> listt = Arrays.asList("Bob", "Civic", "Dad", "Doctor", "Detartrated", "Eve", "Kalyan", "Level");

            List<String> result = listt.stream()
            .collect(Collectors.collectingAndThen
                    (Collectors.toList(),
                            list ->{
                        Collections.reverse(list);
                        return list;
                    }));
            System.out.println(result);
        }

        public static void countCharacterInAList() {
            List<String> list = Arrays.asList("Bob", "Civic", "Dad", "Doctor", "Detartrated", "Eve", "Kalyan", "Level");

            Integer count = list.stream()
                    .mapToInt(s -> s.length()).sum();
            System.out.println(count);
        }

        public static void firstNonRepeatingCharcter() {
            String inputVal = "aaabbcccdee";

            Map<Character,Integer> characterCount = new LinkedHashMap<>();

            for(char c : inputVal.toCharArray()){
                characterCount.put(c,characterCount.getOrDefault(c,0)+1);
            }
            characterCount.entrySet().stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .ifPresent(System.out::println);
        }







    public static void main(String[] args) {

//        FilterEvenNumbers();
//        ConvertListStringUpperCase();
//        maxValueFromList();
//        groupStringByLength();
//        listStartingWithA();
//        findDuplicateInList();
//        removeNullValuesFromList();
//        flatListOfList();
//        partitionOddEven();
        freqOfElement();
//        mergeTwoList();
//        convertListOfIntegersToTheirSquare();
//        removeDuplicateWords();
//        findTheFirstElementInAList();
//        SumOfAllElementsInList();
//        findElementsGreaterThan10();
//        findLongestString();
//        findLongestStringAndLength();
//        findTheSecondHighestElement();
//        collectOddNumber();
//        PositiveList();
//        isNegativeList();
//        skipFirstThree();
//        isPositiveList();
//        findMinimum();
//        findAverage();
//        stringContainingSpecificCharacter();
//        convertListOfStringToSingleConcatenatedString();
//        sortListOfStringByLength();
//        sortListOfStringByLength();
//        productOfAllNumbers();
//        additionOfAllNumbers();
//        removeElementLessThan5();
//        secondSmallest();
//        pallindromicList();
//        convertListToSet();
//        findMedian();
//        generateFibonacciSeries();
//        palindromicListCount();
//        reverseList();
//        countCharacterInAList();
        firstNonRepeatingCharcter();

    }
}
