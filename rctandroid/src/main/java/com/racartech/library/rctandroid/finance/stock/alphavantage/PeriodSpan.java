package com.racartech.library.rctandroid.finance.stock.alphavantage;

import android.icu.util.TimeZone;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.racartech.library.rctandroid.math.RCTrandom;
import com.racartech.library.rctandroid.time.RCTdateTime;
import com.racartech.library.rctandroid.time.RCTdateTimeData;

import java.util.ArrayList;

public class PeriodSpan {

    public long PERIOD_ID;
    public ArrayList<StockUnit> DATA;
    public double HIGHEST_HIGH;
    public double LOWEST_LOW;
    public double AVERAGE_HIGH;
    public double AVERAGE_LOW;
    public double AVERAGE_OPEN;
    public double AVERAGE_CLOSE;
    public double PERIOD_OPEN;
    public double PERIOD_CLOSE;
    public double GROWTH_PERCENTAGE;
    public double GROWTH;
    public long FROM_DAY;
    public long TO_DAY;
    public String FROM_DAY_STRING;
    public String TO_DAY_STRING;
    public String COMPANY_SYMBOL = null;


    public PeriodSpan(ArrayList<StockUnit> period_data){
        this.PERIOD_ID = RCTrandom.fromTo(0L,9_223_372_036_854_775_800L);
        DATA = period_data;
        calculateHighestHigh();
        calculateLowestLow();
        calculateAverageHigh();
        calculateAverageLow();
        calculateAverageOpen();
        calculateAverageClose();
        processPeriodOpenAndClose();
    }

    public PeriodSpan(ArrayList<StockUnit> period_data, long period_id){
        this.PERIOD_ID = period_id;
        DATA = period_data;
        calculateHighestHigh();
        calculateLowestLow();
        calculateAverageHigh();
        calculateAverageLow();
        calculateAverageOpen();
        calculateAverageClose();
        processPeriodOpenAndClose();
    }

    public PeriodSpan(ArrayList<StockUnit> period_data, String company_symbol){
        this.PERIOD_ID = RCTrandom.fromTo(0L,9_223_372_036_854_775_800L);
        DATA = period_data;
        this.COMPANY_SYMBOL = company_symbol;
        calculateHighestHigh();
        calculateLowestLow();
        calculateAverageHigh();
        calculateAverageLow();
        calculateAverageOpen();
        calculateAverageClose();
        processPeriodOpenAndClose();
    }

    public PeriodSpan(ArrayList<StockUnit> period_data, long period_id, String company_symbol){
        this.PERIOD_ID = period_id;
        DATA = period_data;
        this.COMPANY_SYMBOL = company_symbol;
        calculateHighestHigh();
        calculateLowestLow();
        calculateAverageHigh();
        calculateAverageLow();
        calculateAverageOpen();
        calculateAverageClose();
        processPeriodOpenAndClose();
    }


    public void setCOMPANY_SYMBOL(String company_symbol){
        this.COMPANY_SYMBOL = company_symbol;
    }


    private void calculateHighestHigh(){
        double current_highest_high = Double.MIN_VALUE;
        for(int index = 0; index<DATA.size(); index++){
            if(DATA.get(index).getHigh() > current_highest_high){
                current_highest_high = DATA.get(index).getHigh();
            }
        }
        HIGHEST_HIGH = current_highest_high;
    }

    private void calculateLowestLow(){
        double current_lowest_low = Double.MAX_VALUE;
        for(int index = 0; index<DATA.size(); index++){
            if(DATA.get(index).getLow() < current_lowest_low){
                current_lowest_low = DATA.get(index).getLow();
            }
        }
        LOWEST_LOW = current_lowest_low;
    }

    private void calculateAverageHigh(){
       double total = 0;
       for(int index = 0; index<DATA.size(); index++){
           total += DATA.get(index).getHigh();
       }
       AVERAGE_HIGH = total/DATA.size();
    }

    private void calculateAverageLow(){
        double total = 0;
        for(int index = 0; index<DATA.size(); index++){
            total += DATA.get(index).getLow();
        }
        AVERAGE_LOW = total/DATA.size();
    }

    private void calculateAverageOpen(){
        double total = 0;
        for(int index = 0; index<DATA.size(); index++){
            total += DATA.get(index).getOpen();
        }
        AVERAGE_OPEN = total/DATA.size();
    }

    private void calculateAverageClose(){
        double total = 0;
        for(int index = 0; index<DATA.size(); index++){
            total += DATA.get(index).getClose();
        }
        AVERAGE_CLOSE = total/DATA.size();
    }

    private void processPeriodOpenAndClose(){

        long current_lowest_datestamp = Long.MAX_VALUE;
        long current_highest_datestamp = Long.MIN_VALUE;

        for(int index = 0; index<DATA.size(); index++){
            StockUnit unit = DATA.get(index);
            String[] date_part = unit.getDate().split("-");
            RCTdateTimeData current_date = new RCTdateTimeData(
                    Integer.parseInt(date_part[0]),
                    Integer.parseInt(date_part[1]),
                    Integer.parseInt(date_part[2])
            );
            long stamp_millis = current_date.UNIX_EPOCH_MILLISECOND;
            if(stamp_millis < current_lowest_datestamp){
                current_lowest_datestamp = stamp_millis;
                PERIOD_OPEN = unit.getOpen();
                this.FROM_DAY = stamp_millis;
            }
            if(stamp_millis > current_highest_datestamp){
                current_highest_datestamp = stamp_millis;
                PERIOD_CLOSE = unit.getClose();
                this.TO_DAY = stamp_millis;
            }
        }

        this.GROWTH_PERCENTAGE = (((this.PERIOD_CLOSE/this.PERIOD_OPEN)*100)-100);
        this.GROWTH = (this.PERIOD_CLOSE-this.PERIOD_OPEN);
        this.FROM_DAY_STRING = RCTdateTime.getTimestampYYYYMMDD(this.FROM_DAY, TimeZone.getDefault());
        this.TO_DAY_STRING = RCTdateTime.getTimestampYYYYMMDD(this.TO_DAY, TimeZone.getDefault());

    }


}
