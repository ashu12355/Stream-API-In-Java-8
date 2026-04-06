package com.streamApi.stream_Api.java8;

import java.util.*;
import java.util.function.Function;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.*;
import static org.springframework.beans.factory.support.ManagedList.of;

public class StreamApi {
    /**
     Separate odd and even numbers in a list of integers.

     Given a list of integers, write a Java 8 program to separate
      the odd and even numbers into two separate lists.
     */
//    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private static void separationOfEvenOddNumberInList(){
        List<Integer> list = of(1,2,3,4,5,6,7,8,9,10);

        Collection<List<Integer>> evenOddList = list.stream()
                .collect(collectingAndThen(partitioningBy(i -> i%2 == 0), Map::values));

        System.out.println(evenOddList);
    }

    private static void separationOfEvenOddNumberInMap() {
        List<Integer> list = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean,List<Integer>> evenOddListMap = list.stream()
                .collect(partitioningBy(i -> i%2 == 0));

        System.out.println(evenOddListMap);
    }



    /**
     * Write a Java 8 program to remove duplicate elements from a list
     * using the stream API and lambda expressions.
     */

    private static void removeDuplicatesFromList() {
        List<Integer> duplicates = of(2,2,1,4,5,1,2,3,4,5,6,8,7);

       List<Integer> duplicatevalues =  duplicates.stream()
                .distinct()
                .collect(toList());
       System.out.println(duplicatevalues);

        //without order
        Set<Integer> removeDuplicatesWithoutOrder = duplicates.stream()
                .collect(toSet());
        System.out.println(removeDuplicatesWithoutOrder);
    }
     /**
     * Find the frequency of each character in a string using Java 8 streams
      */
     private static void characterFrequency() {
         String s = "AshutoshshutA";

         Map<String, Long> characterFrequency = Arrays.stream(s.split(""))
                 .collect(groupingBy(Function.identity(), counting()));
         System.out.println(characterFrequency);


         Map<Character, Long> collected = s.chars()
                 .mapToObj(ch -> (char)ch)
                 .collect(groupingBy(Function.identity(),counting()));
         System.out.println(collected);

         Map<String, Integer> characterFrequencyInt = Arrays.stream(s.split(""))
                 .collect(groupingBy(Function.identity(),collectingAndThen(counting(),Long::intValue)));
         System.out.println(characterFrequency);
     }
    /**
     * Find the frequency of each element in an array or a list
     */
 private static void wordFrequency() {
     List<String> names = Arrays.asList("rohit", "urmila", "rohit", "urmila", "ram", "sham", "sita", "gita");

     Map<String,Long> count = names.stream()
             .collect(groupingBy(Function.identity(),counting()));
     System.out.println(count);
 }


/**
 * Sort a given list of decimals in reverse order
 */
 private static void sortedList() {
     List<Integer> list = of(12, 32, 2, 4, 777, 5, 32, 890, 422, 44, 99, 43);

     List<Integer> inOrder = list.stream()
             .sorted((x,y)->Integer.compare(x,y))
             .collect(toList());

     System.out.println(inOrder);

     List<Integer> Reverse = list.stream()
             .sorted((x,y)->Integer.compare(y,x))
             .collect(toList());

     System.out.println(Reverse);

     List<Integer> inOrder2 = list.stream().sorted(reverseOrder())
             .collect(toList());
     System.out.println(inOrder2);
 }


    /**
     * Join a list of strings with '[' as prefix, ']' as suffix, and ',' as delimiter
     *
     * Given a list of strings, write a Java 8 program to join the strings
     * with '[' as a prefix, ']' as a suffix, and ',' as a delimiter.
     */

    public static void main(String[] args) {
//        separationOfEvenOddNumberInList();
//        separationOfEvenOddNumberInMap();
//        removeDuplicatesFromList();
//        characterFrequency();
//            wordFrequency();
        sortedList();
    }
}
