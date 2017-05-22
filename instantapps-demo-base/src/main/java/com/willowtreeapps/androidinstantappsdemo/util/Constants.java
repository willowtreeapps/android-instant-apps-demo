package com.willowtreeapps.androidinstantappsdemo.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by willowtree on 5/14/17.
 */

public class Constants {

    public static final String ROOT_ENDPOINT = "https://bumblebee.willowtreeapps.com";
    public static final DecimalFormat MONEY_FORMAT = new DecimalFormat("¤ #,##0.00;-¤ #,##0.00", new DecimalFormatSymbols(Locale.US));

    private Constants() {
    }

}
