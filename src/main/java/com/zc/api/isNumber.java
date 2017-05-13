package com.zc.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chock on 2017/5/13.
 */
public class isNumber {
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^-?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
