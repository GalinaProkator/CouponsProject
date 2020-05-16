package com.galina.coupons.myutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class MyUtils {
    public static boolean isEmailValid(String email) {
        boolean isEmailValid = Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", email);
        if (!isEmailValid) {
            return false;
        }
        return true;
    }

    public static boolean isUsernameValid(String username) {
        boolean isUsernameValid = Pattern.matches("^[a-z0-9_-]{6,14}$", username);
        if (!isUsernameValid) {
            return false;
        }
        return true;
    }

    public static boolean isNameValid(String name) {
        boolean isNameValid = Pattern.matches("^([A-Z][a-z]*(\\s))+[A-Z][a-z]*$", name);
        if (!isNameValid) {
            return false;
        }
        return true;
    }

    public static int getRowCount(ResultSet resultSet) {
        if (resultSet == null) {
            return 0;
        }

        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public static boolean compareTwoDates(java.util.Date date1, java.util.Date date2){
        if (date1.compareTo(date2) > 0){
            return false; //date1 occurs after date2
        }
        else{
            return true; // date1 occurs before date2 or on the same day
        }
    }

}
