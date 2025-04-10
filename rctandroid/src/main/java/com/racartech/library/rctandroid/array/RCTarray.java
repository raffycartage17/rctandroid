package com.racartech.library.rctandroid.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RCTarray{


    public static String concatArrayListStringToString(ArrayList<String> arrayList) {
        StringBuilder concatenatedString = new StringBuilder();
        for (String str : arrayList) {
            concatenatedString.append(str);
        }
        return concatenatedString.toString();
    }




    public static ArrayList<String> convertStringArrayToArrayListString(String[] string_array){
        ArrayList<String> arraylist_string = new ArrayList<>();
        for(int index = 0; index<string_array.length; index++){
            arraylist_string.add(string_array[index]);
        }
        return arraylist_string;
    }

    public static String[] convertArrayListStringToStringArray(ArrayList<String> arraylist_string){
        String[] string_array = new String[arraylist_string.size()];
        for(int index = 0; index<arraylist_string.size(); index++){
            string_array[index] = arraylist_string.get(index);
        }
        return string_array;
    }


    public static ArrayList<Integer> convertIntegerArrayToArrayListInteger(int[] the_array){
        ArrayList<Integer> the_arraylist = new ArrayList<>();
        for(int index = 0; index<the_array.length; index++){
            the_arraylist.add(the_array[index]);
        }
        return the_arraylist;
    }

    public static int[] convertArrayListIntegerToIntegerArray(ArrayList<Integer> the_arraylist){
        int[] the_array = new int[the_arraylist.size()];
        for(int index = 0; index<the_arraylist.size(); index++){
            the_array[index] = the_arraylist.get(index);
        }
        return the_array;
    }


    public static ArrayList<Double> convertDoubleArrayToArrayListDouble(double[] the_array){
        ArrayList<Double> the_arraylist = new ArrayList<>();
        for(int index = 0; index<the_array.length; index++){
            the_arraylist.add(the_array[index]);
        }
        return the_arraylist;
    }

    public static double[] convertArrayListDoubleToDoubleArray(ArrayList<Double> the_arraylist){
        double[] the_array = new double[the_arraylist.size()];
        for(int index = 0; index<the_arraylist.size(); index++){
            the_array[index] = the_arraylist.get(index);
        }
        return the_array;
    }

    public static ArrayList<Float> convertFloatArrayToArrayListFloat(float[] the_array){
        ArrayList<Float> the_arraylist = new ArrayList<>();
        for(int index = 0; index<the_array.length; index++){
            the_arraylist.add(the_array[index]);
        }
        return the_arraylist;
    }

    public static float[] convertArrayListFloatToFloatArray(ArrayList<Float> the_arraylist){
        float[] the_array = new float[the_arraylist.size()];
        for(int index = 0; index<the_arraylist.size(); index++){
            the_array[index] = the_arraylist.get(index);
        }
        return the_array;
    }


    public static ArrayList<Long> convertLongArrayToArrayListLong(long[] the_array){
        ArrayList<Long> the_arraylist = new ArrayList<>();
        for(int index = 0; index<the_array.length; index++){
            the_arraylist.add(the_array[index]);
        }
        return the_arraylist;
    }

    public static long[] convertArrayListLongToLongArray(ArrayList<Long> the_arraylist){
        long[] the_array = new long[the_arraylist.size()];
        for(int index = 0; index<the_arraylist.size(); index++){
            the_array[index] = the_arraylist.get(index);
        }
        return the_array;
    }

    public static ArrayList<Short> convertShortArrayToArrayListShort(short[] the_array){
        ArrayList<Short> the_arraylist = new ArrayList<>();
        for(int index = 0; index<the_array.length; index++){
            the_arraylist.add(the_array[index]);
        }
        return the_arraylist;
    }

    public static short[] convertArrayListShortToShortArray(ArrayList<Short> the_arraylist){
        short[] the_array = new short[the_arraylist.size()];
        for(int index = 0; index<the_arraylist.size(); index++){
            the_array[index] = the_arraylist.get(index);
        }
        return the_array;
    }


    public static ArrayList<Character> convertCharArrayToArrayListCharacter(char[] the_array){
        ArrayList<Character> the_arraylist = new ArrayList<>();
        for(int index = 0; index<the_array.length; index++){
            the_arraylist.add(the_array[index]);
        }
        return the_arraylist;
    }

    public static char[] convertArrayListCharacterToCharArray(ArrayList<Character> the_arraylist){
        char[] the_array = new char[the_arraylist.size()];
        for(int index = 0; index<the_arraylist.size(); index++){
            the_array[index] = the_arraylist.get(index);
        }
        return the_array;
    }


    public static ArrayList<Boolean> convertBooleanArrayToArrayListBoolean(boolean[] the_array){
        ArrayList<Boolean> the_arraylist = new ArrayList<>();
        for(int index = 0; index<the_array.length; index++){
            the_arraylist.add(the_array[index]);
        }
        return the_arraylist;
    }

    public static boolean[] convertArrayListBooleanToBooleanArray(ArrayList<Boolean> the_arraylist){
        boolean[] the_array = new boolean[the_arraylist.size()];
        for(int index = 0; index<the_arraylist.size(); index++){
            the_array[index] = the_arraylist.get(index);
        }
        return the_array;
    }











    public static String concatArrayListStringToString(ArrayList<String> array_list,String median){
        if(array_list.size() > 0){
            String current_string = "";
            for(int index = 0; index<array_list.size(); index++){
                if(index != array_list.size()-1){
                    current_string = current_string.concat(array_list.get(index)).concat(median);
                }else{
                    current_string = current_string.concat(array_list.get(index));
                }
            }
            return current_string;
        }else{
            return null;
        }
    }

    public static String concatArrayListIntegerToString(ArrayList<Integer> array_list,String median){
        if(array_list.size() > 0){
            String current_string = "";
            for(int index = 0; index<array_list.size(); index++){
                if(index != array_list.size()-1){
                    current_string = current_string.concat(String.valueOf(array_list.get(index))).concat(median);
                }else{
                    current_string = current_string.concat(String.valueOf(array_list.get(index)));
                }
            }
            return current_string;
        }else{
            return null;
        }
    }

    public static String concatArrayListDoubleToString(ArrayList<Double> array_list,String median){
        if(array_list.size() > 0){
            String current_string = "";
            for(int index = 0; index<array_list.size(); index++){
                if(index != array_list.size()-1){
                    current_string = current_string.concat(String.valueOf(array_list.get(index))).concat(median);
                }else{
                    current_string = current_string.concat(String.valueOf(array_list.get(index)));
                }
            }
            return current_string;
        }else{
            return null;
        }
    }

    public static String concatArrayListFloatToString(ArrayList<Float> array_list,String median){
        if(array_list.size() > 0){
            String current_string = "";
            for(int index = 0; index<array_list.size(); index++){
                if(index != array_list.size()-1){
                    current_string = current_string.concat(String.valueOf(array_list.get(index))).concat(median);
                }else{
                    current_string = current_string.concat(String.valueOf(array_list.get(index)));
                }
            }
            return current_string;
        }else{
            return null;
        }
    }
    public static String concatArrayListLongToString(ArrayList<Long> array_list,String median){
        if(array_list.size() > 0){
            String current_string = "";
            for(int index = 0; index<array_list.size(); index++){
                if(index != array_list.size()-1){
                    current_string = current_string.concat(String.valueOf(array_list.get(index))).concat(median);
                }else{
                    current_string = current_string.concat(String.valueOf(array_list.get(index)));
                }
            }
            return current_string;
        }else{
            return null;
        }
    }

    public static String concatArrayListByteToString(ArrayList<Byte> array_list,String median){
        if(array_list.size() > 0){
            String current_string = "";
            for(int index = 0; index<array_list.size(); index++){
                if(index != array_list.size()-1){
                    current_string = current_string.concat(String.valueOf(array_list.get(index))).concat(median);
                }else{
                    current_string = current_string.concat(String.valueOf(array_list.get(index)));
                }
            }
            return current_string;
        }else{
            return null;
        }
    }

    public static String concatArrayListBooleanToString(ArrayList<Boolean> array_list,String median){
        if(array_list.size() > 0){
            String current_string = "";
            for(int index = 0; index<array_list.size(); index++){
                if(index != array_list.size()-1){
                    current_string = current_string.concat(String.valueOf(array_list.get(index))).concat(median);
                }else{
                    current_string = current_string.concat(String.valueOf(array_list.get(index)));
                }
            }
            return current_string;
        }else{
            return null;
        }
    }
    public static String concatArrayListCharToString(ArrayList<Character> array_list,String median){
        if(array_list.size() > 0){
            String current_string = "";
            for(int index = 0; index<array_list.size(); index++){
                if(index != array_list.size()-1){
                    current_string = current_string.concat(String.valueOf(array_list.get(index))).concat(median);
                }else{
                    current_string = current_string.concat(String.valueOf(array_list.get(index)));
                }
            }
            return current_string;
        }else{
            return null;
        }
    }


    public static String[] convertToStringArray(ArrayList<String> target_list){
        String[] converted_array = new String[target_list.size()];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = target_list.get(index);
        }
        return converted_array;
    }
    public static String[] convertToStringArray(List<String> target_list){
        String[] converted_array = new String[target_list.size()];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = target_list.get(index);
        }
        return converted_array;
    }
    public static ArrayList<String> convertToArrayList(String[] target_list){
        return new ArrayList<String>(Arrays.asList(target_list));
    }
    public static List<String> convertToList(String[] target_list){
        return new ArrayList<String>(Arrays.asList(target_list));
    }


    public static boolean contains(String[] src_array,String target_string,boolean case_sensitive){
        if(case_sensitive) {
            for (int index = 0; index < src_array.length; index++) {
                if(target_string.equals(src_array[index])){
                    return true;
                }
            }
        }else{
            target_string = target_string.toLowerCase();
            for (int index = 0; index < src_array.length; index++) {
                if(target_string.equals(src_array[index].toLowerCase())){
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * <h2>Description</h2>
     * Removes all null in the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static String[] removeAllNull(String[] target_array){
        int null_count = 0;
        for(String s : target_array){
            if (s == null) {
                null_count++;
            }
        }
        int[] null_indexes = new int[null_count];
        int current_index = 0;
        for(int index = 0; index< target_array.length; index++){
            if(target_array[index] == null){
                null_indexes[current_index] = index;
                current_index++;
            }
        }
        String[] new_array = new String[target_array.length-null_count];
        new_array = deleteIndexes(target_array,null_indexes);
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(String[] target_array){
        String[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(int[] target_array){
        int[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(double[] target_array){
        double[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(float[] target_array){
        float[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(byte[] target_array){
        byte[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(short[] target_array){
        short[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(long[] target_array){
        long[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(char[] target_array){
        char[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    /**
     * <h2>Description</h2>
     * Reverses the target_array elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-24-2021
     */
    public static void reverse(boolean[] target_array){
        boolean[] cloned_array = copy(target_array);
        int current_index = target_array.length-1;
        for(int index = 0; index < target_array.length ; index++){
            target_array[index] = cloned_array[current_index];
            current_index--;
        }
    }

    public static void trimStringArray(String[] target_array){
        for(int index = 0; index< target_array.length; index++){
            target_array[index] = target_array[index].trim();
        }
    }


    public static void replaceNullOccurrences(String[] target_array){
        for(int index = 0; index<target_array.length; index++){
            if(target_array[index] == null){
                target_array[index] = "null";
            }
        }
    }


    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static String[] deletePart(String[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            String[] new_array = new String[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }

    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static int[] deletePart(int[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            int[] new_array = new int[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }

    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static double[] deletePart(double[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            double[] new_array = new double[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }

    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static float[] deletePart(float[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            float[] new_array = new float[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }

    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static byte[] deletePart(byte[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            byte[] new_array = new byte[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }

    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static short[] deletePart(short[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            short[] new_array = new short[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }

    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static long[] deletePart(long[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            long[] new_array = new long[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }


    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static char[] deletePart(char[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            char[] new_array = new char[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }

    /**
     * <h2>Description</h2>
     * Delete the target range of indexes and return it as a new_array.
     * If the parameters are out of range or invalid, the original address of the target_array will be returned.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param target_array the array to be processed
     * @since 04-16-2021
     */
    public static boolean[] deletePart(boolean[] target_array,int from_index,int to_index){
        boolean pass_token = true;
        if(to_index >= from_index){
            if(from_index < 0 && to_index>=target_array.length){
                pass_token = false;
            }
        }
        if(pass_token){
            boolean[] new_array = new boolean[target_array.length-((to_index+1)-from_index)];
            int current_index = 0;
            for(int index = 0; index< target_array.length; index++){
                if(index < from_index || index > to_index){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    index += (to_index-from_index);
                }
            }
            return new_array;
        }
        return target_array;
    }


    public static int getIndex_LinearSearch(String[] to_search_array, String value){
        for(int index = 0; index<to_search_array.length; index++){
            if(to_search_array[index].equals(value)){
                return index;
            }
        }
        return -1;
    }

    public static String[] merge(String[] first_array, String[] second_array){
        String[] new_array = new String[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }
    public static int[] merge(int[] first_array, int[] second_array){
        int[] new_array = new int[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }
    public static double[] merge(double[] first_array, double[] second_array){
        double[] new_array = new double[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }

    public static float[] merge(float[] first_array, float[] second_array){
        float[] new_array = new float[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }

    public static byte[] merge(byte[] first_array, byte[] second_array){
        byte[] new_array = new byte[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }

    public static short[] merge(short[] first_array, short[] second_array){
        short[] new_array = new short[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }

    public static long[] merge(long[] first_array, long[] second_array){
        long[] new_array = new long[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }

    public static char[] merge(char[] first_array, char[] second_array){
        char[] new_array = new char[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }

    public static boolean[] merge(boolean[] first_array, boolean[] second_array){
        boolean[] new_array = new boolean[first_array.length + second_array.length];
        System.arraycopy(first_array, 0, new_array, 0, first_array.length);
        System.arraycopy(second_array, 0, new_array, first_array.length, second_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static String[] add(String[] target_array,String value_to_add){
        String[] new_array = new String[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static int[] add(int[] target_array,int value_to_add){
        int[] new_array = new int[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static double[] add(double[] target_array,double value_to_add){
        double[] new_array = new double[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static float[] add(float[] target_array,float value_to_add){
        float[] new_array = new float[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static byte[] add(byte[] target_array,byte value_to_add){
        byte[] new_array = new byte[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static short[] add(short[] target_array,short value_to_add){
        short[] new_array = new short[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static long[] add(long[] target_array,long value_to_add){
        long[] new_array = new long[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static char[] add(char[] target_array,char value_to_add){
        char[] new_array = new char[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Adds the value_to_add to the end of the target_array
     * @author  Rafael Andaya Cartagena
     * @since   04-05-2021
     * @return  new array
     */
    public static boolean[] add(boolean[] target_array,boolean value_to_add){
        boolean[] new_array = new boolean[target_array.length+1];
        System.arraycopy(target_array, 0, new_array, 0, target_array.length);
        new_array[target_array.length] = value_to_add;
        return new_array;
    }




    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static String[] copy(String[] target_array){
        String[] new_array = new String[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static int[] copy(int[] target_array){
        int[] new_array = new int[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static double[] copy(double[] target_array){
        double[] new_array = new double[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static float[] copy(float[] target_array){
        float[] new_array = new float[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static byte[] copy(byte[] target_array){
        byte[] new_array = new byte[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static short[] copy(short[] target_array){
        short[] new_array = new short[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static long[] copy(long[] target_array){
        long[] new_array = new long[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static char[] copy(char[] target_array){
        char[] new_array = new char[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Copy the target_array and return it as a new_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-06-2021
     */
    public static boolean[] copy(boolean[] target_array){
        boolean[] new_array = new boolean[target_array.length];
        System.arraycopy(target_array, 0, new_array, 0, new_array.length);
        return new_array;
    }

    public static String[] copy(String[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }
    public static int[] copy(int[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }
    public static double[] copy(double[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }
    public static float[] copy(float[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }
    public static byte[] copy(byte[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }
    public static short[] copy(short[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }
    public static long[] copy(long[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }
    public static char[] copy(char[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }
    public static boolean[] copy(boolean[] target_array, int from, int to){
        return Arrays.copyOfRange(target_array, from, to+1);
    }


    public static String[] copy(String[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        String[] new_array = new String[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }

    public static int[] copy(int[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        int[] new_array = new int[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }

    public static double[] copy(double[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        double[] new_array = new double[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }

    public static float[] copy(float[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        float[] new_array = new float[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }

    public static byte[] copy(byte[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int targetIndex : target_indexes){
            if(targetIndex >= 0 && targetIndex <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        byte[] new_array = new byte[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }

    public static short[] copy(short[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        short[] new_array = new short[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }

    public static long[] copy(long[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        long[] new_array = new long[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }

    public static char[] copy(char[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        char[] new_array = new char[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }

    public static boolean[] copy(boolean[] target_array, int[] target_indexes){
        int valid_index_count = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                valid_index_count++;
            }
        }
        boolean[] new_array = new boolean[valid_index_count];
        int current_index = 0;
        for(int target_index : target_indexes){
            if(target_index >= 0 && target_index <= (target_array.length - 1)){
                new_array[current_index] = target_array[target_index];
                current_index++;
            }
        }
        return  new_array;
    }









    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(String[] target_array,String target_element){
        int match_count = 0;
        for (String s : target_array) {
            if (s.equals(target_element)) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(int[] target_array,int target_element){
        int match_count = 0;
        for (int i : target_array) {
            if (i == target_element) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(double[] target_array,double target_element){
        int match_count = 0;
        for (double v : target_array) {
            if (v == target_element) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(float[] target_array,float target_element){
        int match_count = 0;
        for (float v : target_array) {
            if (v == target_element) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(byte[] target_array,byte target_element){
        int match_count = 0;
        for (byte b : target_array) {
            if (b == target_element) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(short[] target_array,short target_element){
        int match_count = 0;
        for (short i : target_array) {
            if (i == target_element) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(long[] target_array,long target_element){
        int match_count = 0;
        for (long l : target_array) {
            if (l == target_element) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(char[] target_array,char target_element){
        int match_count = 0;
        for (char c : target_array) {
            if (c == target_element) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Linear Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaLinearSearch(boolean[] target_array,boolean target_element){
        int match_count = 0;
        for (boolean b : target_array) {
            if (b == target_element) {
                match_count++;
            }
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Binary Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaBinarySearch(String[] target_array, String target_element){
        int target_array_length = target_array.length;
        int match_count = 0;
        int pivot_index = Arrays.binarySearch(target_array,target_element);
        if(pivot_index >= 0){
            match_count++;
            for(int index = (pivot_index+1); index<target_array_length; index++){
                if(target_array[index].equals(target_element)){
                    match_count++;
                }else{
                    break;
                }
            }
            for(int index = (pivot_index-1); index >= 0; index--){
                if(target_array[index].equals(target_element)){
                    match_count++;
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        return match_count;
    }


    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Binary Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaBinarySearch(int[] target_array, int target_element){
        int target_array_length = target_array.length;
        int match_count = 0;
        int pivot_index = Arrays.binarySearch(target_array,target_element);
        if(pivot_index >= 0){
            match_count++;
            for(int index = (pivot_index+1); index<target_array_length; index++){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
            for(int index = (pivot_index-1); index >= 0; index--){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Binary Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaBinarySearch(double[] target_array, double target_element){
        int target_array_length = target_array.length;
        int match_count = 0;
        int pivot_index = Arrays.binarySearch(target_array,target_element);
        if(pivot_index >= 0){
            match_count++;
            for(int index = (pivot_index+1); index<target_array_length; index++){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
            for(int index = (pivot_index-1); index >= 0; index--){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Binary Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaBinarySearch(float[] target_array, float target_element){
        int target_array_length = target_array.length;
        int match_count = 0;
        int pivot_index = Arrays.binarySearch(target_array,target_element);
        if(pivot_index >= 0){
            match_count++;
            for(int index = (pivot_index+1); index<target_array_length; index++){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
            for(int index = (pivot_index-1); index >= 0; index--){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Binary Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaBinarySearch(byte[] target_array, byte target_element){
        int target_array_length = target_array.length;
        int match_count = 0;
        int pivot_index = Arrays.binarySearch(target_array,target_element);
        if(pivot_index >= 0){
            match_count++;
            for(int index = (pivot_index+1); index<target_array_length; index++){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
            for(int index = (pivot_index-1); index >= 0; index--){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Binary Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaBinarySearch(short[] target_array, short target_element){
        int target_array_length = target_array.length;
        int match_count = 0;
        int pivot_index = Arrays.binarySearch(target_array,target_element);
        if(pivot_index >= 0){
            match_count++;
            for(int index = (pivot_index+1); index<target_array_length; index++){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
            for(int index = (pivot_index-1); index >= 0; index--){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Binary Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaBinarySearch(long[] target_array, long target_element){
        int target_array_length = target_array.length;
        int match_count = 0;
        int pivot_index = Arrays.binarySearch(target_array,target_element);
        if(pivot_index >= 0){
            match_count++;
            for(int index = (pivot_index+1); index<target_array_length; index++){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
            for(int index = (pivot_index-1); index >= 0; index--){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Counts the number of matching elements in the target_array via Binary Search
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int countMatchViaBinarySearch(char[] target_array, char target_element){
        int target_array_length = target_array.length;
        int match_count = 0;
        int pivot_index = Arrays.binarySearch(target_array,target_element);
        if(pivot_index >= 0){
            match_count++;
            for(int index = (pivot_index+1); index<target_array_length; index++){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
            for(int index = (pivot_index-1); index >= 0; index--){
                if(target_array[index] == target_element){
                    match_count++;
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        return match_count;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static String[] delete(String[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        String[] new_array = new String[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static int[] delete(int[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        int[] new_array = new int[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static double[] delete(double[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        double[] new_array = new double[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static float[] delete(float[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        float[] new_array = new float[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static byte[] delete(byte[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        byte[] new_array = new byte[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static short[] delete(short[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        short[] new_array = new short[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static long[] delete(long[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        long[] new_array = new long[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static char[] delete(char[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        char[] new_array = new char[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Deletes the target_index and return it as a new_array. If the target_index is invalid, this method will return the original address of the target_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-07-2021
     */
    public static boolean[] delete(boolean[] target_array, int target_index){
        if(target_index < 0) {
            return target_array;
        }
        if(target_index >= target_array.length){
            return target_array;
        }
        boolean[] new_array = new boolean[target_array.length-1];
        int current_index = 0;
        for(int index = 0; index<target_array.length; index++) {
            if (index != target_index) {
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static String[] mergeAllArrays(String[][] arrays_of_arrays){
        int total_elements = 0;
        for (String[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        String[] new_array = new String[total_elements];
        int current_index = 0;

        for (String[] arrays_of_array : arrays_of_arrays) {
            for (String arrays_of_array1 : arrays_of_array) {
                new_array[current_index] = arrays_of_array1;
                current_index++;
            }
        }
        return new_array;
    }


    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static int[] mergeAllArrays(int[][] arrays_of_arrays){
        int total_elements = 0;
        for (int[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        int[] new_array = new int[total_elements];
        int current_index = 0;

        for (int[] arrays_of_array : arrays_of_arrays) {
            for (int i : arrays_of_array) {
                new_array[current_index] = i;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static double[] mergeAllArrays(double[][] arrays_of_arrays){
        int total_elements = 0;
        for (double[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        double[] new_array = new double[total_elements];
        int current_index = 0;

        for (double[] arrays_of_array : arrays_of_arrays) {
            for (double v : arrays_of_array) {
                new_array[current_index] = v;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static float[] mergeAllArrays(float[][] arrays_of_arrays){
        int total_elements = 0;
        for (float[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        float[] new_array = new float[total_elements];
        int current_index = 0;

        for (float[] arrays_of_array : arrays_of_arrays) {
            for (float v : arrays_of_array) {
                new_array[current_index] = v;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static byte[] mergeAllArrays(byte[][] arrays_of_arrays){
        int total_elements = 0;
        for (byte[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        byte[] new_array = new byte[total_elements];
        int current_index = 0;

        for (byte[] arrays_of_array : arrays_of_arrays) {
            for (byte b : arrays_of_array) {
                new_array[current_index] = b;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static short[] mergeAllArrays(short[][] arrays_of_arrays){
        int total_elements = 0;
        for (short[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        short[] new_array = new short[total_elements];
        int current_index = 0;

        for (short[] arrays_of_array : arrays_of_arrays) {
            for (short i : arrays_of_array) {
                new_array[current_index] = i;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static long[] mergeAllArrays(long[][] arrays_of_arrays){
        int total_elements = 0;
        for (long[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        long[] new_array = new long[total_elements];
        int current_index = 0;

        for (long[] arrays_of_array : arrays_of_arrays) {
            for (long l : arrays_of_array) {
                new_array[current_index] = l;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static char[] mergeAllArrays(char[][] arrays_of_arrays){
        int total_elements = 0;
        for (char[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        char[] new_array = new char[total_elements];
        int current_index = 0;

        for (char[] arrays_of_array : arrays_of_arrays) {
            for (char c : arrays_of_array) {
                new_array[current_index] = c;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2
     * Merge the array of 1D arrays in to 1 single 1D Array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-14-2021
     */
    public static boolean[] mergeAllArrays(boolean[][] arrays_of_arrays){
        int total_elements = 0;
        for (boolean[] arrays_of_array : arrays_of_arrays) {
            total_elements = total_elements + arrays_of_array.length;
        }
        boolean[] new_array = new boolean[total_elements];
        int current_index = 0;

        for (boolean[] arrays_of_array : arrays_of_arrays) {
            for (boolean b : arrays_of_array) {
                new_array[current_index] = b;
                current_index++;
            }
        }
        return new_array;
    }


    public static String concatArrayToString(String[] target_array,String separator){
        return String.join(separator,target_array);
    }
    /**
     * <h2>Description</h2
     * Will concatenate every element in the target_array into one single string
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-05-2021
     * @param target_array the array to be processed
     * @param separator separator between elements
     */
    public static String concatArrayToString(double[] target_array, String separator){
        String[] converted_array = new String[target_array.length];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = String.valueOf(target_array[index]);
        }
        return String.join(separator,converted_array);
    }

    /**
     * <h2>Description</h2
     * Will concatenate every element in the target_array into one single string
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-05-2021
     * @param target_array the array to be processed
     * @param separator separator between elements
     */
    public static String concatArrayToString(float[] target_array,String separator){
        String[] converted_array = new String[target_array.length];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = String.valueOf(target_array[index]);
        }
        return String.join(separator,converted_array);
    }

    /**
     * <h2>Description</h2
     * Will concatenate every element in the target_array into one single string
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-05-2021
     * @param target_array the array to be processed
     * @param separator separator between elements
     */
    public static String concatArrayToString(byte[] target_array,String separator){
        String[] converted_array = new String[target_array.length];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = String.valueOf(target_array[index]);
        }
        return String.join(separator,converted_array);
    }

    /**
     * <h2>Description</h2
     * Will concatenate every element in the target_array into one single string
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-05-2021
     * @param target_array the array to be processed
     * @param separator separator between elements
     */
    public static String concatArrayToString(short[] target_array,String separator){
        String[] converted_array = new String[target_array.length];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = String.valueOf(target_array[index]);
        }
        return String.join(separator,converted_array);
    }

    /**
     * <h2>Description</h2
     * Will concatenate every element in the target_array into one single string
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-05-2021
     * @param target_array the array to be processed
     * @param separator separator between elements
     */
    public static String concatArrayToString(long[] target_array,String separator){
        String[] converted_array = new String[target_array.length];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = String.valueOf(target_array[index]);
        }
        return String.join(separator,converted_array);
    }

    /**
     * <h2>Description</h2
     * Will concatenate every element in the target_array into one single string
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-05-2021
     * @param target_array the array to be processed
     * @param separator separator between elements
     */
    public static String concatArrayToString(char[] target_array,String separator){
        String[] converted_array = new String[target_array.length];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = String.valueOf(target_array[index]);
        }
        return String.join(separator,converted_array);
    }

    /**
     * <h2>Description</h2
     * Will concatenate every element in the target_array into one single string
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since   04-05-2021
     * @param target_array the array to be processed
     * @param separator separator between elements
     */
    public static String concatArrayToString(boolean[] target_array,String separator){
        String[] converted_array = new String[target_array.length];
        for(int index = 0; index<converted_array.length; index++){
            converted_array[index] = String.valueOf(target_array[index]);
        }
        return String.join(separator,converted_array);
    }


    public static int[] determineValidIndexes(int array_length,int[] indexes){
        int valid_index_count = 0;
        for (int i : indexes) {
            if (i >= 0 && i < array_length) {
                valid_index_count++;
            }
        }
        int[] valid_indexes = new int[valid_index_count];
        int current_index = 0;
        for (int i : indexes) {
            if (i >= 0 && i < array_length) {
                valid_indexes[current_index] = i;
                current_index++;
            }
        }
        return valid_indexes;
    }

    /**
     * <h2>Description</h2>
     * Will sort the original target array, as this method will sort the target array using Arrays.sort built-in in java. Then remove all duplicated elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param is_already_sorted = if set to false, the method will sort the array, if set to true, it means that the user already sort the array
     * @since 04-05-2021
     */
    public static String[] removeAllDuplicatedElementsAfterSort(String[] target_array,boolean is_already_sorted){
        if(!is_already_sorted){
            Arrays.sort(target_array);
        }
        int unique_elements_count = 0;
        if(target_array.length > 0){
            unique_elements_count++;
        }
        String current_element = null;
        if(target_array.length > 0){
            current_element = target_array[0];
        }
        for(int index = 1; index<target_array.length; index++){
            if(!target_array[index].equals(current_element)){
                unique_elements_count++;
                current_element = target_array[index];
            }
        }
        String[] new_array = new String[unique_elements_count];
        int current_index = 1;

        if(target_array.length > 0){
            current_element = target_array[0];
            new_array[0] = target_array[0];
        }

        for (String target_array1 : target_array) {
            if (!target_array1.equals(current_element)) {
                unique_elements_count++;
                current_element = target_array1;
                new_array[current_index] = target_array1;
                current_index++;
            }
        }
        return new_array;
    }


    /**
     * <h2>Description</h2>
     * Will sort the original target array, as this method will sort the target array using Arrays.sort built-in in java. Then remove all duplicated elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param is_already_sorted = if set to false, the method will sort the array, if set to true, it means that the user already sort the array
     * @since 04-05-2021
     */
    public static int[] removeAllDuplicatedElementsAfterSort(int[] target_array,boolean is_already_sorted){
        if(!is_already_sorted){
            Arrays.sort(target_array);
        }
        int unique_elements_count = 0;
        if(target_array.length > 0){
            unique_elements_count++;
        }
        int current_element = 0;
        if(target_array.length > 0){
            current_element = target_array[0];
        }
        for(int index = 1; index<target_array.length; index++){
            if(target_array[index] != current_element){
                unique_elements_count++;
                current_element = target_array[index];
            }
        }
        int[] new_array = new int[unique_elements_count];
        int current_index = 1;

        if(target_array.length > 0){
            current_element = target_array[0];
            new_array[0] = target_array[0];
        }

        for (int i : target_array) {
            if (i != current_element) {
                unique_elements_count++;
                current_element = i;
                new_array[current_index] = i;
                current_index++;
            }
        }


        return new_array;
    }



    /**
     * <h2>Description</h2>
     * Will sort the original target array, as this method will sort the target array using Arrays.sort built-in in java. Then remove all duplicated elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param is_already_sorted = if set to false, the method will sort the array, if set to true, it means that the user already sort the array
     * @since 04-05-2021
     */
    public static double[] removeAllDuplicatedElementsAfterSort(double[] target_array,boolean is_already_sorted){
        if(!is_already_sorted){
            Arrays.sort(target_array);
        }
        int unique_elements_count = 0;
        if(target_array.length > 0){
            unique_elements_count++;
        }
        double current_element = 0;
        if(target_array.length > 0){
            current_element = target_array[0];
        }
        for(int index = 1; index<target_array.length; index++){
            if(target_array[index] != current_element){
                unique_elements_count++;
                current_element = target_array[index];
            }
        }
        double[] new_array = new double[unique_elements_count];
        int current_index = 1;

        if(target_array.length > 0){
            current_element = target_array[0];
            new_array[0] = target_array[0];
        }

        for (double v : target_array) {
            if (v != current_element) {
                unique_elements_count++;
                current_element = v;
                new_array[current_index] = v;
                current_index++;
            }
        }
        return new_array;
    }


    /**
     * <h2>Description</h2>
     * Will sort the original target array, as this method will sort the target array using Arrays.sort built-in in java. Then remove all duplicated elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param is_already_sorted = if set to false, the method will sort the array, if set to true, it means that the user already sort the array
     * @since 04-05-2021
     */
    public static float[] removeAllDuplicatedElementsAfterSort(float[] target_array,boolean is_already_sorted){
        if(!is_already_sorted){
            Arrays.sort(target_array);
        }
        int unique_elements_count = 0;
        if(target_array.length > 0){
            unique_elements_count++;
        }
        float current_element = 0;
        if(target_array.length > 0){
            current_element = target_array[0];
        }
        for(int index = 1; index<target_array.length; index++){
            if(target_array[index] != current_element){
                unique_elements_count++;
                current_element = target_array[index];
            }
        }
        float[] new_array = new float[unique_elements_count];
        int current_index = 1;

        if(target_array.length > 0){
            current_element = target_array[0];
            new_array[0] = target_array[0];
        }

        for (float v : target_array) {
            if (v != current_element) {
                unique_elements_count++;
                current_element = v;
                new_array[current_index] = v;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Will sort the original target array, as this method will sort the target array using Arrays.sort built-in in java. Then remove all duplicated elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param is_already_sorted = if set to false, the method will sort the array, if set to true, it means that the user already sort the array
     * @since 04-05-2021
     */
    public static byte[] removeAllDuplicatedElementsAfterSort(byte[] target_array,boolean is_already_sorted){
        if(!is_already_sorted){
            Arrays.sort(target_array);
        }
        int unique_elements_count = 0;
        if(target_array.length > 0){
            unique_elements_count++;
        }
        byte current_element = 0;
        if(target_array.length > 0){
            current_element = target_array[0];
        }
        for(int index = 1; index<target_array.length; index++){
            if(target_array[index] != current_element){
                unique_elements_count++;
                current_element = target_array[index];
            }
        }
        byte[] new_array = new byte[unique_elements_count];
        int current_index = 1;

        if(target_array.length > 0){
            current_element = target_array[0];
            new_array[0] = target_array[0];
        }

        for(byte b : target_array) {
            if (b != current_element) {
                unique_elements_count++;
                current_element = b;
                new_array[current_index] = b;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Will sort the original target array, as this method will sort the target array using Arrays.sort built-in in java. Then remove all duplicated elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param is_already_sorted = if set to false, the method will sort the array, if set to true, it means that the user already sort the array
     * @since 04-05-2021
     */
    public static short[] removeAllDuplicatedElementsAfterSort(short[] target_array,boolean is_already_sorted){
        if(!is_already_sorted){
            Arrays.sort(target_array);
        }
        int unique_elements_count = 0;
        if(target_array.length > 0){
            unique_elements_count++;
        }
        short current_element = 0;
        if(target_array.length > 0){
            current_element = target_array[0];
        }
        for(int index = 1; index<target_array.length; index++){
            if(target_array[index] != current_element){
                unique_elements_count++;
                current_element = target_array[index];
            }
        }
        short[] new_array = new short[unique_elements_count];
        int current_index = 1;

        if(target_array.length > 0){
            current_element = target_array[0];
            new_array[0] = target_array[0];
        }

        for (short i : target_array) {
            if (i != current_element) {
                unique_elements_count++;
                current_element = i;
                new_array[current_index] = i;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Will sort the original target array, as this method will sort the target array using Arrays.sort built-in in java. Then remove all duplicated elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param is_already_sorted = if set to false, the method will sort the array, if set to true, it means that the user already sort the array
     * @since 04-05-2021
     */
    public static long[] removeAllDuplicatedElementsAfterSort(long[] target_array,boolean is_already_sorted){
        if(!is_already_sorted){
            Arrays.sort(target_array);
        }
        int unique_elements_count = 0;
        if(target_array.length > 0){
            unique_elements_count++;
        }
        long current_element = 0;
        if(target_array.length > 0){
            current_element = target_array[0];
        }
        for(int index = 1; index<target_array.length; index++){
            if(target_array[index] != current_element){
                unique_elements_count++;
                current_element = target_array[index];
            }
        }
        long[] new_array = new long[unique_elements_count];
        int current_index = 1;

        if(target_array.length > 0){
            current_element = target_array[0];
            new_array[0] = target_array[0];
        }

        for(long l : target_array) {
            if (l != current_element) {
                unique_elements_count++;
                current_element = l;
                new_array[current_index] = l;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Will sort the original target array, as this method will sort the target array using Arrays.sort built-in in java. Then remove all duplicated elements
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param is_already_sorted = if set to false, the method will sort the array, if set to true, it means that the user already sort the array
     * @since 04-05-2021
     */
    public static char[] removeAllDuplicatedElementsAfterSort(char[] target_array,boolean is_already_sorted){
        if(!is_already_sorted){
            Arrays.sort(target_array);
        }
        int unique_elements_count = 0;
        if(target_array.length > 0){
            unique_elements_count++;
        }
        char current_element = 0;
        if(target_array.length > 0){
            current_element = target_array[0];
        }
        for(int index = 1; index<target_array.length; index++){
            if(target_array[index] != current_element){
                unique_elements_count++;
                current_element = target_array[index];
            }
        }
        char[] new_array = new char[unique_elements_count];
        int current_index = 1;

        if(target_array.length > 0){
            current_element = target_array[0];
            new_array[0] = target_array[0];
        }

        for(char c : target_array) {
            if (c != current_element) {
                unique_elements_count++;
                current_element = c;
                new_array[current_index] = c;
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static String[] deleteIndexes(String[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        String[] new_array = new String[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static int[] deleteIndexes(int[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        int[] new_array = new int[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static double[] deleteIndexes(double[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        double[] new_array = new double[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static float[] deleteIndexes(float[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        float[] new_array = new float[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static byte[] deleteIndexes(byte[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        byte[] new_array = new byte[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static short[] deleteIndexes(short[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        short[] new_array = new short[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static long[] deleteIndexes(long[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        long[] new_array = new long[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static char[] deleteIndexes(char[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        char[] new_array = new char[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes the specified indexes of the target_array based on indexes_to_delete_array
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @since 04-05-2021
     */
    public static boolean[] deleteIndexes(boolean[] target_array,int[] indexes_to_delete){
        Arrays.sort(indexes_to_delete);
        indexes_to_delete = removeAllDuplicatedElementsAfterSort(indexes_to_delete,true);
        indexes_to_delete = determineValidIndexes(target_array.length,indexes_to_delete);
        boolean[] new_array = new boolean[target_array.length-indexes_to_delete.length];
        int current_index = 0;
        int itd_index = 0;
        for(int index = 0; index<target_array.length; index++){
            if(itd_index<indexes_to_delete.length){
                if(index != indexes_to_delete[itd_index]){
                    new_array[current_index] = target_array[index];
                    current_index++;
                }else{
                    itd_index++;
                }
            }else{
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }


    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static String[] deleteIndexesBasedOnBooleanArray(String[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for(boolean b : comparison_array){
            if (b) {
                valid_indexes_count++;
            }
        }
        String[] new_array = new String[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }


    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static int[] deleteIndexesBasedOnBooleanArray(int[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for(boolean b : comparison_array){
            if (b) {
                valid_indexes_count++;
            }
        }
        int[] new_array = new int[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static double[] deleteIndexesBasedOnBooleanArray(double[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for(boolean b : comparison_array){
            if (b) {
                valid_indexes_count++;
            }
        }
        double[] new_array = new double[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static float[] deleteIndexesBasedOnBooleanArray(float[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for(boolean b : comparison_array){
            if (b) {
                valid_indexes_count++;
            }
        }
        float[] new_array = new float[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static byte[] deleteIndexesBasedOnBooleanArray(byte[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for (boolean b : comparison_array) {
            if(b){
                valid_indexes_count++;
            }
        }
        byte[] new_array = new byte[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static short[] deleteIndexesBasedOnBooleanArray(short[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for (boolean b : comparison_array) {
            if (b) {
                valid_indexes_count++;
            }
        }
        short[] new_array = new short[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static long[] deleteIndexesBasedOnBooleanArray(long[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for (boolean b : comparison_array) {
            if (b) {
                valid_indexes_count++;
            }
        }
        long[] new_array = new long[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static char[] deleteIndexesBasedOnBooleanArray(char[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for (boolean b : comparison_array) {
            if (b) {
                valid_indexes_count++;
            }
        }
        char[] new_array = new char[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }

    /**
     * <h2>Description</h2>
     * Deletes all elements of the target_array based on the values of comparison_array. All indexes with the equivalent of true
     *<br>
     * will be saved to the new_array and false equivalent will be deleted
     *<br>
     * target_array length and comparison_array length must be the same
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since   05-02-2021
     */
    public static boolean[] deleteIndexesBasedOnBooleanArray(boolean[] target_array, boolean[] comparison_array){
        int valid_indexes_count = 0;
        for (boolean b : comparison_array) {
            if (b) {
                valid_indexes_count++;
            }
        }
        boolean[] new_array = new boolean[valid_indexes_count];
        int current_index = 0;
        for(int index = 0; index<comparison_array.length; index++){
            if(comparison_array[index]){
                new_array[current_index] = target_array[index];
                current_index++;
            }
        }
        return new_array;
    }









}
