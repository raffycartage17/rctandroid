package com.racartech.app.rctandroidlts;

import com.racartech.library.rctandroid.time.RCTdateTime;
import com.racartech.library.rctandroid.time.RCTdateTimeData;

public class TestObjectRCT{


    public long ID;
    public String FIRST_NAME;
    public String MIDDLE_NAME;
    public String LAST_NAME;
    public long BIRTH_DATE;
    public double BMI;

    public TestObjectRCT(
            long id,
            String f_name,
            String m_name,
            String l_name,
            long birth_date,
            double bmi
    ){
        this.ID = id;
        this.FIRST_NAME = f_name;
        this.MIDDLE_NAME = m_name;
        this.LAST_NAME = l_name;
        this.BIRTH_DATE = birth_date;
        this.BMI = bmi;
    }



}
