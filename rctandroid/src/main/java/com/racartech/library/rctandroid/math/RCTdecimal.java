package com.racartech.library.rctandroid.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RCTdecimal{

    public static Double trimToDecimalPlaceCount(Double the_double, int decimal_places) {
        // Convert the Double to a BigDecimal for precise arithmetic
        BigDecimal decimalValue = BigDecimal.valueOf(the_double);

        // Round the value to the specified decimal places
        BigDecimal roundedValue = decimalValue.setScale(decimal_places, RoundingMode.HALF_UP);

        // Convert the rounded BigDecimal back to Double
        return roundedValue.doubleValue();
    }

    public static Double trimToDecimalPlaceCount(Double the_double, int decimal_places, RoundingMode rounding_mode) {
        // Convert the Double to a BigDecimal for precise arithmetic
        BigDecimal decimalValue = BigDecimal.valueOf(the_double);

        // Round the value to the specified decimal places
        BigDecimal roundedValue = decimalValue.setScale(decimal_places, rounding_mode);

        // Convert the rounded BigDecimal back to Double
        return roundedValue.doubleValue();
    }

    public static float trimToDecimalPlaceCount(float the_float, int decimal_places) {
        // Convert the float to a BigDecimal for precise arithmetic
        BigDecimal decimalValue = BigDecimal.valueOf(the_float);

        // Round the value to the specified decimal places
        BigDecimal roundedValue = decimalValue.setScale(decimal_places, RoundingMode.HALF_UP);

        // Convert the rounded BigDecimal back to float
        return roundedValue.floatValue();
    }

    public static float trimToDecimalPlaceCount(float the_float, int decimal_places, RoundingMode rounding_mode) {
        // Convert the float to a BigDecimal for precise arithmetic
        BigDecimal decimalValue = BigDecimal.valueOf(the_float);

        // Round the value to the specified decimal places
        BigDecimal roundedValue = decimalValue.setScale(decimal_places, rounding_mode);

        // Convert the rounded BigDecimal back to float
        return roundedValue.floatValue();
    }


}
