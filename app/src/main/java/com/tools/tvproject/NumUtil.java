package com.tools.tvproject;

import java.text.DecimalFormat;

public class NumUtil {

    private NumUtil(){

    }

    public static String FormatFloat(float value){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }
    public static String FormatRoundUp(boolean isRoundUp,float value){
        DecimalFormat df;
        if(isRoundUp){
            df = new DecimalFormat("######0"); //四色五入转换成整数

            return df.format(value);
        }else{
            Float f= new Float(value);
            int i=f.intValue();
            return String.valueOf(i);
        }
    }


}
