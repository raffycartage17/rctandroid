package com.racartech.library.rctandroid.math;


import com.racartech.library.rctandroid.array.RCTarray;

public class RCTrandom{
    /**
     * <h2>Description</h2>
     * Generates a random int . The range is specified by the min and max parameters
     * @author  Rafael Andaya Cartagena
     * @version 1.0.0
     * @param min the lowest number the method can generate
     * @param max the highest number the method can generate
     * @since   04-14-2021
     * @return int : returns the random int
     */
    public static int fromTo(int min,int max){
        if(min > max){
            int temp_holder = min;
            min = max;
            max = temp_holder;
        }
        return (int)(Math.random()*(max-min+1))+min;
    }

    /**
     * <h2>Description</h2>
     * Generates a random double . The range is specified by the min and max parameters
     * <br>
     * According to testing of this method, this method fails to generate the min and max
     * <br>
     * if your min is 1.0, this method will fail generate 1.00000000 but can generate 1.00000001
     * <br>
     * if your max is 10.0, this method will fail generate 10.00000000 but can generate 9.9999999999 or below
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @param min the lowest double the method can generate
     * @param max the highest double the method can generate
     * @since   2020-04-27
     * @return int : returns the random int
     */

    public static double fromTo(double min,double max){
        if(min > max){
            double temp_holder = min;
            min = max;
            max = temp_holder;
        }
        double range = max-min;
        return ((Math.random()*range)+min);
    }


    /*
    public static int[] generateUniqueNumbers(int no_of_unique_numbers, int from_range, int to_range){
        boolean batch_selection = false;
        if(from_range > to_range){
            int temp_holder = from_range;
            from_range = to_range;
            to_range = temp_holder;
        }
        if(((to_range+1)-from_range) <no_of_unique_numbers){
            no_of_unique_numbers = ((to_range+1)-from_range);
        }


        if(batch_selection) {
            int range_size = (to_range + 1) - from_range;
            int[] pool_array = new int[range_size];
            int start_int = from_range;
            for (int index = 0; index < range_size; index++) {
                pool_array[index] = start_int;
                start_int++;
            }
            shuffle(pool_array);
            return RCTarray.copyPart(pool_array,0,no_of_unique_numbers-1);
        }else{
            int[] pick_array = new int[target_size];
            int current_search_max_index = 0;
            for(int index = 0; index<pick_array.length;){
                int current_int = target_pool_array[RCTrandom.fromTo(0,target_pool_array.length-1)];
                boolean do_qualify = true;
                for(int search = 0; search<current_search_max_index; search++){
                    if(current_int == pick_array[search]){
                        do_qualify = false;
                        break;
                    }
                }
                if(do_qualify){
                    pick_array[current_search_max_index] = current_int;
                    current_search_max_index++;
                    index++;
                }
            }
            return pick_array;
        }






        return null;
    }

     */


    /**
     * <h2>Description</h2>
     * Shuffle the elements of the target array.
     * @author  Rafael Andaya Cartagena
     * @version 1.0
     * @since 04-22-2021
     */
    public static void shuffle(int[] target_array){
        int target_cycles = target_array.length*8;
        int array_length = target_array.length;
        for(int cycle_count = 0; cycle_count<target_cycles; cycle_count++){
            int index_one = RCTrandom.fromTo(0,(array_length-1));
            int index_two = RCTrandom.fromTo(0,(array_length-1));
            int temp_one = target_array[index_one];
            target_array[index_one] = target_array[index_two];
            target_array[index_two] = temp_one;
        }
    }



    private static int[] manualSelection(int[] target_pool_array,int target_size){
        int[] pick_array = new int[target_size];
        int current_search_max_index = 0;
        for(int index = 0; index<pick_array.length;){
            int current_int = target_pool_array[RCTrandom.fromTo(0,target_pool_array.length-1)];
            boolean do_qualify = true;
            for(int search = 0; search<current_search_max_index; search++){
                if(current_int == pick_array[search]){
                    do_qualify = false;
                    break;
                }
            }
            if(do_qualify){
                pick_array[current_search_max_index] = current_int;
                current_search_max_index++;
                index++;
            }
        }
        return pick_array;
    }
}
