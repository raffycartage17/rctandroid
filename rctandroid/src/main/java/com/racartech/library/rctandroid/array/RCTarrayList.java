package com.racartech.library.rctandroid.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RCTarrayList{

    public static ArrayList<String> setToArrayList(Set<String> the_set){
        return new ArrayList<>(the_set);
    }

    public static ArrayList<Integer> setToArrayList_Integer(Set<Integer> the_set){
        return new ArrayList<>(the_set);
    }

    public static ArrayList<Double> setToArrayList_Double(Set<Double> the_set){
        return new ArrayList<>(the_set);
    }

    public static ArrayList<Float> setToArrayList_Float(Set<Float> the_set){
        return new ArrayList<>(the_set);
    }
    public static ArrayList<Long> setToArrayList_Long(Set<Long> the_set){
        return new ArrayList<>(the_set);
    }

    public static ArrayList<Short> setToArrayList_Short(Set<Short> the_set){
        return new ArrayList<>(the_set);
    }

    public static ArrayList<Byte> setToArrayList_Byte(Set<Byte> the_set){
        return new ArrayList<>(the_set);
    }

    public static ArrayList<Character> setToArrayList_Character(Set<Character> the_set){
        return new ArrayList<>(the_set);
    }

    public static ArrayList<Boolean> setToArrayList_Boolean(Set<Boolean> the_set){
        return new ArrayList<>(the_set);
    }

    public static Set<String> arrayListToSet(ArrayList<String> the_array_list) {
        return new HashSet<>(the_array_list);
    }

    public static Set<Integer> arrayListToSet_Integer(ArrayList<Integer> the_array_list) {
        return new HashSet<>(the_array_list);
    }

    public static Set<Double> arrayListToSet_Double(ArrayList<Double> the_array_list) {
        return new HashSet<>(the_array_list);
    }
    public static Set<Float> arrayListToSet_Float(ArrayList<Float> the_array_list) {
        return new HashSet<>(the_array_list);
    }
    public static Set<Long> arrayListToSet_Long(ArrayList<Long> the_array_list) {
        return new HashSet<>(the_array_list);
    }
    public static Set<Short> arrayListToSet_Short(ArrayList<Short> the_array_list) {
        return new HashSet<>(the_array_list);
    }
    public static Set<Byte> arrayListToSet_Byte(ArrayList<Byte> the_array_list) {
        return new HashSet<>(the_array_list);
    }
    public static Set<Character> arrayListToSet_Character(ArrayList<Character> the_array_list) {
        return new HashSet<>(the_array_list);
    }
    public static Set<Boolean> arrayListToSet_Boolean(ArrayList<Boolean> the_array_list) {
        return new HashSet<>(the_array_list);
    }



    public static boolean hasTheSameValues(ArrayList<String> list1, ArrayList<String> list2) {
        // Check if the ArrayLists have the same size
        if (list1.size() != list2.size()) {
            return false;
        }

        // Iterate through both ArrayLists and compare their elements
        for (int i = 0; i < list1.size(); i++) {
            String element1 = list1.get(i);
            String element2 = list2.get(i);

            // Compare elements using the equals method (case-sensitive)
            if (!element1.equals(element2)) {
                return false;
            }
        }

        // If all elements are equal, return true
        return true;
    }




}
