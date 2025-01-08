package com.racartech.library.rctandroid.finance.stock.alphavantage;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PeriodSpanUtil{


    public static ArrayList<PeriodSpan> getPeriodsData(
            ArrayList<StockUnit> units,
            int period_length
    ) {
        if (period_length <= 0 || period_length > units.size()) {
            throw new IllegalArgumentException("Invalid period length, period lengh should be <= 0 and period_length > units.size()");
        }
        int final_index = units.size() - period_length;
        ArrayList<PeriodSpan> periods = new ArrayList<>();
        for (int index = 0; index <= final_index; index++) {
            List<StockUnit> current_period = units.subList(index, index + period_length);
            ArrayList<StockUnit> currentPeriodArrayList = new ArrayList<>(current_period);
            periods.add(new PeriodSpan(currentPeriodArrayList)); // Ensure a new list is created.
        }
        return periods;
    }




    public static void sortPeriodSpansByGrowthPercentage(ArrayList<PeriodSpan> periodSpans) {
        if (periodSpans != null) {
            Collections.sort(periodSpans, new Comparator<PeriodSpan>() {
                @Override
                public int compare(PeriodSpan p1, PeriodSpan p2) {
                    return Double.compare(p1.GROWTH_PERCENTAGE, p2.GROWTH_PERCENTAGE);
                }
            });
        }
    }

    public static ArrayList<Double> getDailyGrowthRate(DailyHistory daily_history){
        ArrayList<Double> daily_growth = new ArrayList<>();

        ArrayList<StockUnit> days_history = daily_history.getDAILY_HISTORY();

        for(int index = 0; index<days_history.size(); index++){
            if(index != 0){
                StockUnit yesterday_unit = days_history.get(index-1);
                StockUnit today_unit = days_history.get(index);
                daily_growth.add(today_unit.getClose()/yesterday_unit.getClose());
            }else{
                daily_growth.add(0.0);
            }
        }
        return daily_growth;
    }

    public static ArrayList<PeriodSpan> sortPeriodSpansByGrowthDescending(ArrayList<PeriodSpan> periodSpans) {
        //PeriodSpan with the highest GROWTH is index 0
        ArrayList<PeriodSpan> sortedList = new ArrayList<>(periodSpans);
        sortedList.sort((o1, o2) -> Double.compare(o2.GROWTH, o1.GROWTH));
        return sortedList;
    }

    public static ArrayList<PeriodSpan> sortPeriodSpansByGrowthAscending(ArrayList<PeriodSpan> periodSpans) {
        // PeriodSpan with the lowest GROWTH will be at index 0
        ArrayList<PeriodSpan> sortedList = new ArrayList<>(periodSpans);
        sortedList.sort((o1, o2) -> Double.compare(o1.GROWTH, o2.GROWTH)); // Ascending order
        return sortedList;
    }

    public static ArrayList<StockUnit> getStockUnitFromYear(ArrayList<StockUnit> stock_units){
        for(int index = 0; index < stock_units.size(); index++){
            StockUnit current_unit = stock_units.get(index);

        }
        //TODO
        return null;
    }

    public static ArrayList<StockUnit> getStockUnitFromMonths(ArrayList<StockUnit> stock_units, int month){
        //Month should be 1-12
        for(int index = 0; index < stock_units.size(); index++){
            StockUnit current_unit = stock_units.get(index);

        }
        //TODO
        return null;
    }

    public static ArrayList<StockUnit> getStockUnitFromMonth(ArrayList<StockUnit> stock_units, int year, int month){
        for(int index = 0; index < stock_units.size(); index++){
            StockUnit current_unit = stock_units.get(index);

        }
        //TODO
        return null;
    }

    public static StockUnit getStockUnitForDate(
            ArrayList<StockUnit> stock_units,
            int year,
            int month,
            int day
    ){

        for(int index = 0; index< stock_units.size(); index++){

        }

        //TODO
        return null;
    }

    public static Double getGrowthForPeriodForMultiCompanies(
            HashMap<String, PeriodSpan> periods
            //String = Company Symbol
    ){
        //TODO
        return null;
    }

    public static Double getGrowthForPeriodsForMultiCompanies(
            HashMap<String, PeriodSpan> periods
            //String = Company Symbol
    ){
        //TODO
        return null;
    }


}
