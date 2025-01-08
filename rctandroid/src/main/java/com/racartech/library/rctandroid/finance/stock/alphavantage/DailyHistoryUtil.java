package com.racartech.library.rctandroid.finance.stock.alphavantage;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.racartech.library.rctandroid.time.RCTdateTime;
import com.racartech.library.rctandroid.time.RCTdateTimeData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DailyHistoryUtil {


    /**
     * Fetches and processes the daily time series data for a given company symbol.
     *
     * This method uses the AlphaVantage API to retrieve stock data.
     *
     * <p>For a list of available company symbols, refer to:
     * <a href="https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=demo">AlphaVantage Company Symbols</a>
     * </p>
     *
     * @param api_key        The API key for authenticating with the AlphaVantage API.
     * @param company_symbol The stock symbol of the company whose data is to be fetched.
     * @see <a href="https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=demo">AlphaVantage: Listing Status</a>
     */
    public static ArrayList<StockUnit> getHistory(String api_key, String company_symbol){
        Config cfg = Config.builder()
                .key(api_key)
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);

        TimeSeriesResponse time_series_response = AlphaVantage
                .api()
                .timeSeries()
                .daily()
                .forSymbol(company_symbol)
                .outputSize(OutputSize.FULL).fetchSync();

        return (ArrayList<StockUnit>) time_series_response.getStockUnits();
    }

    public static JSONObject toJSONObject(String company_symbol, ArrayList<StockUnit> stock_units){

        JSONObject json_object = new JSONObject();

        long current_lowest_datestamp = Long.MAX_VALUE;
        long current_highest_datestamp = Long.MIN_VALUE;


        try{
            json_object.put("company", company_symbol);
        }catch(Exception ignored) {}

        JSONArray json_array = new JSONArray();
        for(int index = 0; index<stock_units.size(); index++){
            StockUnit unit = stock_units.get(index);
            JSONObject new_object = new JSONObject();
            try {
                new_object.put("date", unit.getDate());
                new_object.put("open",unit.getOpen());
                new_object.put("close",unit.getClose());
                new_object.put("high",unit.getHigh());
                new_object.put("low",unit.getLow());
                new_object.put("adjusted_close",unit.getAdjustedClose());
                new_object.put("volume",unit.getVolume());
                new_object.put("dividend_amount",unit.getDividendAmount());
                new_object.put("split_coefficient",unit.getSplitCoefficient());
                json_array.put(new_object);

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

            try{
                json_object.put("from_millis", current_lowest_datestamp);
                json_object.put("to_millis", current_highest_datestamp);
            }catch(Exception ignored) {}

            try{
                json_object.put("data",json_array);
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return json_object;
    }

    public static ArrayList<StockUnit> toStockUnitList(JSONObject data){
        ArrayList<StockUnit> units = new ArrayList<>();
        try {
            JSONArray json_array = data.getJSONArray("data");
            for(int index = 0; index< json_array.length(); index++){
                JSONObject json_object = json_array.getJSONObject(index);
                StockUnit new_unit = new StockUnit.Builder()
                        .time(json_object.getString("date"))
                        .open(json_object.getDouble("open"))
                        .close(json_object.getDouble("close"))
                        .high(json_object.getDouble("high"))
                        .low(json_object.getDouble("low"))
                        .adjustedClose(json_object.getDouble("adjusted_close"))
                        .volume(json_object.getLong("volume"))
                        .dividendAmount(json_object.getDouble("dividend_amount"))
                        .splitCoefficient(json_object.getDouble("split_coefficient"))
                        .build();
                units.add(new_unit);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return units;
    }


    public static ArrayList<StockUnit> filterDataBaseOnTime(
            ArrayList<StockUnit> stock_units,
            RCTdateTimeData from_date,
            RCTdateTimeData to_date
    ){
        ArrayList<StockUnit> filtered_units = new ArrayList<>();
        for(int index = 0; index< stock_units.size(); index++){
            StockUnit current_unit = stock_units.get(index);
            Date unitDate = RCTdateTime.toDate(current_unit.getDate(),"yyyy-MM-dd");
            RCTdateTimeData unit_date = new RCTdateTimeData(unitDate.getTime());

            if(RCTdateTime.isWithinDateRange(unit_date,from_date,to_date)){
                filtered_units.add(current_unit);
            }
        }
        return filtered_units;
    }
















}
