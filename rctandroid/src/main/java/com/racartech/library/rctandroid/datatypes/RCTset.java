package com.racartech.library.rctandroid.datatypes;

import java.util.HashSet;
import java.util.Set;

public class RCTset{

    public static Set<String> merge_STRING(Set<String> first_set,Set<String> second_set){
        Set<String> new_set = new HashSet<String>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }
    public static Set<Integer> merge_INTEGER(Set<Integer> first_set,Set<Integer> second_set){
        Set<Integer> new_set = new HashSet<Integer>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }
    public static Set<Double> merge_DOUBLE(Set<Double> first_set,Set<Double> second_set){
        Set<Double> new_set = new HashSet<Double>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }
    public static Set<Float> merge_FLOAT(Set<Float> first_set,Set<Float> second_set){
        Set<Float> new_set = new HashSet<Float>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }
    public static Set<Byte> merge_BYTE(Set<Byte> first_set,Set<Byte> second_set){
        Set<Byte> new_set = new HashSet<Byte>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }

    public static Set<Short> merge_SHORT(Set<Short> first_set,Set<Short> second_set){
        Set<Short> new_set = new HashSet<Short>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }

    public static Set<Long> merge_LONG(Set<Long> first_set,Set<Long> second_set){
        Set<Long> new_set = new HashSet<Long>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }
    public static Set<Character> merge_CHARACTER(Set<Character> first_set,Set<Character> second_set){
        Set<Character> new_set = new HashSet<Character>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }
    public static Set<Boolean> merge_BOOLEAN(Set<Boolean> first_set,Set<Boolean> second_set){
        Set<Boolean> new_set = new HashSet<Boolean>(first_set);
        new_set.addAll(second_set);
        return new_set;
    }


    public static Set<String> intersection_STRING(Set<String> first_set,Set<String> second_set){
        Set<String> new_set = new HashSet<String>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }
    public static Set<Integer> intersection_INTEGER(Set<Integer> first_set,Set<Integer> second_set){
        Set<Integer> new_set = new HashSet<Integer>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }
    public static Set<Double> intersection_DOUBLE(Set<Double> first_set,Set<Double> second_set){
        Set<Double> new_set = new HashSet<Double>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }
    public static Set<Float> intersection_FLOAT(Set<Float> first_set,Set<Float> second_set){
        Set<Float> new_set = new HashSet<Float>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }
    public static Set<Byte> intersection_BYTE(Set<Byte> first_set,Set<Byte> second_set){
        Set<Byte> new_set = new HashSet<Byte>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }
    public static Set<Short> intersection_SHORT(Set<Short> first_set,Set<Short> second_set){
        Set<Short> new_set = new HashSet<Short>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }
    public static Set<Long> intersection_LONG(Set<Long> first_set,Set<Long> second_set){
        Set<Long> new_set = new HashSet<Long>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }
    public static Set<Character> intersection_CHARACTER(Set<Character> first_set,Set<Character> second_set){
        Set<Character> new_set = new HashSet<Character>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }
    public static Set<Boolean> intersection_BOOLEAN(Set<Boolean> first_set,Set<Boolean> second_set){
        Set<Boolean> new_set = new HashSet<Boolean>(first_set);
        new_set.retainAll(second_set);
        return new_set;
    }










    public static Set<String> difference_STRING(Set<String> first_set,Set<String> second_set){
        Set<String> new_set = new HashSet<String>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }
    public static Set<Integer> difference_INTEGER(Set<Integer> first_set,Set<Integer> second_set){
        Set<Integer> new_set = new HashSet<Integer>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }
    public static Set<Double> difference_DOUBLE(Set<Double> first_set,Set<Double> second_set){
        Set<Double> new_set = new HashSet<Double>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }
    public static Set<Float> difference_FLOAT(Set<Float> first_set,Set<Float> second_set){
        Set<Float> new_set = new HashSet<Float>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }
    public static Set<Byte> difference_BYTE(Set<Byte> first_set,Set<Byte> second_set){
        Set<Byte> new_set = new HashSet<Byte>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }
    public static Set<Short> difference_SHORT(Set<Short> first_set,Set<Short> second_set){
        Set<Short> new_set = new HashSet<Short>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }
    public static Set<Long> difference_LONG(Set<Long> first_set,Set<Long> second_set){
        Set<Long> new_set = new HashSet<Long>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }
    public static Set<Character> difference_CHARACTER(Set<Character> first_set,Set<Character> second_set){
        Set<Character> new_set = new HashSet<Character>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }
    public static Set<Boolean> difference_BOOLEAN(Set<Boolean> first_set,Set<Boolean> second_set){
        Set<Boolean> new_set = new HashSet<Boolean>(first_set);
        new_set.removeAll(second_set);
        return new_set;
    }






}
