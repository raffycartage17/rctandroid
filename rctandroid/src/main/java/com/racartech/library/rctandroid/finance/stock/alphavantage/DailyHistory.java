package com.racartech.library.rctandroid.finance.stock.alphavantage;

import android.icu.util.TimeZone;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.racartech.library.rctandroid.google.firebase.storage.FstorageFileReference;
import com.racartech.library.rctandroid.google.firebase.storage.RCTfirebaseStorage;
import com.racartech.library.rctandroid.time.RCTdateTime;
import com.racartech.library.rctandroid.time.RCTdateTimeData;

import org.json.JSONObject;

import java.util.ArrayList;

public class DailyHistory{

    private String COMPANY;
    private long FROM_MILLIS;
    private long TO_MILLIS;
    private String TIMESTAMP_FROM;
    private String TIMESTAMP_TO;
    private ArrayList<StockUnit> DAILY_HISTORY;



    public DailyHistory(String api_key, String company){
        this.COMPANY = company;
        this.DAILY_HISTORY = DailyHistoryUtil.getHistory(api_key,company);

        constructorCommons();
    }


    public DailyHistory(String company, ArrayList<StockUnit> daily_history){
        this.COMPANY = company;
        this.DAILY_HISTORY = daily_history;

        constructorCommons();
    }

    private void constructorCommons(){
        processTime();
    }

    private void processTime(){
        long current_lowest_datestamp = Long.MAX_VALUE;
        long current_highest_datestamp = Long.MIN_VALUE;

        for(int index = 0; index<this.DAILY_HISTORY.size(); index++){
            StockUnit unit = this.DAILY_HISTORY.get(index);
            try {


                String[] date_part = unit.getDate().split("-");
                RCTdateTimeData current_date = new RCTdateTimeData(
                        Integer.parseInt(date_part[0]),
                        Integer.parseInt(date_part[1]),
                        Integer.parseInt(date_part[2])
                );
                long stamp_millis = current_date.UNIX_EPOCH_MILLISECOND;
                if(stamp_millis < current_lowest_datestamp){
                    current_lowest_datestamp = stamp_millis;
                }
                if(stamp_millis > current_highest_datestamp){
                    current_highest_datestamp = stamp_millis;
                }
            }catch (Exception ignored){}
        }

        this.FROM_MILLIS = current_lowest_datestamp;
        this.TO_MILLIS = current_highest_datestamp;
        this.TIMESTAMP_FROM = RCTdateTime.getTimestampYYYYMMDD_HHMMSS(this.FROM_MILLIS, TimeZone.getDefault());
        this.TIMESTAMP_TO = RCTdateTime.getTimestampYYYYMMDD_HHMMSS(this.TO_MILLIS, TimeZone.getDefault());
    }




    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public ArrayList<StockUnit> getDAILY_HISTORY() {
        return DAILY_HISTORY;
    }

    public void setDAILY_HISTORY(ArrayList<StockUnit> daily_history) {
        this.DAILY_HISTORY = daily_history;
    }

    public long getFROM_MILLIS() {
        return FROM_MILLIS;
    }

    public void setFROM_MILLIS(long FROM_MILLIS) {
        this.FROM_MILLIS = FROM_MILLIS;
    }

    public long getTO_MILLIS() {
        return TO_MILLIS;
    }

    public void setTO_MILLIS(long TO_MILLIS) {
        this.TO_MILLIS = TO_MILLIS;
    }

    public String getTIMESTAMP_FROM() {
        return TIMESTAMP_FROM;
    }

    public void setTIMESTAMP_FROM(String TIMESTAMP_FROM) {
        this.TIMESTAMP_FROM = TIMESTAMP_FROM;
    }

    public String getTIMESTAMP_TO() {
        return TIMESTAMP_TO;
    }

    public void setTIMESTAMP_TO(String TIMESTAMP_TO) {
        this.TIMESTAMP_TO = TIMESTAMP_TO;
    }
}
